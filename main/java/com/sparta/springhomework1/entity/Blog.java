package com.sparta.springhomework1.entity;

import com.sparta.springhomework1.dto.BlogRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor //아래와 같이 써주면 좋음
@NoArgsConstructor  //기본 생성자 생성
@Getter
@Entity //테이블과 연계됨을 스프링에게 알려줌

public class Blog extends Timestamped { //생성, 수정 시간을 자동으로 만들어줌
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String content;

    //추가
    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.password = String.valueOf(requestDto.getPassword());
        this.content = requestDto.getContent();

    }


    //update 메소드 추가하기
    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();

    }
}

