package com.sparta.springhomework1.controller;

import com.sparta.springhomework1.dto.SignUpRequestDto;
import com.sparta.springhomework1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService; //UserService를 멤버변수로 선언
    }

    // 회원 로그인 페이지
    @GetMapping("/api/blogs/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/api/blogs/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/api/blogs/user/signup")
    public String registerUser(SignUpRequestDto signUpRequestDto) {
        userService.registerUser(signUpRequestDto);
        return "redirect:/api/blogs-post/user/login";
    }
}