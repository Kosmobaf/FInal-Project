drop database IF EXISTS provider;
create DATABASE IF NOT EXISTS provider;
Use provider;
create table services
(
    id          int auto_increment
        primary key,
    nameService varchar(255) not null,
    constraint nameService
        unique (nameService)
);

create table tariff
(
    id         int auto_increment
        primary key,
    id_service int            not null,
    nameTariff varchar(255)   not null,
    cost       decimal(65, 2) not null,
    constraint tariff_services_id_fk
        foreign key (id_service) references services (id)
);

create table users
(
    id       int auto_increment
        primary key,
    login    varchar(255)   not null,
    password varchar(255)   not null,
    typeUser varchar(255)   not null,
    cash     decimal(65, 2) not null,
    constraint login
        unique (login)
);

create table users_tariff
(
    id        int auto_increment
        primary key,
    user_id   int                  null,
    tariff_id int                  null,
    isActive  tinyint(1) default 0 null,
    dateAdd   datetime             null,
    constraint user_id
        unique (user_id, tariff_id),
    constraint users_tariff_tariff_id_fk
        foreign key (tariff_id) references tariff (id),
    constraint users_tariff_users_id_fk
        foreign key (user_id) references users (id)
);

insert into provider.services (`id`, `nameService`)
VALUES (1, 'telephone');
insert into provider.services (`id`, `nameService`)
VALUES (2, 'internet');
insert into provider.services (`id`, `nameService`)
VALUES (3, 'cabel');
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (1, 2, 'min', 100.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (2, 2, 'middle', 200.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (3, 2, 'max', 300.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (4, 1, 'hour', 100.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (5, 1, 'day', 110.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (6, 1, 'holiday', 120.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (7, 1, 'manths', 150.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (8, 3, 'low', 250.00);
insert into provider.tariff (`id`, `id_service`, `nameTariff`, `cost`)
VALUES (9, 3, 'fast', 300.00);