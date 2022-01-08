truncate table Review,Movie,Genre,Movie_Genre,Movie_Details,Award;

insert into Genre (name,id) select 'Genre' || generate_series(1,100), generate_series(-100,-1);

insert into Movie (name,certification, version,id) select 'Movie' || generate_series(1,1000000), 1,0,generate_series(-1000000,-1);

insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*32)-1::int AS id FROM  generate_series (-1000000,-1) AS x(n) ;
insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*33)-34::int AS id FROM  generate_series (-1000000,-1) AS x(n) ;
insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*32)-68::int AS id FROM  generate_series (-1000000,-1) AS x(n) ;

insert into Movie_Details  (movie_id,plot) select generate_series(-1000000,-1), MD5(RANDOM()::TEXT);

insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,1000000), 'rewiew1-'|| generate_series(1,1000000), generate_series(-1000000,-1), generate_series(-1000000,-1);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,1000000), 'rewiew2-'|| generate_series(1,1000000), generate_series(-1000000,-1), generate_series(-2000000,-1000001);

insert into Award (name,year,movie_id,id) select 'Best Actor in a Leading Role' || generate_series(1,1000000), -(random()*41)+1980::int,generate_series(-1000000,-1),generate_series(-1000000,-1);
insert into Award (name,year,movie_id,id) select 'Best Director' || generate_series(1,1000000), -(random()*41)+1980::int,generate_series(-1000000,-1),generate_series(-2000000,-1000001);
insert into Award (name,year,movie_id,id) select 'Best Actor in a Supporting Role' || generate_series(1,1000000), -(random()*41)+1980::int,generate_series(-1000000,-1),generate_series(-3000000,-2000001);
insert into Award (name,year,movie_id,id) select 'Best Art Direction' || generate_series(1,1000000), -(random()*41)+1980::int,generate_series(-1000000,-1),generate_series(-4000000,-3000001);
insert into Award (name,year,movie_id,id) select 'Best Sound' || generate_series(1,1000000), -(random()*41)+1980::int,generate_series(-1000000,-1),generate_series(-5000000,-4000001);

