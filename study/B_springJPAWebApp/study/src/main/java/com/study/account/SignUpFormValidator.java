package com.study.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * SignUpForm 타입 인스턴스 검증
 */
@Component  // 컴포넌트 스캔에 의해 Spring 애플리케이션 컨텍스트에 빈으로 등록
@RequiredArgsConstructor  // 생성자를 자동으로 생성해주는 역할(AccountRepository 생성자 만듬)
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
        /**
         * supports() : 해당 AuthenticationProvider가 어떤 유형의 Authentication을 처리할지를 결정하는 역할
         *            : 파라미터로 전달된 Class<?> aClass가 특정 타입을 지원하는지 확인하는 역할
         * SignUpForm.class가 aClass 타입으로 할당(assignable) 가능한지 여부를 확인
         * SignUpForm 클래스의 객체가 aClass 클래스나 인터페이스와 호환 가능한지를 검사
         */
    }

    @Override
    public void validate(Object object, Errors errors) {
        SignUpForm signUpForm = (SignUpForm)object;
        // 입력한 email이 이미 존재한다면 에러 발생 메시지 출력
        if (accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }

        // 입력한 nickname이 이미 존재 한다면 에러 발생 메시지 출력
        if (accountRepository.existsByNickname(signUpForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getEmail()}, "이미 사용중인 닉네임입니다.");
        }

    }
}