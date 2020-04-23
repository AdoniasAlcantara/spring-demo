create table Customer
(
	id bigint auto_increment,
	name varchar(60) not null,
	email varchar(255) not null,
	phone varchar(20) not null,
	constraint CustomerPk primary key (id)
);



