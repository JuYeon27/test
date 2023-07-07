
-- 공지사항 테이블
create table mvcsnsboard(
    bunho number(5) CONSTRAINT mvc_bunho_pk PRIMARY KEY,
    snsjemok VARCHAR2(100) NOT NULL,
    snswriter VARCHAR2(30) NOT NULL,
    snscontent VARCHAR2(4000) NOT NULL,
    snsdate DATE,
    snscnt NUMBER(5) DEFAULT 0 NOT NULL,
    filename varchar2(200),
    user_id varchar2(20),
    constraint mvc_user_id_fk foreign key (user_id) references users(user_id) on delete cascade
    
);

create SEQUENCE mvcsnsboard_sequence1
INCREMENT BY 1
START WITH 1 
MINVALUE 1 
NOCYCLE 
NOCACHE; 

insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'KG커피 공지사항','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트1','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트2','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트3','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트4','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트5','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트6','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트7','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트8','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트9','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
insert into mvcsnsboard
values(mvcsnsboard_sequence1.NEXTVAL,'테스트10','관리자','우리는 고급 원두를 사용합니다', sysdate, 0);
COMMIT;