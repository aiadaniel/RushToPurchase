
CREATE TABLE if not exists `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 comment='测试用户表';


create table if not exists goods (
goods_id bigint primary key not null auto_increment comment '商品id',
goods_name varchar(255) not null comment '商品名',
inventory int not null comment '库存',
start_time timestamp not null comment '抢购开始时间',
end_time timestamp not null comment '抢购结束时间',
create_time timestamp not null comment '创建时间',
key idx_start_time (start_time),
key idx_end_time (end_time),
key idx_create_time (create_time)
)engine=innodb default charset=utf8 comment='抢购商品库存表';

insert into goods(goods_name,inventory,start_time,end_time) values 
('998抢购iphone8',1000,'2017-06-05 00:00:00','2017-06-06 00:00:00'),
('250抢购BMW X6',500,'2017-06-05 00:00:00','2017-06-06 00:00:00'),
('141抢购床上三件套',600,'2017-06-05 00:00:00','2017-06-06 00:00:00'),
('131抢购铁三角',300,'2017-06-05 00:00:00','2017-06-06 00:00:00');

create table if not exists orders (
goods_id bigint not null comment '商品id',
user_phone bigint not null comment '用户手机',
state int(1) not null default -1 comment '商品状态，-1：无效,0：成功,1：无效，2：已入库',
create_time timestamp not null comment '创建时间',
primary key (goods_id,user_phone),
key idx_create_time (create_time)
)engine=innodb default charset=utf8 comment='订单明细表';
