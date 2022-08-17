package com.sparta.springhomework1.controller;

import com.sparta.springhomework1.domain.Blog;
import com.sparta.springhomework1.domain.BlogRepository;
import com.sparta.springhomework1.domain.BlogRequestDto;
import com.sparta.springhomework1.domain.BlogResponseDto;
import com.sparta.springhomework1.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {

//    public final BlogRepository blogRepository;
//    public final BlogService blogService;
    protected final BlogRepository blogRepository;
    protected final BlogService blogService;


    //creat(생성)
    @PostMapping("/api/blogs-post")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto){
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    //read(전체조회)
    @GetMapping("/api/blogs-post")
    public List<Blog> readBlog(){
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    //view(개별조회)
    @GetMapping("/api/blogs-post/{id}")
    public BlogResponseDto findById(@PathVariable Long id){
        return blogService.findById(id);
    }

    //edit(수정)
    @PutMapping ("/api/blogs-post/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        blogService.update(id, requestDto);
        return id;
    }

    //delete(삭제)
    @DeleteMapping("/api/blogs-post/{id}")
    public Long deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        Blog blog = blogRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재 하지 않습니다.")
        );

        // 비밀번호 확인
        if (!blog.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("일치하지 않습니다");

        }
        blogRepository.deleteById(id);
        return id;
    }
//
//    //check(비밀번호 확인 API)
//    @GetMapping("/api/blogs-check/{id}")
//





}


