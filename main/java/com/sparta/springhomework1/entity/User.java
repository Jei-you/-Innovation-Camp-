package com.sparta.springhomework1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)    //스프링데이터 JPA에서 자동으로 되는 작업
    private UserRoleEnum role;

    //생성자
    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity     //DB의 테이블과 1:1로 매핑되는 객체
//@Table(name = "user")   //테이블명을 user로 지정하기 위해 Table 어노테이션 사용
////롬복 관련 어노테이션으로 아래 3가지 관련 코드를 자동으로 생성
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class User {
//
//    @JsonIgnore
//    @Id
//    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    @Column(name = "username", length = 50, unique = true)
//    private String username;
//
//    @JsonIgnore
//    @Column(name = "password", length = 100)
//    private String password;
//
//    @Column(name = "nickname", length = 50)
//    private String nickname;
//
//    @JsonIgnore
//    @Column(name = "activated")
//    private boolean activated;
//
//    //user객체와 권한객체의 테이블 관계를 일대다, 다대일 관계로 정의함
//    @ManyToMany
//    @JoinTable(
//            name = "user_authority",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
//    private Set<Authority> authorities;
//
//}
