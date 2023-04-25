CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE account_type (
    id SERIAL PRIMARY KEY,
    detail_type VARCHAR(255) NOT NULL
);

CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    initial_balance NUMERIC(15, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    type_id INT NOT NULL REFERENCES account_type(id),
    customer_id INT NOT NULL REFERENCES customer(id)
);

CREATE TABLE transaction_type (
    id SERIAL PRIMARY KEY,
    detail_type VARCHAR(255) NOT NULL
);

CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    transaction_type_id INT NOT NULL REFERENCES transaction_type(id),
    value NUMERIC(15, 2) NOT NULL,
    balance NUMERIC(15, 2) NOT NULL,
    account_id INT NOT NULL REFERENCES account(id)
);
