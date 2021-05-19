DROP DATABASE IF EXISTS provider;
CREATE DATABASE IF NOT EXISTS provider;
Use provider;
CREATE TABLE if NOT EXISTS provider.users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    login    VARCHAR(255)   NOT NULL UNIQUE,
    password VARCHAR(255)   NOT NULL,
    typeUser VARCHAR(255)   NOT NULL,
    cash     DOUBLE(255, 2) NOT NULL
);
CREATE TABLE if NOT EXISTS provider.services
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nameService VARCHAR(255) NOT NULL

);
CREATE TABLE if NOT EXISTS provider.tariff
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nameService VARCHAR(255)   NOT NULL,
    nameTariff  VARCHAR(255)   NOT NULL,
    cost        DOUBLE(255, 2) NOT NULL
);
CREATE TABLE if NOT EXISTS provider.users_services_tariff
(
    user_id     INT REFERENCES provider.users (id) ON DELETE CASCADE,
    services_id INT REFERENCES provider.services (id) ON DELETE CASCADE,
    tariff_id   INT REFERENCES provider.tariff (id) ON DELETE CASCADE,
    UNIQUE (user_id, services_id, tariff_id)
);
INSERT INTO provider.services (`id`, `nameService`)
VALUES (1, 'telephone');
INSERT INTO provider.services (`id`, `nameService`)
VALUES (2, 'internet');
INSERT INTO provider.services (`id`, `nameService`)
VALUES (3, 'cabel');
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (1, 'internet', 'min', 100.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (2, 'internet', 'middle', 200.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (3, 'internet', 'max', 300.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (4, 'telephone', 'hour', 100.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (5, 'telephone', 'day', 110.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (6, 'telephone', 'holiday', 120.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (7, 'telephone', 'manths', 150.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (8, 'cabel', 'low', 250.00);
INSERT INTO provider.tariff (`id`, `nameService`, `nameTariff`, `cost`)
VALUES (9, 'cabel', 'fast', 300.00);