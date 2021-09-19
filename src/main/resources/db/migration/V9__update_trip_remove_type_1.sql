ALTER TABLE trip ADD COLUMN IF NOT EXISTS remove_type varchar(30) not null default 'SAVED'
