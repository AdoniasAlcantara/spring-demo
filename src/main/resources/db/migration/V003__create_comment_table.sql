create table Comment
(
	id bigint auto_increment,
	description text not null,
	sendAt datetime not null,
	orderId bigint not null,
	constraint CommentPk primary key (id),
	constraint CommentOrderFk foreign key (orderId) references `Order` (id)
);
