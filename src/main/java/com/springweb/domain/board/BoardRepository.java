package com.springweb.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Query(value = "SELECT * FROM board WHERE userID = ?1", nativeQuery = true)
    List<BoardEntity> findByuserID(String userID);

}
