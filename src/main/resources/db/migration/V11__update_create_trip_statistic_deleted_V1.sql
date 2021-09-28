ALTER TABLE trip_statistic_deleted ADD COLUMN IF NOT EXISTS remove_date timestamp not null default now();
