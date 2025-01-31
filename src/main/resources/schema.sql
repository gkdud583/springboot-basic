SET MODE MYSQL;
CREATE TABLE vouchers
(
    voucher_id int PRIMARY KEY auto_increment,
    voucher_type varchar(20) NOT NULL,
    discount_amount int NOT NULL,
    created_at   datetime(6) NOT NULL,
    updated_at   datetime(6)  DEFAULT NULL
);

CREATE TABLE customers
(
    customer_id int PRIMARY KEY auto_increment,
    name varchar(20) NOT NULL,
    created_at   datetime(6) NOT NULL,
    updated_at   datetime(6)  DEFAULT NULL
);