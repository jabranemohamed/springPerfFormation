create table Award
(
    id       int8 not null,
    name     varchar(255),
    year     smallint,
    movie_id int8,
    primary key (id)
);
alter table Award
    add constraint fk_award_movie foreign key (movie_id) references Movie;
