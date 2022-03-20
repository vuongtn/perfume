CREATE TABLE `brand` (
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
	`id_brand` INT NOT NULL,
	`name` varchar(255) NOT NULL,
	`origin` varchar(255),
	`gender` varchar(10),
	`fragrant` varchar(255),
	`guarantee` varchar(30),
	`price` DECIMAL NOT NULL,
	`image` varchar(255) NOT NULL,
	`short_description` TEXT,
	`detail_description` LONGTEXT NOT NULL,
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
	`phone` varchar(15),
	`avatar` varchar(255),
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
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
    `payment` varchar(100) not null,
    `total_price` DECIMAL NOT NULL,
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

CREATE TABLE `contact` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`full_name` varchar(100) NOT NULL,
	`email` varchar(50) NOT NULL,
	`content` TEXT NOT NULL,
	`created_date` DATETIME,
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
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
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id_user`,`id_role`)
);

CREATE TABLE `news` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL,
	`short_description` TEXT NOT NULL,
	`detail_description` LONGTEXT NOT NULL,
    `image` varchar(255) NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

CREATE TABLE `introduce` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`description` LONGTEXT NOT NULL,
	`created_date` DATETIME,
	`updated_date` DATETIME,
	`created_by` varchar(100),
	`updated_by` varchar(100),
	`status` BOOLEAN,
	PRIMARY KEY (`id`)
);

ALTER TABLE `product` ADD CONSTRAINT `product_fk0` FOREIGN KEY (`id_brand`) REFERENCES `brand`(`id`);

ALTER TABLE `cart` ADD CONSTRAINT `cart_fk0` FOREIGN KEY (`id_product`) REFERENCES `product`(`id`);

ALTER TABLE `cart` ADD CONSTRAINT `cart_fk1` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `bill` ADD CONSTRAINT `bill_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `bill_detail` ADD CONSTRAINT `bill_detail_fk0` FOREIGN KEY (`id_product`) REFERENCES `product`(`id`);

ALTER TABLE `bill_detail` ADD CONSTRAINT `bill_detail_fk1` FOREIGN KEY (`id_bill`) REFERENCES `bill`(`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_fk1` FOREIGN KEY (`id_role`) REFERENCES `role`(`id`);














-- sửa kiểu dữ liệu
ALTER TABLE product MODIFY COLUMN guarantee varchar(30);
-- xóa cột
ALTER TABLE user DROP COLUMN phone;
-- thêm cột
ALTER TABLE bill ADD COLUMN payment varchar(100);

drop table news;







-- insert data brand
select * from brand;
insert into brand(name,status) values('Dolce Gabbana',true);
insert into brand(name,status) values('Chanel',true);
insert into brand(name,status) values('Louis Vuitton',true);
insert into brand(name,status) values('Test1',true);
insert into brand(name,status) values('Test2',true);
insert into brand(name,status) values('Test3',true);
insert into brand(name,status) values('Test4',true);
insert into brand(name,status) values('Test4',false);




-- insert data product
select * from product;

insert into product(id_brand,name,origin,gender,fragrant,guarantee,price,image,short_description,detail_description,amount,created_date,status)
values
(1,'DOLCE GABBANA K EDP 100ML','Pháp','Nam','Hoa hồng','1 tháng',500000,'anh1.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(1,'DOLCE GABBANA THE ONE FOR MEN EDT 150ML','Pháp','Nam','Hoa hồng','1 tháng',350000,'anh2.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-10-09',true),
(1,'DOLCE GABBANA K','Pháp','Nam','Hoa hồng','1 tháng',300000,'anh3.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-09-09',true),
(1,'DOLCE GABBANA K EDP','Pháp','Nam','Hoa hồng','1 tháng',700000,'anh4.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2022-02-01',true),
(1,'LIGHT BLUE EAU INTENSE WOMEN','Pháp','Nam','Hoa hồng','1 tháng',750000,'anh5.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-12-09',true),
(1,'DOLCE ROSA EXCELCA EDP','Pháp','Nam','Hoa hồng','1 tháng',1000000,'anh6.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-07-09',true),
(1,'THE ONE FOR MEN EDP','Pháp','Nam','Hoa hồng','1 tháng',1200000,'anh7.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-08-09',true),
(1,'LIGHT BLUE POUR HOMME EDT','Pháp','Nam','Hoa hồng','1 tháng',1500000,'anh8.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-01-09',true),
(1,'DOLCE SHINE','Pháp','Nam','Hoa hồng','1 tháng',2000000,'anh9.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(1,'DOLCE GABBANA FLOWER EDP','Pháp','Nam','Hoa hồng','1 tháng',2200000,'anh10.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),

(2,'DOLCE GABBANA K EDP 100ML','Pháp','Nữ','Hoa hồng','1 tháng',500000,'anh1.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(2,'DOLCE GABBANA THE ONE FOR MEN EDT 150ML','Pháp','Nữ','Hoa hồng','1 tháng',350000,'anh2.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-10-09',true),
(2,'DOLCE GABBANA K','Pháp','Nữ','Hoa hồng','1 tháng',300000,'anh3.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-09-09',true),
(2,'DOLCE GABBANA K EDP','Pháp','Nữ','Hoa hồng','1 tháng',700000,'anh4.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2022-02-01',true),
(2,'LIGHT BLUE EAU INTENSE WOMEN','Pháp','Nữ','Hoa hồng','1 tháng',750000,'anh5.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-12-09',true),
(2,'DOLCE ROSA EXCELCA EDP','Pháp','Nữ','Hoa hồng','1 tháng',1000000,'anh6.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-07-09',true),
(2,'THE ONE FOR MEN EDP','Pháp','Nữ','Hoa hồng','1 tháng',1200000,'anh7.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-08-09',true),
(2,'LIGHT BLUE POUR HOMME EDT','Pháp','Nữ','Hoa hồng','1 tháng',1500000,'anh8.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-01-09',true),
(2,'DOLCE SHINE','Pháp','Nữ','Hoa hồng','1 tháng',2000000,'anh9.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(2,'DOLCE GABBANA FLOWER EDP','Pháp','Nữ','Hoa hồng','1 tháng',2200000,'anh10.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),

(4,'DOLCE GABBANA K EDP 100ML','Pháp','Unisex','Hoa hồng','1 tháng',500000,'anh1.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(4,'DOLCE GABBANA THE ONE FOR MEN EDT 150ML','Pháp','Unisex','Hoa hồng','1 tháng',350000,'anh2.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-10-09',true),
(4,'DOLCE GABBANA K','Pháp','Unisex','Hoa hồng','1 tháng',300000,'anh3.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-09-09',true),
(4,'DOLCE GABBANA K EDP','Pháp','Unisex','Hoa hồng','1 tháng',700000,'anh4.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2022-02-01',true),
(4,'LIGHT BLUE EAU INTENSE WOMEN','Pháp','Unisex','Hoa hồng','1 tháng',750000,'anh5.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-12-09',true),
(4,'DOLCE ROSA EXCELCA EDP','Pháp','Unisex','Hoa hồng','1 tháng',1000000,'anh6.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-07-09',true),
(4,'THE ONE FOR MEN EDP','Pháp','Unisex','Hoa hồng','1 tháng',1200000,'anh7.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-08-09',true),
(4,'LIGHT BLUE POUR HOMME EDT','Pháp','Unisex','Hoa hồng','1 tháng',1500000,'anh8.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-01-09',true),
(4,'DOLCE SHINE','Pháp','Unisex','Hoa hồng','1 tháng',2000000,'anh9.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true),
(4,'DOLCE GABBANA FLOWER EDP','Pháp','Unisex','Hoa hồng','1 tháng',2200000,'anh10.jpg','Nước hoa hương thơm ngào ngạt','dfasdfasdfasfdasfdasfdasfdasfasfasfdasfasfasfasfasfasfasfasfasfasfdasf',100,'2021-11-09',true);

-- insert data introduce
select * from introduce;
insert into introduce(description,status) values(" ĐỘ Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis ipsa ratione magnam cupiditate! Eum deleniti quam amet praesentium voluptatum ipsa veritatis necessitatibus! Amet placeat illo repellat sint quasi officia sed!
						",true);
                        
-- insert data news
select * from news;
insert into news(title,short_description,detail_description,image,created_date,status) values
('Nước hoa pháp mới ra mắt năm 2022','dfddddddddddddddddddddddddddddddddddddddddddddd','độdfddddddddddddddddddddddddddddddddddddddddddddd','anh1.jpg','2021-12-09',true),
('Nước hoa mỹ mới ra mắt năm 2022','dfddddddddddddddddddddddddddddddddddddddddddddd','độdfddddddddddddddddddddddddddddddddddddddddddddddf','anh1.jpg','2019-12-09',true),
('Nước hoa pháp mới ra mắt năm 2022','dfddddddddddddddddddddddddddddddddddddddddddddd','độdfddddddddddddddddddddddddddddddddddddddddddddd','anh1.jpg','2021-12-09',true),
('Nước hoa mỹ mới ra mắt năm 2022','dfddddddddddddddddddddddddddddddddddddddddddddd','độdfddddddddddddddddddddddddddddddddddddddddddddddf','anh1.jpg','2019-12-09',true),
('Nước hoa pháp mới ra mắt năm 2022','dfddddddddddddddddddddddddddddddddddddddddddddd','độdfddddddddddddddddddddddddddddddddddddddddddddd','anh1.jpg','2021-12-09',true),
('Nước hoa mỹ mới ra mắt năm 2022','dfddddddddddddddddddddddddddddddddddddddddddddd','độdfddddddddddddddddddddddddddddddddddddddddddddddf','anh1.jpg','2019-12-09',true);


-- insert bảng role
select * from role;
insert into role(id,name,code,description,status) values
(1,'Guest','G','Tài khoản khách hàng',true),
(2,'ManageTrademark','MT','Quản lý thương hiệu',true),
(3,'ManageProduct','MP','Quản lý sản phẩm',true),
(4,'ManageNews','MN','Quản lý tin tức',true),
(5,'ManageIntroduce','MI','Quản lý giới thiệu',true),
(6,'ManageOrder','MO','Quản lý đơn hàng',true),
(7,'ManageAccount','MA','Quản lý tài khoản',true),
(8,'ManageContact','MC','Quản lý liên hệ (Chủ cửa hàng)',true),
(9,'ManageReport','MR','Quản lý báo cáo (Chủ cửa hàng)',true);

-- insert bảng cart
select * from cart;
insert into cart values
(1,29,2),
(2,29,2);

-- insert admin bảng user
select * from user;


----------------------------------------------------------------------------
SET SQL_SAFE_UPDATES = 0;
delete from bill_detail;
delete from bill;
delete from cart;
delete from product;
delete from brand;
--------------------------------------------------------------------------------------------------------------------
SET SQL_SAFE_UPDATES = 0;
update product 
set image="anh1.jpg"
where image="anh1,jpg";

delete from product where id=7;

select * from product where id_brand=1 and status = true order by name asc;
select * from product where id_brand=1;
select * from product where status=true;
select * from product where lower(name) like lower('%%');
select lower(name) from product;
select * from contact;
----------------------------------------------------------------------------
 select * from user;
 select * from role;
 select * from user_role;
 select * from bill_detail;
 delete from user;
 delete from user_role;
 select * from cart;
 select * from product;
 
 select * from role where code='G';
select * from user where username='dotv' and id!=28;

select * from bill;
select * from bill_detail;
delete from bill_detail;
delete from bill;

