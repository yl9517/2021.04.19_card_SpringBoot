package com.springweb.service;

import com.springweb.domain.card.CardEntity;
import com.springweb.web.dto.BoardUpdateRequestDto;
import com.springweb.web.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.springweb.domain.board.*;
import com.springweb.web.dto.BoardDto;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    //생성
    @Transactional
    public Long SaveBoard(  BoardDto boardDto  ){
        return boardRepository.save( boardDto.boardEntity()).getBbsID();
    }

    //수정
    @Transactional
    public Long UpdateBoard(Long id , BoardUpdateRequestDto requestDto ){
        // 수정할 대상 찾기 [ 게시물 번호 , 업데이트 내용 ]
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityOptional.get();
        // 못찾았으면 null 예외처리 => optional
        // 찾았으면 업데이트
        boardEntity.update( requestDto.getBbsTitle() , requestDto.getBbsContent()  );
        return id;
    }

    //삭제
    @Transactional
    public void DeleteBoard( Long id){
        // 삭제할 대상 찾기
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityOptional.get();

        Optional<ReplyEntity> replyEntityOptional= replyRepository.findBybbsID(id);

        if(replyEntityOptional.isPresent()){

            ReplyEntity replyEntity = replyEntityOptional.get();
            replyRepository.delete(replyEntity);
            boardRepository.delete(boardEntity);

        }else{

            boardRepository.delete(boardEntity);

        }


    }



    //전체조회
    @Transactional
    public Page<BoardEntity> getAllBoard(Pageable pageable){

        int page=(pageable.getPageNumber()==0)?0:(pageable.getPageNumber()-1);
        pageable= PageRequest.of(page,5,new Sort(Sort.Direction.DESC,"bbsID"));


        return boardRepository.findAll(pageable);
    }

    //개별조회(조건조회)
    @Transactional
    public BoardDto getBoard( Long id ){
        // 검색할 대상 찾기
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityOptional.get();
        // 찾았으면 dto 담기
        BoardDto boardDto = BoardDto.builder()
                .bbsID( boardEntity.getBbsID() )
                .bbsTitle( boardEntity.getBbsTitle() )
                .bbsCategory(boardEntity.getBbsCategory())
                .bbsContent(boardEntity.getBbsContent())
                .bbsReply(boardEntity.getBbsReply())
                .userID(boardEntity.getUserID())
                .createdDate(boardEntity.getCreateDate())
                .modifiedDate(boardEntity.getModifiedDate())
                .build();
        return boardDto;
    }


    //  답글

    //1. 게시물아이디, 댓글 dto 인수로 받기
    //2. 게시물 아이디를 이용하여 해당 엔티티를 찾아 답변완료로 수정
    //3. 댓글 dto에 게시물 아이디 셋팅
    //4. 댓글 dto save


    // 답글 저장메소드
    @Transactional
    public Long save_reply(Long bbsID, ReplyDto replyDto){

        BoardEntity boardEntity=boardRepository.findById(bbsID).orElseThrow(NullPointerException::new);

        boardEntity.doneReply();
        replyDto.setBbsID(boardEntity);

        return replyRepository.save(replyDto.toEntity()).getId();
        //toEntity : 엔티티로 만들어주는 메소드

    }

    public ReplyDto getReply(Long id) {
        ReplyEntity replyEntity = replyRepository.findBybbsID(id).orElseThrow(NullPointerException::new);

        return ReplyDto.builder()
                .id(replyEntity.getId())
                .reply_content(replyEntity.getReply_content())
                .reply_writer(replyEntity.getReply_writer())
                .bbsID(replyEntity.getBbsID())
                .createdDate(replyEntity.getCreateDate())
                .modifiedDate(replyEntity.getModifiedDate())
                .build();
    }

    @Transactional
    public void deleteReply(Long reply_id){

        ReplyEntity replyEntity=replyRepository.findById(reply_id).orElseThrow(NullPointerException::new);
        BoardEntity bbsID=replyEntity.getBbsID();

        BoardEntity boardEntity=boardRepository.findById(bbsID.getBbsID()).orElseThrow(NullPointerException::new);
        boardEntity.undoReply();

        replyRepository.deleteById(reply_id);

    }

    //답글 수정
    @Transactional
    public void update_reply(Long reply_id, ReplyDto replyDto){
            Optional<ReplyEntity> optionalReplyEntity=replyRepository.findById(reply_id);
            ReplyEntity replyEntity=optionalReplyEntity.get();

            replyEntity.update(replyDto.getReply_content());


    }








    //내가 쓴 글 불러오기
    @Transactional
    public Page<BoardEntity> getMine(Pageable pageable, String userID){

        int page=(pageable.getPageNumber()==0)?0:(pageable.getPageNumber()-1);
        pageable= PageRequest.of(page,5,new Sort(Sort.Direction.DESC,"bbsID"));


        return boardRepository.findByuserID(userID, pageable);
    }



}
