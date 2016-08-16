CREATE TABLE `url_rank` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT,
`site_name`  varchar(64) COMMENT '站点名称',
`site_url`   varchar(500) COMMENT '站点地址',
`alexa_rank`   int(10) COMMENT 'alexa排名',
`chinaz_rank`   int(10) COMMENT '站长之家综合排名',
`chinaz_scope`   int(10) COMMENT '站长之家得分',
`create_time`   datetime  NOT NULL DEFAULT NOW() COMMENT '数据添加时间',
`update_time`   datetime  NOT NULL DEFAULT NOW() COMMENT '数据跟新时间',
`status`  tinyint(1) default 0 COMMENT '状态:0,有效；1，删除',
primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网址链接热度数据收集表';