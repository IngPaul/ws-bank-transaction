CREATE TABLE IF NOT EXISTS customer (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS account_type (
    account_type_id SERIAL PRIMARY KEY,
    detail_type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
    account_id SERIAL PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    initial_balance NUMERIC(15, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    account_type_id INT NOT NULL REFERENCES account_type(account_type_id),
    customer_id INT NOT NULL REFERENCES customer(customer_id)
);

CREATE TABLE IF NOT EXISTS transaction_type (
    transaction_type_id SERIAL PRIMARY KEY,
    detail_type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction (
    transaction_id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    transaction_type_id INT NOT NULL REFERENCES transaction_type(transaction_type_id),
    value NUMERIC(15, 2) NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    account_id INT NOT NULL REFERENCES account(account_id)
);

INSERT INTO account_type (detail_type) SELECT 'AHORRO' WHERE NOT EXISTS (SELECT * FROM account_type WHERE detail_type = 'AHORRO');
INSERT INTO account_type (detail_type) SELECT 'CORRIENTE' WHERE NOT EXISTS (SELECT * FROM account_type WHERE detail_type = 'CORRIENTE');


INSERT INTO transaction_type (detail_type)
    SELECT 'Depósito'
    WHERE NOT EXISTS (SELECT * FROM transaction_type WHERE detail_type = 'Depósito');
INSERT INTO transaction_type (detail_type)
    SELECT 'Retiro'
    WHERE NOT EXISTS (SELECT * FROM transaction_type WHERE detail_type = 'Retiro');
INSERT INTO transaction_type (detail_type)
    SELECT 'Transferencia'
    WHERE NOT EXISTS (SELECT * FROM transaction_type WHERE detail_type = 'Transferencia');



INSERT INTO customer (name, gender, age, identification, address, phone, password, status)
SELECT 'Jose Lema', 'Male', 35, '1234567890', 'Otavalo sn y principal', '098254785', '1234', 'ENABLE'
WHERE NOT EXISTS (SELECT 1 FROM customer WHERE name = 'Jose Lema');

INSERT INTO customer (name, gender, age, identification, address, phone, password, status)
SELECT 'Marianela Montalvo', 'Female', 25, '0987654321', 'Amazonas y NNUU', '097548965', '5678', 'ENABLE'
WHERE NOT EXISTS (SELECT 1 FROM customer WHERE name = 'Marianela Montalvo');

INSERT INTO customer (name, gender, age, identification, address, phone, password, status)
SELECT 'Juan Osorio', 'Male', 30, '9876543210', '13 junio y Equinoccial', '098874587', '1245', 'ENABLE'
WHERE NOT EXISTS (SELECT 1 FROM customer WHERE name = 'Juan Osorio');



INSERT INTO account (account_number, initial_balance, status, account_type_id, customer_id)
SELECT '478758', 2000.00, 'ENABLE', account_type_id, customer_id
FROM account_type, customer WHERE account_type_id = '1' AND name = 'Jose Lema'
AND NOT EXISTS (SELECT 1 FROM account WHERE account_number = '478758');

INSERT INTO account (account_number, initial_balance, status, account_type_id, customer_id)
SELECT '225487', 100.00, 'ENABLE', account_type_id, customer_id
FROM account_type, customer WHERE account_type_id = '2' AND name = 'Marianela Montalvo'
AND NOT EXISTS (SELECT 1 FROM account WHERE account_number = '225487');

INSERT INTO account (account_number, initial_balance, status, account_type_id, customer_id)
SELECT '495878', 0.00, 'ENABLE', account_type_id, customer_id
FROM account_type, customer WHERE account_type_id = '1' AND name = 'Juan Osorio'
AND NOT EXISTS (SELECT 1 FROM account WHERE account_number = '495878');

INSERT INTO account (account_number, initial_balance, status, account_type_id, customer_id)
SELECT '496825', 540.00, 'ENABLE', account_type_id, customer_id
FROM account_type, customer WHERE account_type_id = '1' AND name = 'Marianela Montalvo'
AND NOT EXISTS (SELECT 1 FROM account WHERE account_number = '496825');
