package com.springweb.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardCompanyRepository extends JpaRepository<CardCompanyEntity,Long> {

    @Query(value = "SELECT * FROM CARDCOMPANY WHERE companyName = ?1")
    Optional<CardCompanyEntity> findByCardName(String companyName);

}
