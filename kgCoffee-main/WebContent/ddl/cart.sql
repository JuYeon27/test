
create table cart_table(
cart_id number constraint cart_id_pk primary key
,user_id varchar2(20) not null
,menu_id number not null
,menu_amount number
, constraint cart_user_id_fk foreign key (user_id) references users(user_id) on delete cascade 
, constraint cart_menu_id_fk foreign key (menu_id) references Menu(menuId) on delete cascade 

);




create sequence cart_seq
INCREMENT BY 1
START WITH 1 
MINVALUE 1 
NOCYCLE 
NOCACHE;
