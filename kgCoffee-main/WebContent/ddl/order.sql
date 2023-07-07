

create table order_table(
order_id number constraint order_id_pk primary key
,user_id varchar2(20) not null
,map_id number not null
,order_price number
,order_date DATE DEFAULT SYSDATE
,imp_uid varchar2(200) not null,
, constraint oder_user_id_fk foreign key (user_id) references users(user_id) on delete cascade
, constraint order_map_id_fk foreign key (map_id) references map_table(map_id) on delete cascade

);




create sequence order_seq
INCREMENT BY 1
START WITH 1 
MINVALUE 1 
NOCYCLE 
NOCACHE;


