package com.springweb.service;

import com.springweb.domain.user.UserRepository;
import com.springweb.domain.user.User;
import com.springweb.web.dto.OauthAttributes;
import com.springweb.web.dto.SesstionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OauthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    private final HttpSession httpSession; //세션

    @Override //유저 가져오기 메소드 재정의
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //(구글인지 네이버인지 카카오인지) application=oauth.properties에 등록된 클라이언트만 인식

        //DTO
        OauthAttributes attributes = OauthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        //db에 저장
        User user = saveOrUpdat(attributes);

        //로그인 성공 시 세션에 저장
        httpSession.setAttribute("user",new SesstionUser(user));

        //리턴값
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRolekey())),
                attributes.getAttribute(),
                attributes.getNameAttributekey()
        );


    }

    //새로운 유저는 저장 // 기존 유저는 업데이트
    private User saveOrUpdat (OauthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail()) //회원의 이메일 찾기
            .map(entity -> entity.update(attributes.getName())) //동일한 이메일이 있으면 회원명 업데이트
                .orElse(attributes.toEntity()); //동일한 이메일이 없으면 엔티티에 저장

        return userRepository.save(user); //db에 저장장
    }
}
