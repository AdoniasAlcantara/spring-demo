create table `Order`
(
	id bigint auto_increment,
	description text not null,
	price decimal(10, 2) not null,
	status char(16) not null,
	start datetime not null,
	end datetime,
	customerId bigint not null,
	constraint OrderPk primary key (id),
	constraint OrderCustomerFk foreign key (customerId) references Customer (id)
);
