package com.sparta.springhomework1.service;

//import com.sparta.springcore.dto.SignupRequestDto;
//import com.sparta.springcore.model.User;
//import com.sparta.springcore.model.UserRoleEnum;
//import com.sparta.springcore.repository.UserRepository;

import com.sparta.springhomework1.dto.SignUpRequestDto;
import com.sparta.springhomework1.entity.User;
import com.sparta.springhomework1.entity.UserRoleEnum;
import com.sparta.springhomework1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder; //패스워드 암호화
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignUpRequestDto signupRequestDto) {
////회원가입시, 유효성 체크
//        public Map<String,String> validateHandling(Errors errors){
//            Map<String,String>validatorResult = new HashMap<>();
//
//            for(FieldErrorerror : errors.getFieldErrors()){
//                String validKeyName = String.format("valid_%s",error.getField());
//                validatorResulr.put(validkeyName,error.getDefaultMessage());
//            }
//            return validatorResult;
//        }


// 회원 ID 중복 확인
        String username = signupRequestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {    //존재여부 확인
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
//패스워드 암호화
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }
}