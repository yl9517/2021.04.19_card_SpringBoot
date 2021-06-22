package com.springweb.domain.board;

import com.springweb.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="board")
@Table(name = "board")
public class BoardEntity extends BaseTimeEntity {

    @Id //@Id= 기본키 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bbsID", nullable = false)
    private Long bbsID;

    @Column(name="bbsTitle", nullable = false)
    private String bbsTitle;


    @Column(name="bbsCategory", nullable = false)
    private String bbsCategory;

    @Column(name="bbsContent",nullable = false)
    private String bbsContent;

    @Column(name ="bbsReply", nullable = true)
    private String bbsReply;

    @Column(name ="userID", nullable = true)
    private String userID;

    @Builder
    public BoardEntity(Long bbsID, String bbsTitle, String bbsCategory, String bbsContent, String bbsReply, String userID){

        this.bbsID=bbsID;
        this.bbsTitle=bbsTitle;
        this.bbsCategory=bbsCategory;
        this.bbsContent=bbsContent;
        this.bbsReply=bbsReply;
        this.userID=userID;

    }

    public void update(String bbsTitle, String bbsContent){

        this.bbsTitle=bbsTitle;
        this.bbsContent=bbsContent;

    }

    public void doneReply(){

        this.bbsReply="답변 완료";
    }


    public void undoReply(){

        this.bbsReply="답변 대기중";

    }





}
