package com.springweb.domain.card;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="cardCompany")
@Table(name="cardCompany")
public class CardCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyCode", nullable = false)
    private Long companyCode; //회사코드

    @Column(name = "companyName",nullable = false)
    private String companyName; //카드사

    @Column (name = "companyLogo",nullable = false)
    private String companyLogo; //회사로고

    @Column (name = "haveCardNum",nullable = false)
    private int haveCardNum; //등록카드 갯수

    @Builder
    public CardCompanyEntity(Long companyCode, String companyName, String companyLogo, int haveCardNum) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.haveCardNum = haveCardNum;
    }

    //수정
    public CardCompanyEntity update(String companyName, String companyLogo){
        this.companyName=companyName;
        this.companyLogo=companyLogo;

        return this;
    }

    //카드 등록할때마다 등록카드갯수 올라가기
    public void addCard(){
        this.haveCardNum++;
    }



}
