package com.springweb.domain.card;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="cardCompany")
public class CardCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyCord; //회사코드

    @Column(nullable = false)
    private String companyName; //카드사

    @Column (nullable = false)
    private String companyLogo; //회사로고

    @Column (nullable = false)
    private int haveCardNum; //등록카드 갯수

    @Builder
    public CardCompanyEntity(int companyCord, String companyName, String companyLogo, int haveCardNum) {
        this.companyCord = companyCord;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.haveCardNum = haveCardNum;
    }

    public CardCompanyEntity update(String companyName, String companyLogo, int haveCardNum){
        this.companyName=companyName;
        this.companyLogo=companyLogo;
        this.haveCardNum=haveCardNum;

        return this;
    }


}
