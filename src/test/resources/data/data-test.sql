truncate table Review,Movie,Genre,Movie_Genre,Movie_Details,Award;

insert into Movie (name,certification, id,version) values ('Inception', 1,-1,0);
insert into Movie (name,certification, id,version) values ('Memento', 2,-2,0);

insert into Review (author, content, movie_id, id) values ('max', 'au top !', -1, -1);
insert into Review (author, content, movie_id, id) values ('ernest', 'bof bof', -1, -2);

insert into Genre (name, id) values ('Action', -1);
insert into Movie_Genre (movie_id,genre_id) values (-1,-1);

insert into Award (name, year, movie_id, id) values ('Best Achievement in Cinematography', 2011, -1, -1);
insert into Award (name, year, movie_id, id) values ('Best Achievement in Visual Effects', 2011, -1, -2);
insert into Award (name, year, movie_id, id) values ('Best Achievement in Sound Editing', 2011, -1, -3);
insert into Award (name, year, movie_id, id) values ('Best Achievement in Sound Mixing', 2011, -1, -4);
