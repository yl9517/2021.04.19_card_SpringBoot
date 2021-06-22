package com.springweb.web;

import com.springweb.service.CardService;
import com.springweb.web.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class indexController {
    private final CardService cardService;


    @GetMapping("/")
    public String index(Model model){ //모델 = 백과 프론트 사이의 정보이동

        List<CardDto> randomCard = cardService.getMainCard();
        model.addAttribute("randomList", randomCard);
        return "main";
    }

}
