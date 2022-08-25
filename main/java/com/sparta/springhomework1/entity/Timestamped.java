package com.sparta.springhomework1.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  //Entity가 가동으로 컬럼으로 인식함
@EntityListeners(AuditingEntityListener.class)  //생성, 변경 시간을 자동으로 업데이트함

public abstract class Timestamped {
    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}

