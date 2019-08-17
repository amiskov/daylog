create table entries (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    deed_id bigint,
    grade_id bigint,
    datetime_start timestamp
);
