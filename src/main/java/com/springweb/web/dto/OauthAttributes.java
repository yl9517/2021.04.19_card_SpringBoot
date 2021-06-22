package com.springweb.web.dto;

import com.springweb.domain.user.Role;
import com.springweb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OauthAttributes {

    private Map<String, Object> attribute; //회원정보
    private String nameAttributekey; //요청 정보의 키
    private String name;  //이름
    private String email; //이메일

    @Builder
    public OauthAttributes(Map<String, Object> attribute, String nameAttributekey, String name, String email) {
        this.attribute = attribute;
        this.nameAttributekey = nameAttributekey;
        this.name = name;
        this.email = email;
    }

    //sns 구분 메소드
    public static OauthAttributes of(String registrationId, String usernameAttributename, Map<String,Object> attribute){
        //카카오일때 return
        if(registrationId.equals("kakao")){
            return ofKakao(usernameAttributename,attribute);
        }
        //네이버일때 return
        else if(registrationId.equals("naver")){
            return ofNaver(usernameAttributename,attribute);
        }
        else {
            return ofGoogle(usernameAttributename, attribute);
        }
    }

    //구글 정보 빼오기 메소드
    public static OauthAttributes ofGoogle(String usernameAttributename, Map<String,Object> attribute){
        return OauthAttributes.builder()
                .name((String) attribute.get("name")) //이름을 빼오려면 구글에서 정한 'name' 이라는 문자써야함
                .email((String) attribute.get("email"))
                .attribute(attribute)
                .nameAttributekey(usernameAttributename)
                .build();
    }
    //카카오 정보 빼오기 메소드
    private static OauthAttributes ofKakao(String userNameAttributeName,Map<String, Object> attribute) {
        //요청 정보로 객체 생성
        Map<String, Object> kakaoAccount = (Map<String, Object> )  attribute.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object> ) kakaoAccount.get("profile");

        return OauthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .attribute(attribute)
                .nameAttributekey(userNameAttributeName)
                .build();
    }

    //네이버 정보 빼오기 메소드
    private static OauthAttributes ofNaver(String userNameAttributeName,Map<String, Object> attribute) {
        //요청 정보로 객체 생성
        Map<String, Object> response = (Map<String, Object> )  attribute.get("response");

        return OauthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attribute(attribute)
                .nameAttributekey(userNameAttributeName)
                .build();
    }


    //엔티티로 넣어주기 메소드
    public User toEntity(){
        return User.builder().name(name).email(email).role(Role.ADMIN).build(); //관리자로 바로.
    } //나중에 role.member로 바꾸기

}
