package com.springweb.web.dto;

import com.springweb.domain.board.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    //필드
    private Long bbsID;
    private String bbsTitle;
    private String bbsCategory;
    private String bbsContent;
    private String bbsReply;
    private String userID;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    //엔티티 빌더 메소드 : dto -> entity
    public BoardEntity boardEntity(){

        return BoardEntity.builder().bbsTitle(bbsTitle).bbsCategory(bbsCategory).bbsContent(bbsContent).bbsReply(bbsReply).userID(userID).build();

    }


    //생성자
    @Builder
    public BoardDto(Long bbsID, String bbsTitle, String bbsCategory, String bbsContent, String bbsReply, String userID, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.bbsID=bbsID;
        this.bbsTitle = bbsTitle;
        this.bbsCategory = bbsCategory;
        this.bbsContent = bbsContent;
        this.bbsReply = bbsReply;
        this.userID = userID;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
