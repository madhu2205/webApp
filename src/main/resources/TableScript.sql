DROP SCHEMA IF EXISTS webapp_db;


 

CREATE SCHEMA webapp_db;

USE webapp_db;

   


 

CREATE TABLE customer (

    customer_id VARCHAR(10) PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL,

    join_date DATE NOT NULL

);


 

CREATE TABLE transaction (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    customer_id VARCHAR(10),

    amount DECIMAL(10, 2) NOT NULL,

    date DATE NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)

);


 

INSERT INTO customer (customer_id, name, email, join_date) VALUES

('C001', 'Alice Johnson', 'alice@example.com', '2025-01-15'),

('C002', 'Bob Smith', 'bob@example.com', '2025-02-20'),

('C003', 'Charlie Lee', 'charlie@example.com', '2025-03-10');


 

INSERT INTO transaction (customer_id, amount, date) VALUES

('C001', 120.00, '2025-08-15'),

('C001', 75.00, '2025-09-10'),

('C001', 45.00, '2025-10-05'),

('C002', 200.00, '2025-09-20'),

('C002', 99.00, '2025-10-01'),

('C003', 130.00, '2025-08-25'),

('C003', 60.00, '2025-09-15'),

('C003', 110.00, '2025-10-10');

select * from transaction;

select * from customer;