package com.springweb.domain.card;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardCode; //카드 코드

    @Column (nullable = true) //잠시 true
    private String cardName; //카드 명

    @Column(columnDefinition = "TEXT")
    private String cardPhoto; //카드사진

    @Column (nullable = true)
    private String cardCompany; //카드사

    @Column (nullable = true)
    private int annualFee; //연회비

    @Column (nullable = true)
    private int beforePay; //전월 실적

    @Column (nullable = true)
    private String cardType; //카드 타입

    @Column (nullable = true)
    private String benefit1; //혜택1

    @Column (columnDefinition = "TEXT", nullable = true)
    private String benefit1_detail; //혜택1 상세

    @Column (nullable = true)
    private String benefit2; //혜택2

    @Column (columnDefinition = "TEXT", nullable = true)
    private String benefit2_detail; //혜택2 상세

    @Column (nullable = true)
    private String benefit3; //혜택3

    @Column (columnDefinition = "TEXT", nullable = true)
    private String benefit3_detail; //혜택3 상세

    @Column (nullable = true)
    private String cardLink; //카드사 링크

    @Column
    private int count; //순위에 쓰일 카운트

    @Builder
    public CardEntity(Long cardCode, String cardName, String cardPhoto, String cardCompany, int annualFee, int beforePay, String cardType, String benefit1, String benefit1_detail, String benefit2, String benefit2_detail, String benefit3, String benefit3_detail, String cardLink,int count) {
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.cardPhoto = cardPhoto;
        this.cardCompany = cardCompany;
        this.annualFee = annualFee;
        this.beforePay = beforePay;
        this.cardType = cardType;
        this.benefit1 = benefit1;
        this.benefit1_detail = benefit1_detail;
        this.benefit2 = benefit2;
        this.benefit2_detail = benefit2_detail;
        this.benefit3 = benefit3;
        this.benefit3_detail = benefit3_detail;
        this.cardLink = cardLink;
        this.count=count;
    }

    //수정
    public CardEntity update(String cardName, String cardPhoto, String cardCompany, int annualFee, int beforePay, String cardType, String benefit1, String benefit1_detail, String benefit2, String benefit2_detail, String benefit3, String benefit3_detail, String cardLink) {
        this.cardName = cardName;
        this.cardPhoto = cardPhoto;
        this.cardCompany = cardCompany;
        this.annualFee = annualFee;
        this.beforePay = beforePay;
        this.cardType = cardType;
        this.benefit1 = benefit1;
        this.benefit1_detail = benefit1_detail;
        this.benefit2 = benefit2;
        this.benefit2_detail = benefit2_detail;
        this.benefit3 = benefit3;
        this.benefit3_detail = benefit3_detail;
        this.cardLink = cardLink;
        return this;
    }

    //버튼 누를때마다? 카운트 올라가기
    public void countUp(){
        this.count++;
    }



}
