package com.sparta.springhomework1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@RequiredArgsConstructor
@Data

public class BlogRequestDto {   //Blog.java에 뜨는 에러 해결
    private long id;
    private String title;
    private String userName;
    private String password;
    private String content;

//    public BlogRequestDto (BlogRequestDto requestDto){
//        this.title = requestDto.getTitle();
//        this.userName = requestDto.getUserName();
//        this.password = requestDto.getPassword();
//        this.content = requestDto.getContent();

    }





