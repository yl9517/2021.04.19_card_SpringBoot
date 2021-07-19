package com.springweb.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardCompanyRepository extends JpaRepository<CardCompanyEntity,Long> {
 //해당 카드사의 등록된 카드 갯수
   @Query(value = "SELECT * FROM CARD_COMPANY WHERE COMPANY_NAME = ?1", nativeQuery = true)
    Optional<CardCompanyEntity> findByCardName(String companyName);

}
