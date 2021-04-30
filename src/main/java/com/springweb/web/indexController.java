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

    private final HttpSession httpSession; //로그인 했는지 안했는지

    @GetMapping("/")
    public String index(Model model){ //모델 = 백과 프론트 사이의 정보이동?

        SesstionUser user = (SesstionUser) httpSession.getAttribute("user");

        if(user !=null){
            model.addAttribute("logName",user.getName());
        }

        return "main";
    }

}
