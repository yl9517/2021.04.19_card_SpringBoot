package com.springweb.config;

import lombok.AllArgsConstructor;
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
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                    .ignoringAntMatchers("/post/**")
                    .ignoringAntMatchers("/admin/**")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //로그아웃 url
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error"); //에러페이지 url

    }
}
