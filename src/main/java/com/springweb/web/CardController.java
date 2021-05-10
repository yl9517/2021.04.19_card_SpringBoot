package com.springweb.web;

import com.springweb.service.CardService;
import com.springweb.web.dto.CardDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor //빈 생성자
public class CardController {

    private final CardService cardService;

    // get 조회 |  post 생성  |  put 수정  |  delete 삭제

    //html 연결
    //카드 차트 이동
    @GetMapping("/card/card_chart_page") // http 주소 설정
    public String card_chart(Model model){

        List<CardDto> cardRankList = cardService.getRankCard();
        model.addAttribute("rankList",cardRankList);
        return "card_chart"; //card_chart.html 연결 (헤더의 해당 링크에 html 연결, th로 http 주소 연결)
    }

    //카드 조건검색창 이동
    @GetMapping("/card/card_search_page")
    public String card_search(){

        return "cardSearch";
    }
    
    //카드 리스트 이동 (조건검색 후)
    @GetMapping("/card/card_list_page")
    public String card_list(Model model){

        List<CardDto> cardlist = cardService.getAllCard();
        model.addAttribute("cardlist",cardlist);
        return "card_list";
    }

    //카드 비교함 이동
    @GetMapping("/card/card_compare_page")
    public String card_compare(){
        return "card_compare";
    }

    //카드 상세보기 이동
    @GetMapping("/card/card_detail_page/{code}")
    public String card_detail(@PathVariable("code") Long code, Model model){
         //해당 게시물 번호를 찾기
        CardDto cardDto = cardService.getCard(code);
        
        //찾았으면 model 담아서 프론트엔드에게 전달
        model.addAttribute("cardDto",cardDto);
        
        //html 페이지 열기
        return "card_detail";
    }

    //카드 등록페이지 이동

    //카드 등록 기능

    //카드 수정 기능

    //카드 삭제 기능



}
