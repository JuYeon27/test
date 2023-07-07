CREATE TABLE Menu (
    menuId NUMBER(3) NOT NULL 
        CONSTRAINT Menu_menuId_pk PRIMARY KEY,
    imgurl varchar2(200),
    caffeineType VARCHAR2(10), --카페인 유,무
    menuName VARCHAR2(50),
    menuPrice NUMBER(10),
    menuExplain VARCHAR2(200),
    menuType VARCHAR2(10) -- hot, ice ...
);

create sequence Menu_sequencel
INCREMENT BY 1
START WITH 1 
MINVALUE 1 

NOCYCLE 
NOCACHE; 