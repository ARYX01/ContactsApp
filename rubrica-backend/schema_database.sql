create table persone (eta integer, id integer not null, telefono varchar(18), cognome varchar(255), indirizzo varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
create table persone_seq (next_val bigint) engine=InnoDB;
insert into persone_seq values ( 1 );
alter table persone add constraint UKpbni14bjkielwagswv2hchsi5 unique (telefono);
