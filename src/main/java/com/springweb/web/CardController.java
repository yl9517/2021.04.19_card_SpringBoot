package com.springweb.web;

import com.springweb.service.CardCompanyService;
import com.springweb.service.CardService;
import com.springweb.web.dto.CardCompanyDto;
import com.springweb.web.dto.CardDto;
import com.springweb.web.dto.CardUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor //빈 생성자
public class CardController {

    private final CardService cardService;
    private final CardCompanyService cardCompanyService;

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
    @GetMapping("/card_company_page")
    public String card_company(Model model){
        List<CardCompanyDto> cardCompanyList = cardCompanyService.getAllCardCompany();

        model.addAttribute("companyList", cardCompanyList);
        return "card_company";
    }



    //해당 카드사의 리스트 (카드사 검색 후)
    @GetMapping("/find_company/{name}")
    public String finCompany_list(@PathVariable("name") String name ,Model model) {

        List<CardCompanyDto> cardCompanyList = cardCompanyService.getAllCardCompany();
        model.addAttribute("companyList", cardCompanyList);


        List<CardDto> cardlist = cardService.getAllCard();
        List<CardDto> foundList = new ArrayList<>();

        for(int i=0; i<cardlist.size(); i++){
            if(cardlist.get(i).getCardCompany().equals(name)){ //카드사 골라내기
                foundList.add(cardlist.get(i));
            }
        }

        model.addAttribute("thisCardlist",foundList);
        model.addAttribute("thisCompany",name);

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


        String[] foundBene = {foundBene1,foundBene2,foundBene3};
        String[] thisBene = {"교통","마트/편의점","쇼핑","영화/문화","주유","카페/디저트","통신"};
        String[] changeBene = {"benefit_traffic.png","benefit_mart.png","benefit_shopping.png","benefit_movie.png","benefit_gas.png","benefit_cafe.png","benefit_phone.png"};

        for(int i=0; i<foundBene.length; i++){
            for(int j=0; j< thisBene.length; j++) {
                foundBene[i] = foundBene[i].replace(thisBene[j], changeBene[j]);
            }
        }

        List<CardDto> cardlist = cardService.getAllCard();
        List<CardDto> foundList = new ArrayList<>();


        for(int i=0; i<cardlist.size(); i++){

            if(cardlist.get(i).getCardType().equals(foundType) || foundType.equals("전체")){ //카드타입 같은거 , 카드 혜택 같은거 골라내기


                if (foundBene[0].equals("") && foundBene[1].equals("") && foundBene[2].equals("")){ //카드혜택 아무것도 선택 안했을 경우
                    foundList.add(cardlist.get(i));
                }
                else {
                    for (int j = 0; j < foundBene.length; j++) { //3번 돌기

                        if (cardlist.get(i).getBenefit1().equals(foundBene[j]) || cardlist.get(i).getBenefit2().equals(foundBene[j]) || cardlist.get(i).getBenefit3().equals(foundBene[j])) {

                            if (foundList.size() == 0) {
                                foundList.add(cardlist.get(i));
                            } else {
                                if (!foundList.contains(cardlist.get(i))) { //found리스트 안에 찾는카드의 코드가 없으면
                                    foundList.add(cardlist.get(i));
                                }
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

    //개별카드에서 카드신청 눌렀을때 - 카드사 링크 카운트 수정
    @PostMapping("/card_countUp")
    public String countUp(HttpServletRequest request){

        Long code = Long.valueOf(request.getParameter("cardCode"));
        String link = request.getParameter("cardLink");

        cardService.UpdateCount(code);
        return "redirect:"+link;
    }



}
