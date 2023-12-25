package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 회원가입 main
  */
public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = new MemberServiceImpl();
        //MemberService memberService = appConfig.memberService();
        // ApplicationContext : 스프링 컨테이너
        // AnnotationConfigApplicationContext : 어노테이션 기반의 config
        ApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(AppConfig.class);
        // 직접 찾아오는 것이 아닌 spring 컨테이너를 통해서 찾아야 함 ("이름", 타입.class)
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member finMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + finMember.getName());
    }
}
