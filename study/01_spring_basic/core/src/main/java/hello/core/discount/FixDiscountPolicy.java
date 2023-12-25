package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 할인정체 구현체 - 정액할인
 * vip면 1000원, 아니면 할인 x
 */
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
