package com.thehecklers.jpatest.controller;

import com.thehecklers.jpatest.domain.Memo;
import com.thehecklers.jpatest.repository.MemoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/memos")
public class RestMemoApiController {

    // JPA 저장소 객체 불러오기
    private final MemoRepository memoRepository;


    public RestMemoApiController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping
    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Memo> findById(@PathVariable Long id) {
        return memoRepository.findById(id);
    }

    // create post 메서드, update put 메서드, delete delete 메서드 구현

    // create 구현
    @PostMapping
    public Memo postMemo(@RequestBody Memo memo) {
        return memoRepository.save(memo);
    }

    // update 구현
    @PutMapping("/{id}")
    ResponseEntity<Memo> putMemo(@PathVariable Long id, @RequestBody Memo memo) {
        return (memoRepository.existsById(id))
                ? new ResponseEntity<>(memoRepository.save(memo), HttpStatus.OK)
                : new ResponseEntity<>(memoRepository.save(memo), HttpStatus.CREATED) ;
    }
    
    // delete 구현
    @DeleteMapping("/{id}")
    public ResponseEntity<Memo> deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return memoRepository.existsById(id)
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK);
    }
    
    // 페이징 및 정렬
    @GetMapping("/{page}/{pagePerCount}")
    public List<Memo> findByPage(@PathVariable int page, @PathVariable int pagePerCount) {
        // 페이징 처리를 위한 객체
        // pageNumber는 0부터 시작
        Pageable pageable = PageRequest.of(page-1, pagePerCount);
        Page<Memo> result = memoRepository.findAll(pageable);
        // Total page
        System.out.println("Total page: "+result.getTotalPages());
        // Total Elements
        System.out.println("Total Elements: "+result.getTotalElements());
        // 현재 페이지 번호
        System.out.println("This Page Number: "+result.getNumber());
        // 페이지당 데이터의 갯수
        System.out.println("PagePerCount: "+result.getSize());
        // 다음 페이지 존재 여부
        System.out.println("HasNext Page?: "+result.hasNext());
        // 이전 페이지 존재 여부
        System.out.println("HasPervious Page?: "+result.hasPrevious());
        // 현재 시작 페이지 인지
        System.out.println("Is First Page?: "+result.isFirst());
        // getContent()는 쿼리 결과를 저정하고 있음. 타입은 List
        return result.getContent();
    }

    @GetMapping("/{page}/{pagePerCount}/{sort}")    // sort는 Column 지정
    public List<Memo> findByPageAndSort(
            @PathVariable int page,
            @PathVariable int pagePerCount,
            @PathVariable String sort) {
        // Sort 객체 생성
        Sort sort1 = Sort.by(sort).descending();    // descending() 내림차순, ascending() 오름차순(default)
        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page-1, pagePerCount, sort1);
        Page<Memo> result = memoRepository.findAll(pageable);
        return result.getContent();
    }

    @GetMapping("/search/{start}/{end}")
    public List<Memo> findBySearch(@PathVariable long start, @PathVariable long end) {
        // findByMnoBetween
        Page<Memo> result= memoRepository.findByMnoBetween(start,end,PageRequest.of(1,10));
        return result.getContent();
    }
    @GetMapping("/search2/{mno}")
    public List<Memo> findBySearch2(@PathVariable long mno) {
        // findByMnoBetween
        List<Memo> result = memoRepository.findByMno(mno);
        return result;
    }

    



}
