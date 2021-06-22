package com.springweb.domain.board;


import com.springweb.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name="reply")
@NoArgsConstructor
@Table(name="reply")
public class ReplyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name = "reply_writer",columnDefinition = "TEXT", nullable = false
    )
    private String reply_writer;

    @Column(name="reply_content",length = 300, nullable = false)
    private String reply_content;

    @ManyToOne
    @JoinColumn(name = "bbsID",nullable = false)
    private BoardEntity bbsID;


    @Builder
    public ReplyEntity(Long id, String reply_writer, String reply_content, BoardEntity bbsID) {
        this.id = id;
        this.reply_writer = reply_writer;
        this.reply_content = reply_content;
        this.bbsID = bbsID;
    }

    public void update(String reply_content){

        this.reply_content=reply_content;
    }

}
