package com.springweb.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class cardsController {

    @GetMapping("/card/cards_page")
    public String cards(){

        return "cards_page";
    }

    public String cards_page(){

        return
    }

}
