package com.springweb.web;


import com.springweb.service.BoardService;
import com.springweb.web.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;


    // @GetMapping : 이동 페이지 url 설정
    // @DeleteMapping : 삭제 url
    // @PostMapping : 쓰기 url
    // @PutMapping : 수정 url

    // 게시판 페이지로 이동
    @GetMapping("/board/boardlist_page")
    public String boardlist_page(){
        return "boardlist";
    }

    // 쓰기 페이지로 이동
    @GetMapping("/board/boardwrite_page")
    public String boardwrite_page(){
        return "board_write";
    }

    // 개별 게시물 이동 페이지
    @GetMapping("/board/board_detail_page")
    public String board_sangsebogi_page( ){
        // 해당 게시물 번호를 찾기
//        BoardDto boardDto = boardService.getBoard(id);
//        // 찾았으면 model 담아서 프론트엔드에게 전달
//        model.addAttribute("boardDto" , boardDto);
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










}
