truncate table Review,Movie,Genre,Movie_Genre,Movie_Details,Award;

insert into Genre (name,id) select 'Genre' || generate_series(1,100), generate_series(-100,-1);

insert into Movie (name,certification, version,id) select 'Movie' || generate_series(1,10000), 1,0,generate_series(-10000,-1);

insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*32)-1::int AS id FROM  generate_series (-10000,-1) AS x(n) ;
insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*33)-34::int AS id FROM  generate_series (-10000,-1) AS x(n) ;
insert into Movie_Genre (movie_id,genre_id) select  n,-(random()*32)-68::int AS id FROM  generate_series (-10000,-1) AS x(n) ;

insert into Movie_Details  (movie_id,plot) select generate_series(-10000,-1), MD5(RANDOM()::TEXT);

insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew1-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-10000,-1);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew2-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-20000,-10001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew3-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-30000,-20001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew4-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-40000,-30001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew5-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-50000,-40001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew6-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-60000,-50001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew7-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-70000,-60001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew8-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-80000,-70001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew9-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-90000,-80001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew10-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-100000,-90001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew11-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-110000,-100001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew12-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-120000,-110001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew13-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-130000,-120001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew14-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-140000,-130001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew15-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-150000,-140001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew16-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-160000,-150001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew17-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-170000,-160001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew18-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-180000,-170001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew19-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-190000,-180001);
insert into Review (author, content, movie_id, id) select 'author' || generate_series(1,10000), 'rewiew20-'|| generate_series(1,10000), generate_series(-10000,-1), generate_series(-200000,-190001);

insert into Award (name,year,movie_id,id) select 'Best Actor in a Leading Role' || generate_series(1,10000), (random()*41)+1980::int,generate_series(-10000,-1),generate_series(-10000,-1);
insert into Award (name,year,movie_id,id) select 'Best Director' || generate_series(1,10000), (random()*41)+1980::int,generate_series(-10000,-1),generate_series(-20000,-10001);
insert into Award (name,year,movie_id,id) select 'Best Actor in a Supporting Role' || generate_series(1,10000), (random()*41)+1980::int,generate_series(-10000,-1),generate_series(-30000,-20001);
insert into Award (name,year,movie_id,id) select 'Best Art Direction' || generate_series(1,10000), (random()*41)+1980::int,generate_series(-10000,-1),generate_series(-40000,-30001);
insert into Award (name,year,movie_id,id) select 'Best Sound' || generate_series(1,10000), (random()*41)+1980::int,generate_series(-10000,-1),generate_series(-50000,-40001);

