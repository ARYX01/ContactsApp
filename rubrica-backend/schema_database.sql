create table persone (eta integer, id integer not null auto_increment, telefono varchar(18), cognome varchar(255), indirizzo varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
create table utenti (id integer not null auto_increment, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
alter table persone add constraint UKpbni14bjkielwagswv2hchsi5 unique (telefono);
alter table utenti add constraint UKtn8mwk6h2wn28yyj7fco65gls unique (username);
