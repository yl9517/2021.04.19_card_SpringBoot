package com.springweb.service;


import com.springweb.domain.card.CardEntity;
import com.springweb.domain.card.CardRepository;
import com.springweb.web.dto.CardDto;
import com.springweb.web.dto.CardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.smartcardio.Card;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;

    //생성
    @Transactional
    public Long SaveCard(CardDto cardDto){

        return cardRepository.save(cardDto.toEntity()).getCardCode();
    }

    //수정
    @Transactional
    public Long UpdateCard(Long code, CardUpdateDto requestDto){ //카드코드와 수정할 값 받아오기

        //수정할 대상 찾기 [카드코드, 업데이트 내용]
        Optional<CardEntity> cardEntityOptional = cardRepository.findById(code);
        CardEntity cardEntity = cardEntityOptional.get();
            //못찾았으면 null 예외처리 (Optional)

        //찾았으면 업데이트
        cardEntity.update(
                requestDto.getCardName(),requestDto.getCardPhoto()
                ,requestDto.getCardCompany(),requestDto.getAnnualFee()
                ,requestDto.getBeforePay(),requestDto.getCardType()
                ,requestDto.getBenefit1(),requestDto.getBenefit1_detail()
                ,requestDto.getBenefit2(),requestDto.getBenefit2_detail()
                ,requestDto.getBenefit3(),requestDto.getBenefit3_detail()
                ,requestDto.getCardLink());
        return code;
    }
    //Optional 쓰는 이유 : 널값으로 인해 발생하는 에러를 예외처리를 해줌


    //카드 링크 카운트 수정
    @Transactional
    public Long UpdateCount(Long code){

        //수정할 대상찾기
        Optional<CardEntity> cardEntityOptional = cardRepository.findById(code);
        CardEntity cardEntity = cardEntityOptional.get();

        //찾았으면 업데이트
        cardEntity.countUp();

        return code;

    }

    //삭제
    @Transactional
    public void DeleteCard(Long code) {

        //삭제할 대상 찾기
        Optional<CardEntity> cardEntityOptional = cardRepository.findById(code);
        CardEntity cardEntity = cardEntityOptional.get();

        //찾았으면 삭제
        cardRepository.delete(cardEntity);

    }

    //전체조회 (관리자)
    @Transactional
    public List<CardDto> getAllCard(){

        //1. 모든 엔티티 가져오기
        List<CardEntity> cardEntities = cardRepository.findAll();
            //정렬할거면 findAll(Sort.by(Sort.Direction.DESC,"count"));로 넣기
            //페이지로 잘라서 출력할거면 findAll(Pageable

        //2. 가져온 엔티티 빼오기
        List<CardDto> cardDtoList = new ArrayList<>();

        for(CardEntity entity : cardEntities){ //모든(가져온) 엔티티 만큼 반복
            CardDto cardDto = CardDto.builder()
                    .cardCode(entity.getCardCode())
                    .cardName(entity.getCardName())
                    .cardPhoto(entity.getCardPhoto())
                    .cardCompany(entity.getCardCompany())
                    .annualFee(entity.getAnnualFee())
                    .beforePay(entity.getBeforePay())
                    .cardType(entity.getCardType())
                    .benefit1(entity.getBenefit1())
                    .benefit1_detail(entity.getBenefit1_detail())
                    .benefit2(entity.getBenefit2())
                    .benefit2_detail(entity.getBenefit2_detail())
                    .benefit3(entity.getBenefit3())
                    .benefit3_detail(entity.getBenefit3_detail())
                    .cardLink(entity.getCardLink())
                    .build();
            cardDtoList.add(cardDto);

        }
        return cardDtoList;
    }

    //랜덤 카드조회 (메인)
    @Transactional
    public List<CardDto> getMainCard(){
        List<CardEntity> cardEntities = cardRepository.findAll();
        Collections.shuffle(cardEntities); //카드 값들을 랜덤으로 순서 재배치

        List<CardDto> mainCardList = new ArrayList<>();
        for(int i=0; i<cardEntities.size(); i++){ //사이즈만큼 반복

            if(i==3) break; //3이면 끝내기

            CardEntity entity = cardEntities.get(i);
            CardDto cardDto = CardDto.builder()
                    .cardCode(entity.getCardCode())
                    .cardName(entity.getCardName())
                    .cardPhoto(entity.getCardPhoto())
                    .cardCompany(entity.getCardCompany())
                    .annualFee(entity.getAnnualFee())
                    .beforePay(entity.getBeforePay())
                    .cardType(entity.getCardType())
                    .benefit1(entity.getBenefit1())
                    .benefit1_detail(entity.getBenefit1_detail())
                    .benefit2(entity.getBenefit2())
                    .benefit2_detail(entity.getBenefit2_detail())
                    .benefit3(entity.getBenefit3())
                    .benefit3_detail(entity.getBenefit3_detail())
                    .cardLink(entity.getCardLink())
                    .build();
            mainCardList.add(cardDto);

        }
        return mainCardList;
    }

    //조건 전체조회 (차트순위 별)
    public List<CardDto> getRankCard(){
        List<CardEntity> cardEntities = cardRepository.findAll(Sort.by(Sort.Direction.DESC,"count"));

        //가져온 엔티티 빼오기
        List<CardDto> cardRankList = new ArrayList<>();

        //  for (CardEntity entity : cardEntities) {
        for(int i=0; i<cardEntities.size(); i++){ //사이즈만큼 반복

            if(i==10) break; //10이면 끝내기

            CardEntity entity = cardEntities.get(i);
            CardDto cardDto = CardDto.builder()
                    .cardCode(entity.getCardCode())
                    .cardName(entity.getCardName())
                    .cardPhoto(entity.getCardPhoto())
                    .cardCompany(entity.getCardCompany())
                    .annualFee(entity.getAnnualFee())
                    .beforePay(entity.getBeforePay())
                    .cardType(entity.getCardType())
                    .benefit1(entity.getBenefit1())
                    .benefit1_detail(entity.getBenefit1_detail())
                    .benefit2(entity.getBenefit2())
                    .benefit2_detail(entity.getBenefit2_detail())
                    .benefit3(entity.getBenefit3())
                    .benefit3_detail(entity.getBenefit3_detail())
                    .cardLink(entity.getCardLink())
                    .build();
            cardRankList.add(cardDto);

        }
        return cardRankList;

    }

    //개별조회(조건조회)
    @Transactional
    public CardDto getCard(Long code){
        //검색할 대상 찾기
        Optional<CardEntity> cardEntityOptional = cardRepository.findById(code);
        CardEntity cardEntity = cardEntityOptional.get();

        CardDto cardDto = CardDto.builder()
                .cardCode(cardEntity.getCardCode())
                .cardName(cardEntity.getCardName())
                .cardPhoto(cardEntity.getCardPhoto())
                .cardCompany(cardEntity.getCardCompany())
                .annualFee(cardEntity.getAnnualFee())
                .beforePay(cardEntity.getBeforePay())
                .cardType(cardEntity.getCardType())
                .benefit1(cardEntity.getBenefit1())
                .benefit1_detail(cardEntity.getBenefit1_detail())
                .benefit2(cardEntity.getBenefit2())
                .benefit2_detail(cardEntity.getBenefit2_detail())
                .benefit3(cardEntity.getBenefit3())
                .benefit3_detail(cardEntity.getBenefit3_detail())
                .cardLink(cardEntity.getCardLink())
                .build();

        return cardDto;
    }
}
