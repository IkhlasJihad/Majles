create role Volunteer;
create role adminstrator;
grant CONNECT on database "Majles" to adminstrator, Volunteer;
grant usage on schema "majles" to adminstrator, Volunteer;
create user fadi with login password 'adminfadi' createrole;
CREATE user emyw WITH LOGIN PASSWORD '326';
CREATE user mahmoud86 WITH LOGIN PASSWORD '428'; 
grant adminstrator to fadi;
grant Volunteer to emyw,mahmoud86;

create table region(
	region_code varchar(5) primary key,
	name varchar(20)
);

CREATE SEQUENCE student_seq START 1401;
create table student(
	id varchar(8) default nextval('student_seq') primary key,
	first_name varchar(20) not null,
	snd_name varchar(20) not null,
	third_name varchar(20) not null,
	last_name varchar(20) not null,
	gender varchar(4) default null,
	region_code varchar(5) references region(region_code) on delete cascade not null
);
create table std_phone(
	id varchar(8) references student(id) on delete cascade not null,
	phone varchar(14),
	primary key(id, phone)
);
create table sheikh(
	sh_id varchar(6) primary key,
	name varchar(20) not null,
	email text,
	gender varchar(4) default null,
	region_code varchar(5) references region(region_code) on delete cascade 
);
create table volunteer(
	username varchar(10) primary key,
	name varchar(20) not null,
	region_code varchar(5) references region(region_code) on delete cascade 
);
create table vol_phone(
	username varchar(10) references volunteer(username) on delete cascade,
	phone varchar(14),
	primary key(username, phone)
);

CREATE SEQUENCE maj_id_seq START 101;
create table majles(
	maj_id varchar(8) default nextval('maj_id_seq')  primary key,
	sh_id varchar(6) references sheikh(sh_id) on delete cascade  not null,
	place text not null,
	name text not null,
	subject text
);

CREATE SEQUENCE book_seq START 10;
create table book(
	book_id varchar(5) default nextval('book_seq') primary key,
	title text not null,
	buy_from text,
	price numeric(10,2)
);
CREATE sequence cat_seq START 1;
create table category(
	category_id varchar(5) default nextval('cat_seq') primary key,
	name varchar(20)	
);
CREATE sequence auth START 1 ;
create table author(
	author_id varchar(5) default nextval('auth') primary key,
	name text
);
create table author_book(
	author_id varchar(5) references author(author_id) on delete cascade ,
	book_id varchar(5) references book(book_id) on delete cascade ,
	primary key(book_id, author_id)
);
create table category_book(
	category_id varchar(5) references category(category_id) on delete cascade ,
	book_id varchar(5) references book(book_id) on delete cascade ,
	primary key(book_id, category_id)
);
create table lecture(
	lec_id varchar(5) unique ,
	maj_id varchar(8) references majles(maj_id) on delete cascade ,
	title text,
	"date" date,
	"time" varchar(20),
	place text,
	primary key(lec_id,maj_id)
);

create table joins(
	id varchar(8) references student(id) on delete cascade ,
	maj_id varchar(8) references majles(maj_id) on delete cascade ,
	join_in date,
	primary key(id,maj_id)
);

create table responsible_for(
	username varchar(10) references volunteer(username) ,
	maj_id varchar(8) references majles(maj_id) on delete cascade ,	
	primary key(maj_id, username)	
);
create table attends(
	id varchar(8) references student(id) on delete cascade ,
	lec_id varchar(5) references lecture(lec_id) on delete cascade ,
	primary key(id, lec_id)
);

create table elucidates(
	maj_id varchar(8) references majles(maj_id) on delete cascade ,
	book_id varchar(5) references book(book_id) on delete cascade ,
	primary key(maj_id, book_id )
);

grant all  on joins,lecture,attends,student to Volunteer;
grant select on region, volunteer, majles, book, std_phone,responsible_for, lecreport, sheikh, commitment,stdattends,elucidates to Volunteer;
grant all  
on majles, volunteer, sheikh, responsible_for, elucidates, book, author_book, category_book, author, vol_phone, student,std_phone
to adminstrator;
grant select on region to adminstrator;
grant select, update on sequence  student_seq, book_seq, maj_id_seq,auth to Volunteer, adminstrator;
