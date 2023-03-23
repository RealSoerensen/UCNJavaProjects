-- drop if necessary, then create the database
use master;
if exists (select * from sys.databases where name='DbFidoFitness')
	drop database DbFidoFitness;
go

create database DbFidoFitness;
go

use DbFidoFitness;

create table member (
	id int primary key identity(1,1),
	name varchar(20) not null,
	phone varchar(10),
	email varchar(30) not null unique
);

create table dog (
	id int primary key identity(1,1),
	chip varchar(20) unique not null,
	name varchar(20) not null,
	feeyear int,
	memberid int not null foreign key references member(id)
)

insert into member(name, phone, email) values ('Joe', '12345678', 'joe@email.com');
insert into member (name, phone, email) values ('Jane', '11111111', 'jane@email.com');
insert into member (name, phone, email) values ('Janice', '22222222', 'janice@email.com');
insert into dog (name, feeyear, chip, memberid ) values ('Fido', 2020, '1234', (select id from member where name = 'Joe'));
insert into dog (name, feeyear, chip, memberid) values ('Rex', 2019, '2345', (select id from member where name = 'Joe'));
insert into dog (name, feeyear, chip, memberid) values ('Rolex', 2020, '3456', (select id from member where name = 'Jane'));
go


--select * from member;
--select * from dog;
