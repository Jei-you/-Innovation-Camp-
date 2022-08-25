package com.sparta.springhomework1.service;

import com.sparta.springhomework1.entity.Blog;
import com.sparta.springhomework1.repository.BlogRepository;
import com.sparta.springhomework1.dto.BlogRequestDto;

import com.sparta.springhomework1.dto.BlogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service

public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional

    public Long update(Long id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (blog.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        blog.update(requestDto);
        return blog.getId();
    }

    public BlogResponseDto findById (Long id) {
        Blog view = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        return new BlogResponseDto(view);
    }
}



