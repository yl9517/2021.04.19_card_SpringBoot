package com.springweb.web.dto;

import com.springweb.domain.board.boardEntity;
import com.springweb.domain.board.ReplyEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyDto {


    private Long id;
    private String reply_writer;
    private String reply_content;
    private boardEntity bbsID;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    //엔티티 빌더 메소드
    public ReplyEntity replyEntity(){
        return ReplyEntity.builder()
                .reply_content(reply_content)
                .reply_writer(reply_writer)
                .bbsID(bbsID)
                .build();


    }



    //생성자
    public ReplyDto(Long id, String reply_writer, boardEntity bbsID, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.reply_writer = reply_writer;
        this.bbsID = bbsID;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }



}