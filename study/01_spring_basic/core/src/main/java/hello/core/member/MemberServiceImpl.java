package hello.core.member;

/**
 * 회원서비스 - 구현체
 */
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 찾기
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
