CREATE TABLE IF NOT EXISTS booking
(
    id                    bigserial not null primary key,
    user_id               bigint    not null references users,
    total_number_of_seats numeric   not null check ( total_number_of_seats > 0)
);

CREATE TABLE IF NOT EXISTS ticket
(
    id         bigserial    not null primary key,
    first_name varchar(90)  not null,
    last_name  varchar(90)  not null,
    email      varchar(150) not null,
    seat_id    bigint       not null references seat,
    booking_id bigint       not null references booking,
    route_id   bigint       not null references route
);
