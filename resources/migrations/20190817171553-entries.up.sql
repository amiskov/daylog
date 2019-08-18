create table entries (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    deed_id BIGINT,
    grade_id BIGINT,
    timestamp_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP)
);
