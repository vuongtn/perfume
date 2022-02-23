CREATE TABLE `trademark` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

CREATE TABLE `product` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_trademark` INT NOT NULL,
	`name` varchar(255) NOT NULL,
	`origin` varchar(255),
	`gender` varchar(10),
	`fragrant` varchar(255),
	`guarantee` varchar(30),
	`price` DECIMAL NOT NULL,
	`image` varchar(255) NOT NULL,
	`short_description` TEXT,
	`detail_description` TEXT NOT NULL,
	`amount` INT NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL,
	`password` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
	`full_name` varchar(100) NOT NULL,
	`address` varchar(255),
	`phone` varchar(15) NOT NULL,
	`avatar` varchar(255),
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`password` varchar(100) NOT NULL,
	`code` varchar(50) NOT NULL,
	`description` varchar(255),
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
	`id_user` INT NOT NULL,
	`id_role` INT NOT NULL,
	`role_name` varchar(50),
	`view_role` BOOLEAN NOT NULL,
	`insert_role` BOOLEAN NOT NULL,
	`update_role` BOOLEAN NOT NULL,
	`delete_role` BOOLEAN NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id_user`,`id_role`)
);

CREATE TABLE `cart` (
	`id_product` INT NOT NULL,
	`id_user` INT NOT NULL,
	`amount` INT NOT NULL,
	PRIMARY KEY (`id_product`,`id_user`)
);

CREATE TABLE `bill` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_user` INT NOT NULL,
	`receiver_name` varchar(100) NOT NULL,
	`receiver_address` varchar(255) NOT NULL,
	`receiver_email` varchar(50) NOT NULL,
	`receiver_phone` varchar(15) NOT NULL,
	`note` TEXT NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`updated_by` varchar(100),
	`status` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `bill_detail` (
	`id_product` INT NOT NULL,
	`id_bill` INT NOT NULL,
	`amount` INT NOT NULL,
	`currently_price` DECIMAL NOT NULL,
	PRIMARY KEY (`id_product`,`id_bill`)
);

CREATE TABLE `review` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_product` INT NOT NULL,
	`id_user` INT NOT NULL,
	`content` TEXT NOT NULL,
	`star` INT NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);
CREATE TABLE `contact` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`full_name` varchar(100) NOT NULL,
	`email` varchar(50) NOT NULL,
	`content` TEXT NOT NULL,
	`created_date` DATETIME,
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

CREATE TABLE `news` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL,
	`short_description` TEXT NOT NULL,
	`detail_description` TEXT NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100) NOT NULL,
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);



ALTER TABLE `product` ADD CONSTRAINT `product_fk0` FOREIGN KEY (`id_trademark`) REFERENCES `trademark`(`id`);

ALTER TABLE `cart` ADD CONSTRAINT `cart_fk0` FOREIGN KEY (`id_product`) REFERENCES `product`(`id`);

ALTER TABLE `cart` ADD CONSTRAINT `cart_fk1` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `bill` ADD CONSTRAINT `bill_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `bill_detail` ADD CONSTRAINT `bill_detail_fk0` FOREIGN KEY (`id_product`) REFERENCES `product`(`id`);

ALTER TABLE `bill_detail` ADD CONSTRAINT `bill_detail_fk1` FOREIGN KEY (`id_bill`) REFERENCES `bill`(`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_fk1` FOREIGN KEY (`id_role`) REFERENCES `role`(`id`);

ALTER TABLE `review` ADD CONSTRAINT `review_fk0` FOREIGN KEY (`id_product`) REFERENCES `product`(`id`);

ALTER TABLE `review` ADD CONSTRAINT `review_fk1` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);






-- sửa kiểu dữ liệu
ALTER TABLE product MODIFY COLUMN guarantee varchar(30);
-- xóa cột
ALTER TABLE trademark DROP COLUMN updated_by;
-- thêm cột
ALTER TABLE trademark ADD COLUMN updated_by varchar(100);


-- insert data trademark
select * from trademark;
insert into trademark(name,status) values('Gucci',true);

-- insert data product
select * from product;
select * from product where gender='nu';


insert into product(id_trademark,name,origin,gender,fragrant,guarantee,price,image,short_description,detail_description,amount,status)
values
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(1,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,true),
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,false),
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,false)
;


insert into product(id_trademark,name,origin,gender,fragrant,guarantee,price,image,short_description,detail_description,amount,created_date,status)
values
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',false),
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-12-09',false),
(2,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nam','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-01-09',false)
;

insert into product(id_trademark,name,origin,gender,fragrant,guarantee,price,image,short_description,detail_description,amount,created_date,status)
values
(3,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Unisex','Hoa hồng','1 tháng',500000.00,'anh1.jpg','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(3,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Unisex','Hoa hồng','1 tháng',25000000.00,'anh1,jpg','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-12-09',true),
(3,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Unisex','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-01-09',true),
(3,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nữ','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(3,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Nữ','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-12-09',true),
(3,'Nước Hoa Nam Versace Eros Man EDT 5ml','Pháp','Unisex','Hoa hồng','1 tháng',2500000.00,'anh1','Nước hoa rất ô xờ kê','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-01-09',true)
;

SET SQL_SAFE_UPDATES = 0;
update product 
set image='anh1.jpg'
where image='anh1.png';

delete from product






