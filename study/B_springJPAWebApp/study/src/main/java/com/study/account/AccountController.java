package com.study.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        //model.addAttribute("signUpForm", new SignUpForm());
        model.addAttribute(new SignUpForm());  //"signUpForm" 생략시 new SignUpForm()과 같은 이름을 생성하기 떄문
        return "account/sign-up";
    }

}