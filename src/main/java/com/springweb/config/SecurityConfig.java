package com.springweb.config;

import com.springweb.service.OauthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  //해당 클래스 설정 어노테이션
@EnableWebSecurity //해당 클래스는 웹 보안 연결 어노테이션
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
            //시큐리티[보안] 설정
    private final OauthService oauthService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
            //1. 웹 서비스 전체 설정
        web.ignoring().antMatchers("/css/**","/js/**","/images/**","/templates/**");
            //ignoring : 보안해제
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
      //          .antMatchers("/user/myinfo").hasRole("MEMBER") //회원페이지 있으면 이거 쓰기
                .antMatchers("/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/user/login_page")  //로그인 url
                    .loginProcessingUrl("/user/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf() //아래 특정 url 들은 CSRF 방지 처리 적용X
                    .ignoringAntMatchers("/h2-console/**") //해지해놓아서 우리가 h2에서 정보 변경할수 있음
                    .ignoringAntMatchers("/post/**")
                    .ignoringAntMatchers("/admin/**")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //로그아웃 url
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error") //에러페이지 url
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(oauthService);

        http.headers().frameOptions().disable(); //csrf 사용 시 X-frame 자동 사용되는것을 비활성화시키기

    }
}
