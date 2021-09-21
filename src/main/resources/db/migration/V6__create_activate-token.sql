CREATE TABLE IF NOT EXISTS activate_token
(
    id        bigserial    not null primary key,
    token     varchar(255) not null unique,
    timestamp timestamp    not null,
    expire_at timestamp    not null,
    user_id   bigint       not null references users
)
