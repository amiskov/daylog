create table deeds (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title varchar(255)
);