create table movie_details
(
    plot     varchar(4000),
    movie_id int8 not null,
    primary key (movie_id)
);
alter table movie_details
    add constraint fk_moviedetails_movie foreign key (movie_id) references Movie;
