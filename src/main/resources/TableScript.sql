DROP SCHEMA IF EXISTS webapp_db;


 

CREATE SCHEMA webapp_db;

USE webapp_db;

   


 

CREATE TABLE customers (

    customer_id INT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100),

    email VARCHAR(100)

);



 

CREATE TABLE transactions (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    customer_name VARCHAR(100) NOT NULL,

    amount DECIMAL(10, 2) NOT NULL,

    transaction_date DATE NOT NULL

);



 

CREATE TABLE rewards (

    reward_id INT PRIMARY KEY AUTO_INCREMENT,

    transaction_id INT,

    points_earned INT,

    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id)

);




 

INSERT INTO customers (name, email) VALUES

('Alice Johnson', 'alice@example.com'),

('Bob Smith', 'bob@example.com'),

('Charlie Brown', 'charlie@example.com');





 

INSERT INTO transactions (customer_name, amount, transaction_date) VALUES

('Alice', 120.00, '2025-07-10'),

('Alice', 75.00, '2025-08-15'),

('Bob', 200.00, '2025-09-05'),

('Bob', 50.00, '2025-07-20');






 

INSERT INTO rewards (transaction_id, points_earned) VALUES

(1, 90),

(2, 25),

(3, 0),

(4, 250),

(5, 45),

(6, 110);




 

select * from customers;

select * from transactions;

select * from rewards;