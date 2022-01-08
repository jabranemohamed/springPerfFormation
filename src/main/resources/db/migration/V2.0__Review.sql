create table Review
(
    id       int8 not null,
    author   varchar(255),
    content  varchar(255),
    movie_id int8,
    rating   smallint,
    primary key (id)
);
alter table Review
    add constraint fk_review_movie foreign key (movie_id) references Movie;

