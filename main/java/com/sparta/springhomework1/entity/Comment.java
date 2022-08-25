package com.sparta.springhomework1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor //아래와 같이 써주면 좋음
@NoArgsConstructor  //기본 생성자 생성
@Getter
@Entity //테이블과 연계됨을 스프링에게 알려줌
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;
}
