insert into genre ( id, name) values (1,'Mistery');
insert into genre ( id, name) values (2,'Fantasy');
insert into genre ( id, name) values (3,'Love');

insert into book(id, name, isbn, genre_id) values(1, 'The ghost', '978-0-13-50', 1);
insert into book(id, name, isbn, genre_id) values(2, 'The candle', '978-0-13-45', 1);

insert into book(id, name, isbn, genre_id) values(3, 'Lord of the Rings', '978-0-13-51', 2);
insert into book(id, name, isbn, genre_id) values(4, 'The human', '978-0-13-52', 2);

insert into book(id, name, isbn, genre_id) values(5, 'The lady', '978-0-13-53', 3);
insert into book(id, name, isbn, genre_id) values(6, 'Love in the mountains', '978-0-13-52', 3);

insert into customer ( id, first_name, last_name) values (1,'Frodo', 'Baggins');
insert into customer ( id, first_name, last_name) values (2,'Frank', 'Castle');
insert into customer ( id, first_name, last_name) values (3,'Matt', 'Murdock');

