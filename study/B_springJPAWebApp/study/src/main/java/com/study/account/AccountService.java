package com.study.account;

import com.study.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service  // 비즈니스 로직을 담당하는 서비스(Service) 클래스, 컴포넌트 스캔을 통해 빈으로 등록
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;

    /**
     * processNewAccount
     * (토큰생성, 회원가입처리, 이메일 전송)
     * @param signUpForm
     */
    public void processNewAccount(SignUpForm signUpForm) {
        // 회원가입처리
        Account newAccount = saveNewAccount(signUpForm);
        // 토큰 생성
        newAccount.generateEmailCheckToken();
        // 이메일 전송
        sendSignUpConfirmEmail(newAccount);
    }

    /**
     * 회원가입 처리
     * @param signUpForm
     * @return
     */
    private Account saveNewAccount(@Valid SignUpForm signUpForm) {
        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(signUpForm.getPassword()) // TODO encoding 해야함
                .studyCreatedByWeb(true)
                .studyEnrollmentResultByWeb(true)
                .studyUpdatedByWeb(true)
                .build();
        return accountRepository.save(account);
    }

    /**
     * 이메일 전송
     * @param newAccount
     */
    private void sendSignUpConfirmEmail(Account newAccount) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setSubject("스터디올래, 회원 가입 인증");
        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken() +
                "&email=" + newAccount.getEmail());
        javaMailSender.send(mailMessage);
    }

}
