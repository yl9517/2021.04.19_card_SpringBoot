package com.springweb.web;


import com.springweb.domain.board.BoardEntity;
import com.springweb.service.BoardService;
import com.springweb.service.OauthService;
import com.springweb.web.dto.BoardDto;
import com.springweb.web.dto.SesstionUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession httpSession;



    // @GetMapping : 이동 페이지 url 설정
    // @DeleteMapping : 삭제 url
    // @PostMapping : 쓰기 url
    // @PutMapping : 수정 url

    // 게시판 페이지로 이동
    @GetMapping("/boardlist_page")
    public String boardlist_page( Model model){

        List<BoardDto> postList = boardService.getAllBoard();
        model.addAttribute("postList", postList);


        return "boardlist";
    }

    // 쓰기 페이지로 이동
    @GetMapping("/boardwrite_page")
    public String boardwrite_page(){
        return "board_write";
    }

    // 개별 게시물 이동 페이지
    @GetMapping("/board_detail_page/{id}")
    public String board_sangsebogi_page(@PathVariable("id") Long id, Model model ){


        SesstionUser user = (SesstionUser) httpSession.getAttribute("user");

        if(user !=null){
            model.addAttribute("user", user);
        }else{

            model.addAttribute("user", null);
        }

        // 해당 게시물 번호를 찾기
        BoardDto boardDto = boardService.getBoard(id);
//        // 찾았으면 model 담아서 프론트엔드에게 전달
        model.addAttribute("postDto" , boardDto);
        // html 페이지 열기
        return "board_detail";
    }

    //    // 수정 게시물 이동 페이지
//    @GetMapping
//
//    // 쓰기 기능 url
//    @PostMapping
//
//    // 수정 기능 url
//    @PutMapping
//
//    // 삭제 기능 url
//    @DeleteMapping
//
//
//게시판 글쓰기 기능
    @PostMapping("/post")
    public String write(BoardDto boardDto) {

        SesstionUser user = (SesstionUser) httpSession.getAttribute("user");

        if(boardDto.getBbsTitle()==null || boardDto.getBbsContent()==null ) {
            return "redirect:/error";
        } else {


            boardDto.setUserID( user.getName() );
            boardService.SaveBoard(boardDto);

            return "redirect:/boardlist_page";
        }
    }

    //게시글 삭제처리
    @DeleteMapping("/post/delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {

        boardService.DeleteBoard(id);

        return true;
    }









}
