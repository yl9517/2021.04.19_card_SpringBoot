package com.springweb.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor //빈 생성자
public class CardController {

    // get 조회 |  post 생성  |  put 수정  |  delete 삭제

    //html 연결
    @GetMapping("/card/card_chart_page") // http 주소 설정
    public String card_chart(){

        return "card_chart"; //card_chart.html 연결 (헤더의 해당 링크에 html 연결, th로 http 주소 연결)
    }

    @GetMapping("/card/card_search_page")
    public String card_search(){

        return "cardSearch";
    }
}
