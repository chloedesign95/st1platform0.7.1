package com.st1.platform.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//7.11 : 각 필드에 접근 가능하도록 Getter도 넣어준다.
@Getter
//7.10 : lombok
@ToString
//7.9 EntityListeners Auditing Field
@EntityListeners(AuditingEntityListener.class)
//7.2 : 표준 JPA annotation
@MappedSuperclass
//7.1 : AuditingFields 클래스 생성 및 작성
public abstract class AuditingFields {

    //7.12 : 웹화면에서 파라미터를 받아서 셋팅할때 파싱이 잘 되기위한 파싱에대한 룰을 넣어주어야한다.
    //스프링프레임워크 Formatter annotation  :: Iso 객체를 이용해서 사용해본다.
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //7.4 : 생성일시
    @CreatedDate
    @Column(nullable = false, updatable = false) //7.13 :  updatable = false 이 필드는 최초한번 셋팅 이후 업데이트가 불가하다.
    protected LocalDateTime createdAt;

    //7.5 : 생성자
    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100) //7.14 :  updatable = false 이 필드는 최초한번 셋팅 이후 업데이트가 불가하다.
    protected String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //7.6 : 수정일시
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifiedAt;

    //7.7 : 수정자
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    protected String modifiedBy;

}
