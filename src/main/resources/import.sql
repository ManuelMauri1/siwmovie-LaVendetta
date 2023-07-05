/*INSERT ARTIST*/
insert into artist (id, nome, cognome, data_nascita) values(nextval('hibernate_sequence'), 'Gabriele', 'Mainetti', '1976-11-07');
insert into artist (id, nome, cognome, data_nascita) values(nextval('hibernate_sequence'), 'Alex', 'Infascelli', '1968-04-01');
insert into artist (id, nome, cognome, data_nascita) values(nextval('hibernate_sequence'), 'Francesco', 'Totti', '1976-09-27');
insert into artist (id, nome, cognome, data_nascita) values(nextval('hibernate_sequence'), 'Tommy Lee', 'Jones', '1946-09-15');
insert into artist (id, nome, cognome, data_nascita) values(nextval('hibernate_sequence'), 'Will', 'Smith', '1968-09-25');
insert into artist (id, nome, cognome, data_nascita) values(nextval('hibernate_sequence'), 'Woody', 'Harrelson', '1961-07-23');

/*INSERT USER-CREDENTIALS*/
insert into users (id, nome, cognome, email) values(1, 'Admin', 'Admin', 'admin@gmail.com');
insert into credentials (id, user_id, password, ruolo, username) values(nextval('hibernate_sequence'), 1, '$2a$10$mUJ7goVVMtAXrikFD9jOZe36XoXJGtxmrX9dvabqDr2tsckn6zUZa', 'ADMIN', 'Admin');

