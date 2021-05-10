package com.springweb.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
public class CardCompanyUpdateDto {

    //필드
    private String companyName; //카드사
    private String companyLogo; //회사로고

    @Builder
    public CardCompanyUpdateDto(String companyName, String companyLogo) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
    }
}
