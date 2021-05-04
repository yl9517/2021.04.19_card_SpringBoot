package com.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication //해당 클래스에게 스프링 부트 데이터 제공
public class WebStart {

    //상속 : 상위클래스가 해당 클래스에게 변수와 메소드, 생성자 제공
    //@어노테이션 : 하나의 프로그램을 제공
    //장점 : 별도의 설치가 필요없음

    public static void main(String[] args) {
        SpringApplication.run(WebStart.class, args);

    }
}
