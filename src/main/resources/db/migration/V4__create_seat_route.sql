CREATE TABLE IF NOT EXISTS seat
(
    id          bigserial unique not null primary key,
    seat_number numeric          not null,
    trip_id     bigint           not null references trip
);

CREATE TABLE IF NOT EXISTS route
(
    id             bigserial    not null primary key,
    departure_from varchar(100) not null,
    arrival_to     varchar(100) not null,
    departure_time timestamp    not null,
    arrival_time   timestamp    not null,
    price          numeric      not null check ( price >= 0 ),
    trip_id        bigint       not null references trip,
    entrance_stop  numeric      null,
    exit_stop      numeric      null
);
