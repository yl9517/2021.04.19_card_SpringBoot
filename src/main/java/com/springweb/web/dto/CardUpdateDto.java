package com.springweb.web.dto;

import com.springweb.domain.card.CardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardUpdateDto {
    //필드
    private String cardName; //카드 명
    private String cardPhoto; //카드사진
    private String cardCompany; //카드사
    private int annualFee; //연회비
    private int beforePay; //전월 실적
    private String cardType; //카드 타입
    private String benefit1; //혜택1
    private String benefit1_detail; //혜택1 상세
    private String benefit2; //혜택2
    private String benefit2_detail; //혜택2 상세
    private String benefit3; //혜택3
    private String benefit3_detail; //혜택3 상세
    private String cardLink; //카드사 링크

    //생성자
    @Builder
    public CardUpdateDto(String cardName, String cardPhoto, String cardCompany, int annualFee, int beforePay, String cardType, String benefit1, String benefit1_detail, String benefit2, String benefit2_detail, String benefit3, String benefit3_detail, String cardLink) {
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
    }

}
