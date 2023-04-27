--liquibase formatted sql
--changeset myname:create-multiple-tables splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS order_table
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_date DATE,
    external_id  VARCHAR(36) UNIQUE,
    updated_date DATE,
    identifier VARCHAR(36) UNIQUE,
    username VARCHAR(36),
    order_price double,
    order_created_message VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS perfume
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_date DATE,
    external_id  VARCHAR(36) UNIQUE,
    updated_date DATE,
    name VARCHAR(36) UNIQUE,
    price double,
    category VARCHAR(36),
    promo VARCHAR(36),
    identifier VARCHAR(36) UNIQUE,
    description VARCHAR(255),
    order_id BIGINT
);

CREATE TABLE IF NOT EXISTS user_entity
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_date DATE,
    external_id  VARCHAR(36) UNIQUE,
    updated_date DATE,
    password     VARCHAR(255),
    username     VARCHAR(100) UNIQUE
);

CREATE TABLE IF NOT EXISTS user_entity_roles
(
    user_entity_id INT,
    roles          VARCHAR(255)
);