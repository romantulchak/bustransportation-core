CREATE TABLE IF NOT EXISTS bus
(
    id              bigserial   not null primary key,
    name            varchar(40) not null,
    brand           varchar(45) not null,
    number_of_seats numeric     not null,
    user_id         bigint      not null references users
);



