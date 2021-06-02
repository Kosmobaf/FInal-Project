DROP DATABASE IF EXISTS provider;
CREATE DATABASE IF NOT EXISTS provider;
USE provider;
CREATE TABLE services
(
    id          INT AUTO_INCREMENT
        PRIMARY KEY,
    nameService VARCHAR(255) NOT NULL,
    CONSTRAINT nameService
        UNIQUE (nameService)
);

CREATE TABLE tariff
(
    id         INT AUTO_INCREMENT
        PRIMARY KEY,
    id_service INT            NOT NULL,
    nameTariff VARCHAR(255)   NOT NULL,
    cost       DECIMAL(65, 2) NOT NULL,
    CONSTRAINT tariff_services_id_fk
        FOREIGN KEY (id_service) REFERENCES services (id)
);

CREATE TABLE users
(
    id       INT AUTO_INCREMENT
        PRIMARY KEY,
    login    VARCHAR(255)   NOT NULL,
    password VARCHAR(255)   NOT NULL,
    typeUser VARCHAR(255)   NOT NULL,
    cash     DECIMAL(65, 2) NOT NULL,
    status   VARCHAR(255)   NOT NULL,
    CONSTRAINT login
        UNIQUE (login)
);

CREATE TABLE users_orders
(
    id        INT AUTO_INCREMENT
        PRIMARY KEY,
    user_id   INT          NOT NULL,
    tariff_id INT          NOT NULL,
    status    VARCHAR(255) NOT NULL,
    dateAdd   DATETIME     NOT NULL,
    CONSTRAINT user_id
        UNIQUE (user_id, tariff_id),
    CONSTRAINT users_orders_tariff_id_fk
        FOREIGN KEY (tariff_id) REFERENCES tariff (id),
    CONSTRAINT users_orders_users_id_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO provider.services (`id`, `nameService`)
VALUES (1, 'Telephone');
INSERT INTO provider.services (`id`, `nameService`)
VALUES (2, 'Internet');
INSERT INTO provider.services (`id`, `nameService`)
VALUES (3, 'Cabel');
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (1, 2, 'Min', 100.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (2, 2, 'Middle', 200.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (3, 2, 'Max', 300.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (4, 1, 'Hour', 100.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (5, 1, 'Day', 110.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (6, 1, 'Holiday', 120.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (7, 1, 'Months', 150.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (8, 3, 'Low', 250.00);
INSERT INTO provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (9, 3, 'Fast', 300.00);
INSERT INTO users(id, login, password, typeUser, cash, status)
VALUES (1, 'Admin@asd', 1, 'admin', 0, 'active');
INSERT INTO users(id, login, password, typeUser, cash, status)
VALUES (2, 'User@asd', 1, 'user', 0, 'active');
INSERT INTO users(id, login, password, typeUser, cash, status)
VALUES (3, 'User@asd2', 1, 'user', 0, 'active');