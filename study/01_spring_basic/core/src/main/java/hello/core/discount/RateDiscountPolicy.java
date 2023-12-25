package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 할인정책 - 구연체
 * vip면 10%, 아니면 할인 x
 */
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;  // 10% 할인

    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
