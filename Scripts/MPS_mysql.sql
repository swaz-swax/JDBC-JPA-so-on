CREATE TABLE mobiles_cg 
(
mobile_id INT PRIMARY KEY,
 name VARCHAR(20),
 price DOUBLE,
quantity INT
);



create table customer_cg
(
customer_id INT primary key,
customer_name varchar(30),
email varchar(30),
phone_number INT
); 

create table purchase_details_cg
(
purchase_id INT primary key,
purchase_date date,
purchase_quantity INT,
purchase_amount DOUBLE,
mobile_id INT references mobiles_cg(mobile_id),
customer_id INT references customer_cg(customer_id)
);

INSERT INTO mobiles_cg VALUES(1001,'Nokia Lumia 520',8000,20);
INSERT INTO mobiles_cg VALUES(1002,'Samsung Galaxy IV',38000,40);
INSERT INTO mobiles_cg VALUES(1003,'Sony xperia C',15000,30);
INSERT INTO mobiles_cg VALUES(1004,'iPhone Xs',132000,10);

INSERT INTO customer_cg VALUES(101,'Smith','smith@gmail.com',1233456789);
INSERT INTO customer_cg VALUES(232,'Clarke','clarke@gmail.com',7654256789);
INSERT INTO customer_cg VALUES(305,'Jones','jones@gmail.com',8456256789);
INSERT INTO customer_cg VALUES(451,'Ravi Kumar','ravi@gmail.com',8965256789); 



# @D:\Scripts\MPS.sql