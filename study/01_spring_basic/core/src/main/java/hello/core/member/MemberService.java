package hello.core.member;

/**
 * 회원서비스 - 인터페이스
 */
public interface MemberService {

    // 회원 가입
    void join(Member member);

    // 회원 찾기
    Member findMember(Long memberId);
}
