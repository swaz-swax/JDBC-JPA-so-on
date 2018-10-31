CREATE TABLE mobiles_cg 
(
mobile_id NUMBER PRIMARY KEY,
 name VARCHAR2 (20),
 price NUMBER(10,2),
quantity NUMBER(10)
);



create table customer_cg
(
customer_id number(10) primary key,
customer_name varchar2(30),
email varchar2(30),
phone_number number(10)
); 

create table purchase_details_cg
(
purchase_id number(10) primary key,
purchase_date date,
purchase_quantity number(10),
purchase_amount number(10,2),
mobile_id NUMBER references mobiles_cg(mobile_id),
customer_id Number(10) references customer_cg(customer_id)
);

INSERT INTO mobiles_cg VALUES(1001,'Nokia Lumia 520',8000,20);
INSERT INTO mobiles_cg VALUES(1002,'Samsung Galaxy IV',38000,40);
INSERT INTO mobiles_cg VALUES(1003,'Sony xperia C',15000,30);
INSERT INTO mobiles_cg VALUES(1004,'iPhone Xs',132000,10);

INSERT INTO customer_cg VALUES(101,'Smith','smith@gmail.com',1233456789);
INSERT INTO customer_cg VALUES(232,'Clarke','clarke@gmail.com',7654256789);
INSERT INTO customer_cg VALUES(305,'Jones','jones@gmail.com',8456256789);
INSERT INTO customer_cg VALUES(451,'Ravi Kumar','ravi@gmail.com',8965256789); 

create sequence purchase_details_sequence;

# @D:\Scripts\MPS.sql