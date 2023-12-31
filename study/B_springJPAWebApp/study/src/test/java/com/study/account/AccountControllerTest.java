package com.study.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    JavaMailSender javaMailSender;

    @DisplayName("회원 가입 화면 보이는지 테스트")
    @Test
    void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up")) // get 요청 /sign-up으로 했을 때
                .andDo(print()) // 응답바디 출력
                .andExpect(status().isOk()) // 상태코드 : 응답인지 확인
                .andExpect(view().name("account/sign-up"))  // 뷰가 보이는지 확인
                .andExpect(model().attributeExists("signUpForm"));  // signUpForm 있는지 확인
    }

    @DisplayName("회원 가입 처리 - 입력값 오류")
    @Test
    void signUpSubmit_with_wrong_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("nickname", "keesun")
                        .param("email", "email..")
                        .param("password", "12345")
                        .with(csrf()))  // CSRF 토큰 기능
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"));

        /**
         * 타임리프, spring security, spring MVC에 의해 CSRF 토큰 기능 지원해줌
         * 토큰으로 외부에서 보낸 폼인지 직접 입력받은 폼인지 확인
         * 테스트는 외부에서 보낸 것과 같은 형식이기 때문에 403 오루 발생
         * 기본적으로 CSRF 기능 활성화
         * Cross-Site Request Forgery : 공격은 악의적인 사용자가 희생자의 권한을 도용하여 웹 애플리케이션에 요청을 위조하는 공격
         *
         */
    }

    @DisplayName("회원 가입 처리 - 입력값 정상")
    @Test
    void signUpSubmit_with_correct_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("nickname", "keesun")
                        .param("email", "keesun@email.com")
                        .param("password", "12345678")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        // 이메일로 존재여부 확인
        assertTrue(accountRepository.existsByEmail("keesun@email.com"));

        // 메일 발송여부 확인
        then(javaMailSender).should().send(any(SimpleMailMessage.class));
    }

}