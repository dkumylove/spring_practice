package com.study.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") // 연관관계가 복잡해 질때 순환참조-무한루프 발생 -> 스택오버발생/ 방지차원에서 사용
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id  // Primary Key
    @GeneratedValue  // 시퀀스 자동증가
    private Long id;

    @Column(unique = true) // 중복방지 : 유니크
    private String email;

    @Column(unique = true) // 중복방지 : 유니크
    private String nickname;

    private String password;

    private boolean emailVerified; // 계정의 이메일 인증여부 확인을 위한 블링 플래그

    private String emailCheckToken;  // 이메일 검증시 사용 토큰값 (DB에저장후 매치시 사용)

    private LocalDateTime joinedAt;  // 가입날짜

    // 프로필을 위한 엔티티(varchar(255))

    private String bio;  // 자기소개

    private String url;  // 웹사이트 url

    private String occupation;  // 직업

    private String location;  // 살고있는 지역

    @Lob  // 텍스트타입매핑, Large Object컬럼에 매핑, 데이터베이스에서 지원하는 큰 크기의 데이터를 저장할 수 있는 컬럼
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;  // 프로필 이미지

    // 알림설정

    private boolean studyCreatedByEmail;  // 스터디 생성 이메일 알림

    private boolean studyCreatedByWeb;  // 스터디 생성 웹 알람

    private boolean studyEnrollmentResultByEmail;  // 스터디 신청 결과 이메일 알림

    private boolean studyEnrollmentResultByWeb;  // 스터디 신청 결과 웹 알림

    private boolean studyUpdatedByEmail;  // 스터디 업데이트 정보 이메일 알림

    private boolean studyUpdatedByWeb;  // 스터디 업데이트 정보 웹 알림

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }

}