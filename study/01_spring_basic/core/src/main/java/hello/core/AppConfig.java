package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 동작방식을 구성
 * 구현객체 생성 및 연결
 */
public class AppConfig {
    public MemberService memberService() {
        //return new MemberServiceImpl(new MemoryMemberRepository());
        // 역할과 구현 클래스를 한분에 보기위해 분리
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        //return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    private static FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
