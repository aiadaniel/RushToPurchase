
CREATE TABLE if not exists `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 comment='�����û���';


create table if not exists goods (
goods_id bigint primary key not null auto_increment comment '��Ʒid',
goods_name varchar(255) not null comment '��Ʒ��',
inventory int not null comment '���',
start_time timestamp not null comment '������ʼʱ��',
end_time timestamp not null comment '��������ʱ��',
create_time timestamp not null comment '����ʱ��',
key idx_start_time (start_time),
key idx_end_time (end_time),
key idx_create_time (create_time)
)engine=innodb default charset=utf8 comment='������Ʒ����';

insert into goods(goods_name,inventory,start_time,end_time) values 
('998����iphone8',1000,'2017-06-05 00:00:00','2017-06-06 00:00:00'),
('250����BMW X6',500,'2017-06-05 00:00:00','2017-06-06 00:00:00'),
('141��������������',600,'2017-06-05 00:00:00','2017-06-06 00:00:00'),
('131����������',300,'2017-06-05 00:00:00','2017-06-06 00:00:00');

create table if not exists orders (
goods_id bigint not null comment '��Ʒid',
user_phone bigint not null comment '�û��ֻ�',
state int(1) not null default -1 comment '��Ʒ״̬��-1����Ч,0���ɹ�,1����Ч��2�������',
create_time timestamp not null comment '����ʱ��',
primary key (goods_id,user_phone),
key idx_create_time (create_time)
)engine=innodb default charset=utf8 comment='������ϸ��';
