package com.example.oauth2login.config.auth;

import com.example.oauth2login.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면 사용위해
                .csrf().disable()
                .headers().frameOptions().disable()

                // url 별 권한 관리
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())

                // 설정된 값들 이외 나머지 url
                .anyRequest().authenticated()

                // 로그아웃 성공 시 / 주소로 이동
                .and()
                .logout().logoutSuccessUrl("/")

                // oauth2 로그인 기능에 대한 여러 설정의 진입점
                .and()
                .oauth2Login()
                // oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정
                .userInfoEndpoint()
                // 소셜 로그인 성공 시 후속 조치를 진행할 userService 인터페이스의 구현체
                // 리소스 서버(구글)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
                .userService(customOAuth2UserService);
    }
}
