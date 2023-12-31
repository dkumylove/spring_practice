package com.study.account;

import com.study.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)  // 속성설정 : 특정 메서드나 클래스에 대한 트랜잭션을 읽기 전용(read-only)
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Account findByEmail(String email);

}
