package com.thehecklers.jpatest.repository;

import com.thehecklers.jpatest.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
    CRUD 작업
        - insert, update 작업 : save(Entity 객체)
        - select 작업 : findById(), findAll()
        - 데이터의 갯수 : count()
        - delete 작업 : deleteById(키타입), delete(Entity 객체)
 */

public interface MemoRepository extends JpaRepository<Memo, Long> {
//    List<Memo> findByMnoAndMemoText(Long mno, String memoText);
    Page<Memo> findByMnoBetween(Long mnoAfter, Long mnoBefore, Pageable pageable);

    @Query("select m from Memo m where m.mno >= ?1")
    List<Memo> findByMno(Long mno);

}
