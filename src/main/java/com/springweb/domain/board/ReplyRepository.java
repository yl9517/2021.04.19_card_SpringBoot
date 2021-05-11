package com.springweb.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    @Query(value = "SELECT * FROM reply WHERE bbsID = ?1", nativeQuery = true)
    Optional<ReplyEntity> findBybbsID(Long aLong);

}
