
create table payments_table(


payments_id number constraint payments_id_pk primary key
,user_id varchar2(200) not null
,order_id number not null
,menu_id number not null
,menu_amount number
, constraint payments_user_id_fk foreign key (user_id) references users(user_id) on delete cascade 
, constraint payments_menu_id_fk foreign key (menu_id) references Menu(menuId) on delete cascade 
, constraint payments_order_id_fk foreign key (order_id) references order_table(order_id) on delete cascade 
);



create sequence payments_seq
INCREMENT BY 1
START WITH 1 
MINVALUE 1 
NOCYCLE 
NOCACHE;
