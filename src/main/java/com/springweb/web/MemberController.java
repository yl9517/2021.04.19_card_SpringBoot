package com.springweb.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MemberController {

    // html
    @GetMapping("/user/login_page") // http: 주소 설정
    public String Login_page(){
        return "login"; // login.html 연결
    }
}
