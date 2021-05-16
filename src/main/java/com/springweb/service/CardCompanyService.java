package com.springweb.service;

import com.springweb.domain.card.CardCompanyEntity;
import com.springweb.domain.card.CardCompanyRepository;
import com.springweb.web.dto.CardCompanyDto;
import com.springweb.web.dto.CardCompanyUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardCompanyService {


    private final CardCompanyRepository cardCompanyRepository;

    //생성
    @Transactional
    public Long SaveCardCompany(CardCompanyDto cardCompanyDto){
        return cardCompanyRepository.save(cardCompanyDto.toEntity()).getCompanyCode();
    }

    //수정
    @Transactional
    public Long UpdateCardCompany(Long code, CardCompanyUpdateDto requestDto){

        //수정 할 대상찾기 [카드코드, 업데이트 내용]
        Optional<CardCompanyEntity> cardCompanyOptional = cardCompanyRepository.findById(code);
        CardCompanyEntity cardCompanyEntity = cardCompanyOptional.get();

        //찾았으면 업데이트
        cardCompanyEntity.update(requestDto.getCompanyName(),requestDto.getCompanyLogo());
        return code;
    }
    //삭제
    @Transactional
    public void DeleteCardCompany(Long code){

        //삭제할 대상 찾기
        Optional<CardCompanyEntity> cardCompanyOptional = cardCompanyRepository.findById(code);
        CardCompanyEntity cardCompanyEntity = cardCompanyOptional.get();

        //찾았으면 삭제
        cardCompanyRepository.delete(cardCompanyEntity);

    }
    @Transactional
    //전체조회
    public List<CardCompanyDto> getAllCardCompany(){

        //1. 모든 엔티티 가져오기
        List<CardCompanyEntity> cardCompanyEntities = cardCompanyRepository.findAll();

        //2. 가져온 엔티티 뺴오기
        List<CardCompanyDto> cardCompanyList = new ArrayList<>();

        for(CardCompanyEntity entity : cardCompanyEntities){ //가져온 엔티티만큼 반복
            CardCompanyDto cardCompanyDto = CardCompanyDto.builder()
                    .companyCode(entity.getCompanyCode())
                    .companyLogo(entity.getCompanyLogo())
                    .companyName(entity.getCompanyName())
                    .build();

            cardCompanyList.add(cardCompanyDto);
        }
        return cardCompanyList;

    }
    //개별조회
    @Transactional
    public CardCompanyDto getCardCompany(Long code) {
        //검색할 대상 찾기
        Optional<CardCompanyEntity> companyEntityOptional = cardCompanyRepository.findById(code);
        CardCompanyEntity cardCompanyEntity = companyEntityOptional.get();

        CardCompanyDto cardCompanyDto = CardCompanyDto.builder()
                .companyCode(cardCompanyEntity.getCompanyCode())
                .companyLogo(cardCompanyEntity.getCompanyLogo())
                .companyName(cardCompanyEntity.getCompanyName())
                .build();
        return cardCompanyDto;
    }

    //해당카드사의 등록카드 갯수 수정
    @Transactional
    public String addCard(String name){
        //수정할 대상 찾기
        Optional<CardCompanyEntity> companyEntityOptional = cardCompanyRepository.findByCardName(name);
        CardCompanyEntity cardCompanyEntity = companyEntityOptional.get();

        //찾았으면 업데이트
        cardCompanyEntity.addCard();

        return name;
    }

}
