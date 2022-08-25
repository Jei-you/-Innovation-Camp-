package com.sparta.springhomework1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {
//    @Pattern (regexp= "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,12}$",message="최소 4자 이상, 12자 이하 알파벳 대소문자(a~z, A~Z), 숫자(0~9)를 적어주세요 ")
    private String username;
//
//    @NotBlank
//    @Size(min = 4, max = 20)
    private String password;

    private boolean admin = false;
    private String adminToken = "";

    }