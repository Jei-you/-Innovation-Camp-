package com.sparta.springhomework1.domain;

import com.sparta.springhomework1.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog>findAllByOrderByModifiedAtDesc();

}


