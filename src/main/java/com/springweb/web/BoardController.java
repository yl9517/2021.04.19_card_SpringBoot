package com.springweb.web;


import com.springweb.domain.board.BoardEntity;
import com.springweb.service.BoardService;
import com.springweb.service.OauthService;
import com.springweb.web.dto.BoardDto;
import com.springweb.web.dto.BoardUpdateRequestDto;
import com.springweb.web.dto.ReplyDto;
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
        // 찾았으면 model 담아서 프론트엔드에게 전달
        model.addAttribute("postDto" , boardDto);
        // html 페이지 열기
        return "board_detail";
    }

    // 수정 게시물 이동 페이지
    @GetMapping("/post/update/{id}")
    public String board_update(@PathVariable("id")Long id, Model model){

        //게시물 번호를 넣어서 해당 게시물찾기
        BoardDto boardDto = boardService.getBoard(id);

        //찾았으면 페이지에게 넘겨주기
        model.addAttribute("postDto",boardDto);

        return "board_update";


    }



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


    //게시글 수정 실제처리

    @PutMapping("/post/update/{id}") //여기의 id는 매개변수이다.
    @ResponseBody
    public void update(@PathVariable("id") Long id, @RequestBody BoardUpdateRequestDto requestDto) {

        boardService.UpdateBoard(id, requestDto);


    }

    //답장 등록 기능

    //답장 삭제

    //답장 업뎃

    //답장 조회 기능



    //관리자-답장등록기능
    @PostMapping("/post/reply/{bbsID}")
    @ResponseBody
    public Boolean reply_function(@PathVariable("bbsID") Long bbsID,  @RequestBody ReplyDto replyDto) {

        System.out.println(":::"+bbsID);
        System.out.println(":::"+replyDto.toString());


        replyDto.setReply_writer("ADMIN");
        return boardService.save_reply(bbsID, replyDto) >= 0L;


    }






}
