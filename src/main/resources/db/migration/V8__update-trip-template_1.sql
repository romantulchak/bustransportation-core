CREATE TABLE IF NOT EXISTS trip_template_stops
(
    trip_template_id bigint       not null references trip_template,
    name             varchar(125) not null,
    departure        date         not null,
    is_bus_stop      bool default true,
    street           varchar(150) not null,
    price            numeric      not null check ( price >= 0),
    bus_stop_number  numeric      not null
)
