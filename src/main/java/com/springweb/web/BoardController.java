package com.springweb.web;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BoardController {



    @GetMapping("/board/boardlist_page")
    public String boardlist_page(){
        return "boardlist";
    }

    @GetMapping("/board/boardwrite_page")
    public String boardwrite_page(){
        return "board_write";
    }




}
