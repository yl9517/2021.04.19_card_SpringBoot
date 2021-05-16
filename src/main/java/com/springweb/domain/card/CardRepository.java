package com.springweb.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<CardEntity,Long> {

    // 직접 sql문을 작성하겠다는 코드
    // @Query(value = "SELECT * FROM CARD WHERE cardCode = ?1 and cardCompany = ?2")
    //  Optional<어쩌구Entity> findBycardId(Long 코드, String 회사이름);

}
