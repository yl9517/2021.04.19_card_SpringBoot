package com.springweb.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BoardUpdateRequestDto {

    private String bbsTitle;
    private String bbsContent;


    public BoardUpdateRequestDto(String bbsTitle, String bbsContent){

        this.bbsTitle=bbsTitle;
        this.bbsContent=bbsContent;

    }



}
