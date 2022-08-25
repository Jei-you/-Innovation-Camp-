package com.sparta.springhomework1.dto;

import com.sparta.springhomework1.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {

    private long id;
    private String title;
    private String userName;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BlogResponseDto (Blog view){
        this.title = view.getTitle();
        this.userName = view.getUserName();
        this.password = view.getPassword();
        this.content = view.getContent();
        this.createdAt = view.getCreateAt();
        this.modifiedAt = view.getModifiedAt();

    }

}
