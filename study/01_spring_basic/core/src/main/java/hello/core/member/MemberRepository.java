package hello.core.member;

/**
 * 회원저장소 - 회원저장소 인터페이스
 */
public interface MemberRepository {

    // 회원 저장
    void save(Member member);

    // 회원 id찾기
    Member findById(Long memberId);
}
