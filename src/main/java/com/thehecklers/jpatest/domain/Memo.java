package com.thehecklers.jpatest.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/*
JPA를 통해서 관리하게 되는 객체 - Entity 클래스와 Entity Object

# Entity 관련 annotation
 - @Entity : 클래스를 Entity로 선언
 - @Table : Entity와 매핑할 테이블 지정 - 생략시 이름과 동일한 테이블 매핑
 - @Id : 테이블의 기본키(Primary Key)로 사용할 속성을 지정
 - @GeneratedValue : 키 값을 생성하는 전력을 명시
 - @Column : 필드와 컬럼의 패밍
 - @Lob : BLOB, CLOB 타입을 매핑
 - @CreateTimestamp : insert시에 시간을 자동 저장
 - @UpdateTimestamp : update시에 시간을 자동 저장
 - @Enumerated : enum 타입 매핑
 - @Transient : 해당 필드는 데이터베이스에 매핑을 하지 않습니다.
 - @Temporal : 날짜 타입 매핑
 - @CreateDate : Entity가 생성되어 저장될 때 시간 자동 저장
 - @LastModifiedDate : Entity가 값을 변경할 때 시간 자동 저장

 */

@Entity
@Table(name = "tbl_memo")   // Entity와 같이 사용할 수 있는 어노테이션으로
                            // 어떤 테이블을 생성할지에 대한 정보를 담는 어노테이션
public class Memo {

    // field
    @Id     // primary key 설정
    // auto_increment 사용하는 경우
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Sequence 생성
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceGeneratorName")
    // @SequenceGenerator(sequenceName = "SequenceName", name = "SequenceGeneratorName", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;


    // @Column : 테이블의 컬럼과 매핑하기 위한 annotationㅇ
    // 생략하면 동일한 이름의 컬럼을 매핑
    //  속성 :   name - 매핑할 컬럼의 이름,
    //          unique - 유닉 설정,
    //          insertable - 삽입 가능 여부,
    //          updatable - 수정 가능 여부,
    //          length - 문자열의 길이,
    //          nullable - null 가능 여부,
    //          columnDefinition - 자료형과 제약 조건을 직접 기재,
    //          -> 예시) @Column(columnDefinition = "varchar(255) default 'Yes' ")
    //          precision - 소수 점 이하를 포함한 전체 자릿수 BigDecimal에서 사용 가능,
    //          scale - 소수 점이하 자릿수. BigDecimal에서 사용 가능
    @Column(length = 200, nullable = false)
    private String memoText;

    
    // Construct
    public Memo() {}
    public Memo(String memoText) {
        this.memoText = memoText;
    }
    public Memo(Long mno, String memoText) {
        this.mno = mno;
        this.memoText = memoText;
    }

    
    // Getter, Setter 
    public Long getMno() {return mno;}
    public void setMno(Long mno) {this.mno = mno;}
    public String getMemoText() {return memoText;}
    public void setMemoText(String memoText) {this.memoText = memoText;}


}
