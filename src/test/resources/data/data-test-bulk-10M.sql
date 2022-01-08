truncate table Review,Movie,Genre,Movie_Genre,Movie_Details,Award;

insert into Genre (name,id) select 'Genre' || generate_series(1,100), generate_series(-100,-1);

insert into Movie (name,certification, version,id) select 'Movie' || generate_series(1,10000000), 1,0,generate_series(-10000000,-1);

insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*32)-1::int AS id FROM  generate_series (-10000000,-1) AS x(n) ;
insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*33)-34::int AS id FROM  generate_series (-10000000,-1) AS x(n) ;
insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*32)-68::int AS id FROM  generate_series (-10000000,-1) AS x(n) ;

insert into Movie_Details  (movie_id,plot) select generate_series(-10000000,-1), MD5(RANDOM()::TEXT);

insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000000), 'rewiew1-'|| generate_series(1,10000000), generate_series(-10000000,-1), generate_series(-10000000,-1);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000000), 'rewiew2-'|| generate_series(1,10000000), generate_series(-10000000,-1), generate_series(-20000000,-10000001);

insert into Award (name,year,movie_id,id) select 'Best Actor in a Leading Role' || generate_series(1,10000000), (random()*121)+1900::int,generate_series(-10000000,-1),generate_series(-10000000,-1);
insert into Award (name,year,movie_id,id) select 'Best Director' || generate_series(1,10000000), (random()*121)+1900::int,generate_series(-10000000,-1),generate_series(-20000000,-10000001);
insert into Award (name,year,movie_id,id) select 'Best Actor in a Supporting Role' || generate_series(1,10000000), (random()*41)+1900::int,generate_series(-10000000,-1),generate_series(-30000000,-20000001);
insert into Award (name,year,movie_id,id) select 'Best Art Direction' || generate_series(1,10000000), (random()*121)+1900::int,generate_series(-10000000,-1),generate_series(-40000000,-30000001);
insert into Award (name,year,movie_id,id) select 'Best Sound' || generate_series(1,10000000), (random()*121)+1900::int,generate_series(-10000000,-1),generate_series(-50000000,-40000001);

