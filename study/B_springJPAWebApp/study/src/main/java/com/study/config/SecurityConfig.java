package com.study.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  // 설정클래스
@EnableWebSecurity  // Security 설정을 직접하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up", "/check-email", "/check-email-token",
                        "/email-login", "/check-email-login", "/login-link").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                .anyRequest().authenticated();

        /**
         * mvcMatchers() 권한 확인 없이 접근 가능하도 예외처러함
         * mvcMatchers(HttpMethod.GET, "/profile/*") get요청시만 허용
         * anyRequest().authenticated(); : 나머지는 로그인해야 사용 가능
         */
    }

    /**
     * static 리소스들은 인증없이 처리되도록
     * 기본 정적 자원 위치에 대한 요청에 대해서는 보안 검사를 무시하고 모든 사용자에게 허용하도록 설정
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

        /**
         * .ignoring() : Spring Security의 보안 필터 체인을 통과하지 않고 무시
         * .requestMatchers() : 보안 구성 시 특정 요청 매처(Request Matcher)에 대한 설정을 지정할 때 사용
         * PathRequest : 정적 자원에 대한 요청을 처리하기 위한 도우미 클래스
         * .toStaticResources() : 정적 자원에 대한 요청을 나타내기 위한 RequestMatcher를 생성
         *                      : 정적 자원에 대한 특별한 처리를 요청
         * .atCommonLocations() : 일반적으로 사용되는 공통된 보안 설정을 포함하는 빌더를 반환
         */
    }
}
