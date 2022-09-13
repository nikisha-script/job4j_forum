create table posts (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);

insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

CREATE TABLE roles (
    id serial primary key,
    role VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
    id serial primary key,
    username VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    role_id int not null references roles(id)
);

create table messages(
    id serial primary key,
    text text,
    post_id integer references posts(id)
)

create table if not exists posts_messages (
    post_id integer references posts(id),
    messages_id integer references messages(id)
);

insert into roles (id, role) values (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');