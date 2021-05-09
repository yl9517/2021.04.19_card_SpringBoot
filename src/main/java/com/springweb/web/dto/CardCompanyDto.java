package com.springweb.web.dto;

import com.springweb.domain.card.CardCompanyEntity;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardCompanyDto {
    //빌드
    private Long companyCode; //회사코드
    private String companyName; //카드사
    private String companyLogo; //회사로고
    private int haveCardNum; //등록카드 갯수

    //생성자
    @Builder
    public CardCompanyDto(Long companyCode, String companyName, String companyLogo, int haveCardNum) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.haveCardNum = haveCardNum;
    }

    public CardCompanyEntity toEntity(){
        return CardCompanyEntity.builder()
                .companyCode(companyCode)
                .companyName(companyName)
                .companyLogo(companyLogo)
                .haveCardNum(haveCardNum)
                .build();
    }



}
