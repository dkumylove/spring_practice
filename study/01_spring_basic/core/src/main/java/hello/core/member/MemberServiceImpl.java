package hello.core.member;

/**
 * 회원서비스 - 구현체
 */
public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자 주입 MemberServiceImpl ->  MemoryMemberRepository
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
