--liquibase formatted sql

--changeset nikishin:Ucreate_posts
create table posts (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);

--changeset nikishin:Ucreate_roles
create table roles (
    id serial primary key,
    role VARCHAR(50) NOT NULL unique
);

--changeset nikishin:Ucreate_users
create table users (
    id serial primary key,
    username VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    role_id int not null references roles(id)
);

--changeset nikishin:Ucreate_messages
create table messages(
    id serial primary key,
    text text,
    post_id integer references posts(id)
)

--changeset nikishin:Ucreate_posts_messages
create table posts_messages (
    post_id integer references posts(id),
    messages_id integer references messages(id)
);
