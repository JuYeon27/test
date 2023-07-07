create table users(
    user_id VARCHAR2(20) CONSTRAINT users_id_pk PRIMARY KEY,
    user_pw VARCHAR2(20),
    user_name VARCHAR2(20),
    birthday DATE,
    tel VARCHAR2(20)
);


insert into users 
values('admin', 'admin001', '관리자','','');
insert into users 
values('kim01', 'asdf111', '김철수','1993-01-01','010-111-2222');
insert into users 
values('lee02', 'asdf222', '이영지','2000-03-04','010-2222-3333');
COMMIT;