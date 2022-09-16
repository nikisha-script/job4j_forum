--liquibase formatted sql

insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

--changeset nikishin:Uinsert_roles
insert into roles (id, role) values (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');