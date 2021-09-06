CREATE TABLE IF NOT EXISTS role
(
    id   bigserial   not null primary key,
    name varchar(35) not null
);

INSERT INTO role
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (3, 'ROLE_MODERATOR') ON CONFLICT DO NOTHING ;

CREATE TABLE IF NOT EXISTS users
(
    id         bigserial    not null primary key,
    username   varchar(40)  not null unique,
    email      varchar(90)  not null unique,
    password   varchar(120) not null,
    first_name varchar(40),
    last_name  varchar(40)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id bigint references users,
    role_id bigint references role,
    PRIMARY KEY (user_id, role_id)
);

ALTER TABLE users ADD COLUMN IF NOT EXISTS is_enabled bool default false;
