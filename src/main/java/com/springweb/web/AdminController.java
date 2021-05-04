package com.springweb.web;

import com.springweb.service.CardService;
import com.springweb.web.dto.CardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor //빈 생성자
public class AdminController {

    private final CardService cardService;

    // get 조회 |  post 생성  |  put 수정  |  delete 삭제

    //html 연결
    //카드 차트 이동
    @GetMapping("/admin") // http 주소 설정
    public String mainPage(){

        return "admin_CardList"; //card_chart.html 연결 (헤더의 해당 링크에 html 연결, th로 http 주소 연결)
    }

    //카드 등록페이지 이동

    //카드 등록 기능

    //카드 수정 기능

    //카드 삭제 기능



}
