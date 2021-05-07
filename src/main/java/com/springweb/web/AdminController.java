package com.springweb.web;

import com.springweb.service.CardService;
import com.springweb.web.dto.CardDto;
import com.springweb.web.dto.CardUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.smartcardio.Card;
import java.io.File;
import java.util.List;

@Controller
@AllArgsConstructor //빈 생성자
public class AdminController {

    private final CardService cardService;

    // get 조회 |  post 생성  |  put 수정  |  delete 삭제

    //html 연결
    //카드 리스트 이동
    @GetMapping("/admin") // http 주소 설정
    public String mainPage(Model model){

        List<CardDto> cardlist = cardService.getAllCard();
        model.addAttribute("cardlist",cardlist);

        return "admin_CardList"; //card_chart.html 연결 (헤더의 해당 링크에 html 연결, th로 http 주소 연결)
    }

    //카드 등록페이지 이동
    @GetMapping("/admin/card_regist_page")
    public String regist_page(){
        return "admin_CardRegister";
    }

    //카드 등록 기능
    @PostMapping("/admin/card_register")
    public String register(@RequestParam("cardPhoto") MultipartFile files, HttpServletRequest request){
        try{
            String baseDir="C:\\Users\\yl951\\IdeaProjects\\springProject\\src\\main\\resources\\static\\images"; //파일이 저장되는 위치
            String filePath = baseDir+"\\"+files.getOriginalFilename(); //파일이 저장되는 위치 +파일명
            files.transferTo(new File(filePath));       //파일 저장!!!

            CardDto cardDto = new CardDto();

            cardDto.setCardPhoto(files.getOriginalFilename());
            cardDto.setCardName( request.getParameter("cardName"));
            cardDto.setCardCompany(request.getParameter("cardCompany"));
            cardDto.setAnnualFee( Integer.parseInt(request.getParameter("annualFee")));
            cardDto.setBeforePay(Integer.parseInt(request.getParameter("beforePay")));
            cardDto.setCardType(request.getParameter("cardType"));
//            cardDto.setBenefit1("benefit1");
//            cardDto.setBenefit1_detail("benefit1_detail");
//            cardDto.setBenefit2("benefit2");
//            cardDto.setBenefit2_detail("benefit2_detail");
//            cardDto.setBenefit3("benefit3");
//            cardDto.setBenefit3_detail("benefit3_detail");
            cardDto.setCardLink(request.getParameter("cardLink"));
            cardDto.setCount(0);


            cardService.SaveCard(cardDto);
            return "redirect:/admin";//다 쓰면 리스트로 돌리기

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/admin"; //다 쓰면 리스트로 돌리기
    }

    //카드 수정페이지 이동
    @GetMapping("/admin/card_update_page/{id}")
    public String card_update_page(@PathVariable("id") Long id,Model model){
        //카드코드 넣어서 해당 카드 찾기
        CardDto cardDto = cardService.getCard(id);

        //카드에게 넘겨주기
        model.addAttribute("cardDto", cardDto);

        return "admin_CardUpdate";
    }

    //카드 수정 기능
    @PutMapping("/admin/card_update/{id}")
    @ResponseBody
    public void update(@PathVariable("id") Long id, @RequestBody CardUpdateDto cardUpdateDto){ /* ajax로 받을때 requestbody로 받음*/

        cardService.UpdateCard(id, cardUpdateDto);

    }

    //카드 삭제 기능
    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id){
        cardService.DeleteCard(id);

        return true;
    }



}
