package com.springweb.web;

import com.springweb.web.dto.SesstionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class indexController {


    @GetMapping("/")
    public String index(){ //모델 = 백과 프론트 사이의 정보이동?

        return "main";
    }

}
