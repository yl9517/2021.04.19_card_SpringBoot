package com.springweb.web;

import com.springweb.service.CardService;
import com.springweb.web.dto.CardDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import javax.smartcardio.Card;
import java.util.ArrayList;
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
    //카드사 페이지
    @GetMapping("/card/card_company_page")
    public String card_company(){
        return "card_company";
    }

    //카드 리스트 이동 (조건검색 후)
    @PostMapping("/card_list_page")
    public String card_list(HttpServletRequest request ,Model model){

        //값가져오기
        String foundType = request.getParameter("intype"); //카드타입
        String foundBene1 = request.getParameter("inbene0"); //혜택 3개
        String foundBene2 = request.getParameter("inbene1");
        String foundBene3 = request.getParameter("inbene2");

        System.out.println("타입"+foundType);

        String[] foundBene = {foundBene1,foundBene2,foundBene3};
        String[] thisBene = {"교통","마트/편의점","쇼핑","영화/문화","주유","카페/디저트","통신"};
        String[] changeBene = {"benefit_traffic.png","benefit_mart.png","benefit_shopping.png","benefit_movie.png","benefit_gas.png","benefit_cafe.png","benefit_phone.png"};

        for(int i=0; i<foundBene.length; i++){

            System.out.println("혜택 한글"+foundBene[i]);

            for(int j=0; j< thisBene.length; j++) {
                foundBene[i].replace(thisBene[j], changeBene[j]);
            }

            System.out.println("혜택영어"+changeBene[i]);
        }
////////////////


        List<CardDto> cardlist = cardService.getAllCard();
        List<CardDto> foundList = new ArrayList<>();

        //혜택값이 널이라면..??

        for(int i=0; i<cardlist.size(); i++){
            for(int j=0; j< thisBene.length; j++) {

                if(cardlist.get(i).getCardType().equals(foundType)){ //카드타입 같은거 , 카드 혜택 같은거 골라내기
                    if(cardlist.get(i).getBenefit1().equals(changeBene[j]) || cardlist.get(i).getBenefit2().equals(changeBene[j]) || cardlist.get(i).getBenefit3().equals(changeBene[j])) {

                        if(foundList.size()==0){
                            foundList.add(cardlist.get(i));
                        }
                        for(int x=0; x< foundList.size(); x++){
                            if(foundList.get(x).getCardCode() != cardlist.get(i).getCardCode()) { //같은코드가 들어가있지 않으면 넣기
                                foundList.add(cardlist.get(i));
                            }
                        }

                    }
                }

            }
        }

        model.addAttribute("cardlist",foundList);
        return "card_list";
    }

    //카드 비교함 이동
    @GetMapping("/card_compare_page")
    public String card_compare(HttpServletRequest request , Model model){

        //카드가져오기
       Long one = Long.valueOf(request.getParameter("one"));
       Long two = Long.valueOf(request.getParameter("two"));

        //해당 카드 찾기
        CardDto card1 = cardService.getCard(one);
        CardDto card2 = cardService.getCard(two);

        //찾았으면 model 담아서 전달
        model.addAttribute("card1",card1);
        model.addAttribute("card2",card2);

        return "card_compare";
    }

    //카드 상세보기 이동
    @GetMapping("/card_detail_page/{code}")
    public String card_detail(@PathVariable("code") Long code, Model model){
         //해당 카드를 찾기
        CardDto cardDto = cardService.getCard(code);
        
        //찾았으면 model 담아서 프론트엔드에게 전달
        model.addAttribute("cardDto",cardDto);
        
        //html 페이지 열기
        return "card_detail";
    }

}
