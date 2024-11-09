
create table persone (eta integer, id integer not null, telefono varchar(18), cognome varchar(255), indirizzo varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
create table persone_seq (next_val bigint) engine=InnoDB;
insert into persone_seq values ( 1 );
create table utenti (id integer not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table utenti_seq (next_val bigint) engine=InnoDB;
insert into utenti_seq values ( 1 );
alter table persone add constraint UKpbni14bjkielwagswv2hchsi5 unique (telefono);
alter table utenti add constraint UKtn8mwk6h2wn28yyj7fco65gls unique (username);
