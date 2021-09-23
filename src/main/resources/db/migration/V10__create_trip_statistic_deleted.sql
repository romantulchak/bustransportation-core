CREATE TABLE IF NOT EXISTS trip_statistic_deleted
(
    id         bigserial    not null primary key,
    name       varchar(100) not null,
    user_id    bigint       not null references users,
    start_date timestamp    not null,
    end_date   timestamp    not null
);

CREATE TABLE IF NOT EXISTS trip_statistic_deleted_stops
(
    trip_statistic_deleted_id bigint       not null references trip_statistic_deleted,
    name                      varchar(125) not null,
    departure                 timestamp    not null,
    is_bus_stop               bool default true,
    street                    varchar(150) not null,
    price                     numeric      not null check ( price >= 0),
    bus_stop_number           numeric      not null
);

