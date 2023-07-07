

create table vocboard(
    v_bunho number CONSTRAINT voc_bunho_pk PRIMARY KEY,
    v_jemok VARCHAR2(100) NOT NULL,
    v_writer VARCHAR2(30) NOT NULL,
    v_content VARCHAR2(4000) NOT NULL,
    v_date DATE,
    v_cnt NUMBER DEFAULT 0 NOT NULL,
    v_filename varchar2(200),
    ref NUMBER,
    re_step NUMBER,
    re_level NUMBER,
    user_id varchar2(20),
    constraint voc_user_id_fk foreign key (user_id) references users(user_id) on delete cascade
);


create SEQUENCE vocboard_seq
INCREMENT BY 1
START WITH 1 
MINVALUE 1 

NOCYCLE 
NOCACHE;