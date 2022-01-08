create table Genre
(
    id   int8 not null,
    name varchar(255) UNIQUE,
    primary key (id)
);
create table movie_genre
(
    movie_id int8 not null,
    genre_id int8 not null,
    primary key (movie_id, genre_id)
);
alter table movie_genre
    add constraint fk_moviegenre_genre foreign key (genre_id) references Genre;
alter table movie_genre
    add constraint fk_moviegenre_movie foreign key (movie_id) references Movie;
