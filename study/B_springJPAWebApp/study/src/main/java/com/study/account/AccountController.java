package com.study.account;

import com.study.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller  // 컴포넌트 스캔에 의해 빈으로 등록, 사용자의 HTTP 요청을 처리하고 응답을 반환하는 역할을 담당
@RequiredArgsConstructor  //생성자를 자동으로 생성해주는 역할
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;

//    private final AccountRepository accountRepository;
//    private final JavaMailSender javaMailSender;


    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);

        /**
         * @InitBinder
         *   : 폼 데이터 바인딩을 처리하기 전에 초기화 작업을 수행하는 메서드를 지정
         *   : 컨트롤러 클래스 내에서 특정한 데이터 바인딩 설정을 변경하거나 추가적인 전처리를 수행하는 데 사용
         *   : 특정 메서드를 정의하여 이 메서드에서 데이터 바인딩을 수정하거나 추가적인 검증을 수행
         */
    }


    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        //model.addAttribute("signUpForm", new SignUpForm());
        model.addAttribute(new SignUpForm());  //"signUpForm" 생략시 new SignUpForm()과 같은 이름을 생성하기 떄문
        return "account/sign-up";
    }

    /**
     * 회원가입 : post(작성)
     * @param signUpForm
     * @param errors
     * @return
     */
    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {

        // 에러가 있으면 다시 시작페이지
        if (errors.hasErrors()) {
            return "account/sign-up";
        }

        /*
        // 회원가입 처리
        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(signUpForm.getPassword()) // TODO encoding 해야함
                .studyCreatedByWeb(true)
                .studyEnrollmentResultByWeb(true)
                .studyUpdatedByWeb(true)
                .build();
        Account newAccount = accountRepository.save(account);

        // 토튼값 생성
        newAccount.generateEmailCheckToken();

        // 이메일 전송
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());  // 이메일 주소
        mailMessage.setSubject("스터디올래, 회원 가입 인증"); // 이메일 제목
        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken() +
                "&email=" + newAccount.getEmail());
        javaMailSender.send(mailMessage);  // 작성한 메세지 보냄

        /**
         * mailMessage.setTo() : 이메일 주소
         * mailMessage.setSubject() : 이메일 제목
         * mailMessage.setText() : 이메일 본문
         */

        accountService.processNewAccount(signUpForm);


        // TODO 회원가입 처리
        return "redirect:/";
    }

}