package com.springweb.web;

import com.springweb.service.CardCompanyService;
import com.springweb.service.CardService;
import com.springweb.web.dto.CardCompanyDto;
import com.springweb.web.dto.CardCompanyUpdateDto;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.smartcardio.Card;
import java.io.File;
import java.util.List;

//여름 이미지폴더 경로 -> C:\\Users\\yl951\\IdeaProjects\\springProject\\src\\main\\resources\\static\\images"
@Controller
@AllArgsConstructor //빈 생성자
public class AdminController {

    private final CardService cardService;
    private final CardCompanyService cardCompanyService;

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
            cardDto.setBenefit1(request.getParameter("benefit1"));
            cardDto.setBenefit1_detail(request.getParameter("benefit1_detail"));
            cardDto.setBenefit2(request.getParameter("benefit2"));
            cardDto.setBenefit2_detail(request.getParameter("benefit2_detail"));
            cardDto.setBenefit3(request.getParameter("benefit3"));
            cardDto.setBenefit3_detail(request.getParameter("benefit3_detail"));
            cardDto.setCardLink(request.getParameter("cardLink"));
            cardDto.setCount(0);


            cardService.SaveCard(cardDto);
            cardCompanyService.addCard(request.getParameter("cardCompany")); //해당 회사 등록카드 +1

            return "redirect:/admin";//다 쓰면 리스트로 돌리기

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/admin"; //다 쓰면 리스트로 돌리기
    }

    //카드 수정페이지 이동
    @GetMapping("/card_update_page/{id}")
    public String card_update_page(@PathVariable("id") Long id,Model model){
        //카드코드 넣어서 해당 카드 찾기
        CardDto cardDto = cardService.getCard(id);

        //카드에게 넘겨주기
        model.addAttribute("cardDto", cardDto);

        return "admin_CardUpdate";
    }

    //카드 수정 기능
    //@ResponseBody -> 스크립트에서 끌어올때
    //@PutMapping("/admin/card_update") // requestMapping에서 썼으므로 put안써도됨  --> requestMapping는 기본
    @RequestMapping("/admin/card_update")
    public String update(@RequestParam("cardPhoto") MultipartFile files, HttpServletRequest request){ /* ajax로 받을때 requestbody로 받음*/
        //멀티파트로 받아온게 아니면 CardDto로 받아올 수 있음!!!
        try{
            String baseDir="C:\\Users\\yl951\\IdeaProjects\\springProject\\src\\main\\resources\\static\\images"; //파일이 저장되는 위치
            String filePath = baseDir+"\\"+files.getOriginalFilename(); //파일이 저장되는 위치 +파일명
            files.transferTo(new File(filePath));       //파일 저장!!!

            CardUpdateDto cardDto = new CardUpdateDto();

            Long id = Long.valueOf(request.getParameter(("cardCode")));

            cardDto.setCardPhoto(files.getOriginalFilename());
            cardDto.setCardName( request.getParameter("cardName"));
            cardDto.setCardCompany(request.getParameter("cardCompany"));
            cardDto.setAnnualFee( Integer.parseInt(request.getParameter("annualFee")));
            cardDto.setBeforePay(Integer.parseInt(request.getParameter("beforePay")));
            cardDto.setCardType(request.getParameter("cardType"));
            cardDto.setBenefit1("benefit1");
            cardDto.setBenefit1_detail("benefit1_detail");
            cardDto.setBenefit2("benefit2");
            cardDto.setBenefit2_detail("benefit2_detail");
            cardDto.setBenefit3("benefit3");
            cardDto.setBenefit3_detail("benefit3_detail");
            cardDto.setCardLink(request.getParameter("cardLink"));

            cardService.UpdateCard(id, cardDto);

            return "redirect:/admin"; //다 쓰면 리스트로 돌리기

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/admin"; //다 쓰면 리스트로 돌리기
        }

    //카드 삭제 기능
    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id){
        cardService.DeleteCard(id);

        return true;
    }



    //카드사

    //카드사 목록
    @GetMapping("/admin/cardCompany_list_page") // http 주소 설정
    public String companyListPage(Model model){

        List<CardCompanyDto> cardCompanylist = cardCompanyService.getAllCardCompany();
        model.addAttribute("companylist",cardCompanylist);

        return "admin_CardCompanyList"; //card_chart.html 연결 (헤더의 해당 링크에 html 연결, th로 http 주소 연결)
    }

    //카드사 등록페이지 이동
    @GetMapping("/admin/company_regist_page")
    public String companyRegist_page(){
        return "admin_CardCompanyRegister";

    }
    //카드사 등록
    @PostMapping("/admin/company_register")
    public String companyRegister(@RequestParam("companyLogo") MultipartFile files, HttpServletRequest request){
        try{
            String baseDir="C:\\Users\\yl951\\IdeaProjects\\springProject\\src\\main\\resources\\static\\images"; //파일이 저장되는 위치
            String filePath = baseDir+"\\"+files.getOriginalFilename(); //파일이 저장되는 위치 +파일명
            files.transferTo(new File(filePath));       //파일 저장!!!

            CardCompanyDto companyDto = new CardCompanyDto();

            companyDto.setCompanyLogo(files.getOriginalFilename());
            companyDto.setCompanyName(request.getParameter("companyName"));
            companyDto.setHaveCardNum(0);

            cardCompanyService.SaveCardCompany(companyDto);
            return "redirect:/admin/cardCompany_list_page";//다 쓰면 리스트로 돌리기

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/admin/cardCompany_list_page"; //다 쓰면 리스트로 돌리기
    }

    //카드사 수정페이지 이동
    @GetMapping("/company_update_page/{id}")
    public String company_update_page(@PathVariable("id") Long id,Model model){
        //카드사코드 넣어서 해당 카드 찾기
        CardCompanyDto companyDto = cardCompanyService.getCardCompany(id);

        //카드에게 넘겨주기
        model.addAttribute("companyDto", companyDto);

        return "admin_CardCompanyUpdate";
    }

    //카드사 수정
    @RequestMapping("/admin/company_update")
    public String companyUpdate(@RequestParam("cardPhoto") MultipartFile files, HttpServletRequest request){
        //멀티파트로 받아온게 아니면 CardDto로 받아올 수 있음!!!
        try{
            String baseDir="C:\\Users\\yl951\\IdeaProjects\\springProject\\src\\main\\resources\\static\\images"; //파일이 저장되는 위치
            String filePath = baseDir+"\\"+files.getOriginalFilename(); //파일이 저장되는 위치 +파일명
            files.transferTo(new File(filePath));       //파일 저장!!!

            CardCompanyUpdateDto companyDto = new CardCompanyUpdateDto();

            Long id = Long.valueOf(request.getParameter("companyCode"));
            companyDto.setCompanyLogo(files.getOriginalFilename());
            companyDto.setCompanyName(request.getParameter("companyName"));

            cardCompanyService.UpdateCardCompany(id, companyDto);

            return "redirect:/admin/cardCompany_list_page";//다 쓰면 리스트로 돌리기

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/admin/cardCompany_list_page"; //다 쓰면 리스트로 돌리기
    }
    //카드사 삭제
    @DeleteMapping("/admin/companyDelete/{id}")
    @ResponseBody
    public boolean companyDelete(@PathVariable("id") Long id){
        cardCompanyService.DeleteCardCompany(id);
        return true;
    }
}
