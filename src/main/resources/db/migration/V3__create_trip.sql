CREATE TABLE IF NOT EXISTS trip
(
    id              bigserial    not null primary key,
    name            varchar(150) not null,
    bus_id          bigint       null references bus,
    number_of_seats numeric      not null,
    trip_type       varchar(50)  not null,
    creator_id      bigint       not null references users,
    date_start      date         null,
    date_ended      date         null
);

CREATE TABLE IF NOT EXISTS trip_stops
(
    trip_id         bigint       not null references trip,
    name            varchar(125) not null,
    departure       timestamp    not null,
    is_bus_stop     bool default true,
    street          varchar(150) not null,
    price           numeric      not null check ( price >= 0),
    bus_stop_number numeric      not null
);
