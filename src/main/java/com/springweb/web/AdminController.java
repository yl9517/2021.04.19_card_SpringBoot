package com.springweb.web;

import com.springweb.service.CardService;
import com.springweb.web.dto.CardDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String register(@RequestParam("cardPhoto") MultipartFile files, CardDto cardDto){
        try{
            String baseDir="C:\\Users\\yl951\\IdeaProjects\\springProject\\Files";
            String filePath = baseDir+"\\"+files.getOriginalFilename();
            files.transferTo(new File(filePath));
            Authentication card = SecurityContextHolder.getContext().getAuthentication();
            // String registCard = card.getName();
            cardDto.setCardPhoto(filePath); //이미지 경로 db에 저장
            cardService.SaveCard(cardDto);

            return "redirect:/admin";//다 쓰면 리스트로 돌리기

        }catch (Exception e){
            e.printStackTrace();
        }

        //권한 가진 사람이 멤버라면 에러페이지로 돌리기
        return "redirect:/admin"; //다 쓰면 리스트로 돌리기
    }

    //카드 수정 기능

    //카드 삭제 기능
    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id){
        cardService.DeleteCard(id);

        return true;
    }



}