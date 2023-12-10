package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository {

    // seve할 떄 저장할 곳 - 실무에서는 동시성 문제도해결해야함
    private static Map<Long, Member> store = new HashMap<>();

    // 0, 1, 2 키값을 생성 - 실무에서는 동시성 문제도해결해야함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // setId할때 시퀀스값을 하나 올려줌
        member.setId(++sequence);
        // store에 저장함.
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 id가져와 Optional 감싸서 반환
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프를 돌려 람다를 사용해 피러처리하여 얻은 name값을 파라미터로 넘어옴 name과 같은지 확인, findAny 그중에 찾으면 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // Map인데 List가 루프돌리기도 편하기 때문에 List로 반환
        // store.values() = Map에 있는 Member
        return new ArrayList<>(store.values());
    }
}
