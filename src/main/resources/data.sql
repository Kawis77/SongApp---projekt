insert into user (id, password, role, username) VALUES (1, '{bcrypt}$2a$10$yAcwG1b6g2uxq/.wkTliZO3sF3YclYdaFlRTcLSMWF84e4vV1auTm', 'ROLE_USER', 'admin');

insert into chords (id, name) values (1, 'A');
insert into chords (id, name) values (2, 'C');
insert into chords (id, name) values (3, 'G');
insert into chords (id, name) values (4, 'E');

insert into songs (id , name ,author , text ,user_id) values (1 , 'TNT' , 'AC/DC' , '...', 1 );
insert into songs (id , name ,author , text ,user_id) values (2 , 'Forever Young' , 'Alphalive' , '...', 1 );
insert into songs (id , name ,author , text ,user_id) values (3 , 'Venus' , 'Shocking Blue' , '...', 1 );
insert into songs (id , name ,author , text,user_id) values (4 , 'Take My Breath' , 'Berlin' , '...' , 1 );

insert into songs_chords(songs_id , chords_id) values (1 , 1);
insert into songs_chords(songs_id , chords_id) values (2 , 2);
insert into songs_chords(songs_id , chords_id) values (3 , 3);
insert into songs_chords(songs_id , chords_id) values (4 , 4);