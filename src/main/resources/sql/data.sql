create table customer {
	id bigint(20) not null auto_increment,
	name varchar(255) default null,
	contact varchar(255) default null,
	telephone varchar(255) default null,
	email varchar(255) default null,
	remark text,
	primary key ('id')	
} ENGINE = InnoDB DEFAULT CHARSET = utf8

insert into customer values ('1', 'customer1', 'Jack', '13889259343', '952327407@qq.com', null);
insert into customer values ('2', 'customer2', 'Rose', '13889259344', '952327408@qq.com', null);