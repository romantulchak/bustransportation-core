CREATE TABLE IF NOT EXISTS trip_template
(
    id        bigserial    not null primary key,
    name      varchar(120) not null,
    bus_id    bigint       not null references bus,
    user_id   bigint       not null references users,
    trip_type varchar(120) not null
)
