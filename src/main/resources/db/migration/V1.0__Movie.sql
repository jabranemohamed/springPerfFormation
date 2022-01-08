create sequence hibernate_sequence start 1 increment 1;
create table Movie
(
    id            int8         not null,
    certification int4,
    description   varchar(255),
    name          varchar(255) not null,
    primary key (id),
    version       smallint default 0,
    director varchar(255),
    image varchar(255)
);
