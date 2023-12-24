package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 저장
    Member save(Member member);

    // id값을 가져올때 null이더라도 NPE발생안하게 감쌈
    Optional<Member> findById(Long id);

    // 이름값을 가져올때 null이더라도 NPE발생안하게 감쌈
    Optional<Member> findByName(String name);

    // 전부 불러오기
    List<Member> findAll();
}
