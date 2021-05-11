package com.springweb.web.dto;

import com.springweb.domain.board.BoardEntity;
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
    private BoardEntity bbsID;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    //엔티티 빌더 메소드
    public ReplyEntity toEntity(){
        return ReplyEntity.builder()
                .reply_content(reply_content)
                .reply_writer(reply_writer)
                .bbsID(bbsID)
                .build();


    }



    //생성자
    @Builder
    public ReplyDto(Long id, String reply_writer, BoardEntity bbsID, String reply_content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.reply_writer = reply_writer;
        this.reply_content=reply_content;
        this.bbsID = bbsID;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }



}
