drop table if exists member CASCADE;
create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);

-- 데이터 추가 쿼리
insert into member(name) values('spring')