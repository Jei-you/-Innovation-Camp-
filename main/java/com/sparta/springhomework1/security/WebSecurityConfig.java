package com.sparta.springhomework1.security;

import com.sparta.springhomework1.security.filter.FormLoginFilter;
import com.sparta.springhomework1.security.filter.JwtAuthFilter;
import com.sparta.springhomework1.security.jwt.HeaderTokenExtractor;
import com.sparta.springhomework1.security.provider.FormLoginAuthProvider;
import com.sparta.springhomework1.security.provider.JWTAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity // 웹보안 활성화
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthProvider jwtAuthProvider;
    private final HeaderTokenExtractor headerTokenExtractor;
    public WebSecurityConfig(
            JWTAuthProvider jwtAuthProvider,
            HeaderTokenExtractor headerTokenExtractor
    ){
        this.jwtAuthProvider = jwtAuthProvider;
        this.headerTokenExtractor = headerTokenExtractor;
    }
    //비밀번호 암호화 알고리즘
    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(formLoginAuthProvider())
                .authenticationProvider(jwtAuthProvider);
    }
    @Override
    public void configure(WebSecurity web){
        //h2콘솔 사용에 대한 허용(CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //회원관리 처리 API(POST/user/**)에 대해 CSRF무시
//서버에서 JWT로 인증하기 위해 세션 인증 막기
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//FormLoginFilter(로그인 인증), JwtFilter(서버 접근 시 JWT 확인 후 인증 실시) 등록
        http
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

//HttpServletRequest를 사용하는 요청들에 대한 접근 제한 설정
            http.authorizeRequests()
                .anyRequest()   // 어떤 요청이든 '인증'
                .permitAll()    // 모든 사용자가 접근할 수 있음
                .and()
                //로그아웃기능
                .logout()
                // 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                // "접근 불가" 페이지 URL 설정
                .accessDeniedPage("/forbidden.html");
    }

    @Bean
    public FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
        formLoginFilter.setFilterProcessesUrl("/user/login");
        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
        formLoginFilter.afterPropertiesSet();
        return formLoginFilter;
    }

    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler() {
        return new FormLoginSuccessHandler();
    }

    @Bean
    public FormLoginAuthProvider formLoginAuthProvider() {
        return new FormLoginAuthProvider(encodePassword());
    }

    private JwtAuthFilter jwtFilter() throws Exception {
        List<String> skipPathList = new ArrayList<>();

        // Static 정보 접근 허용
        skipPathList.add("GET,/images/**");
        skipPathList.add("GET,/css/**");

        // h2-console 허용
        skipPathList.add("GET,/h2-console/**");
        skipPathList.add("POST,/h2-console/**");
        // 회원 관리 API 허용
        skipPathList.add("GET,/user/**");
        skipPathList.add("POST,/user/signup");

        skipPathList.add("GET,/");
        skipPathList.add("GET,/basic.js");

        skipPathList.add("GET,/favicon.ico");

        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPathList,
                "/**"
        );

        JwtAuthFilter filter = new JwtAuthFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




}

//// image 폴더를 login 없이 허용
//                .antMatchers("/images/**")
//                        .permitAll()
//// css 폴더를 login 없이 허용
//                        .antMatchers("/css/**")
//                        .permitAll()
//
//
//                        .authenticated()
//
//// 로그인 기능 허용
//                        .formLogin()
//// 로그인 View제공(GET / user /login)
//                        .loginPage("/api/blogs-post/user/login")
//// 로그인 처리 (POST / user /login)
//                        .loginProcessingUrl("/api/blogs-post/user/login")
//// 로그인 처리 후 성공 시 URL
//                        .defaultSuccessUrl("/")
//// 로그인 처리 후 실패 시 URL
//                        .failureUrl("/api/blogs-post/user/login?error")
//                        .permitAll()
//                        .and()
//// 로그아웃 기능 허용
//                        .logout()
//// 로그아웃 처리 URL
//                        .logoutUrl("/api/blogs-post/user/logout")
//                        .permitAll();
//
//                        http.csrf()
//                        .ignoringAntMatchers("/user/**");
