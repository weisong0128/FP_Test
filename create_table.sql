CREATE TABLE `fp_user` (
  `uuid` varchar(100) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(32) NOT NULL,
  `user_role` varchar(10) NOT NULL,
  `user_state` varchar(2) NOT NULL,
  `create_time` varchar(100) NOT NULL,
  `update_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `fp`.`fp_user` (`uuid`, `user_id`, `user_name`, `user_password`, `user_role`, `user_state`, `create_time`, `update_time`) VALUES ('10000', '15951612755', 'weisong', '123123', '1', '0', '2019-11-19 17:13:01', '');
INSERT INTO `fp`.`fp_user` (`uuid`, `user_id`, `user_name`, `user_password`, `user_role`, `user_state`, `create_time`, `update_time`) VALUES ('10001', '13956585448', 'xunbaobao', '123123', '1', '0', '2019-11-19 19:01:01', NULL);


create table `fp_auth_management` (
	`uuid` varchar(32) not null default '0' comment '主键',
	`project_name` varchar(32) default null comment '项目名称',
	`envir_head` varchar(20) default null comment '环境负责人',
	`phone` varchar(20) default null comment '电话',
	`provinces` varchar(100) default null comment '安装省份',
	`cities` varchar(20) default null comment '安装地市',
	`address` varchar(255) default null comment '安装地址',
	`mac` varchar(255) default null,
	`master_ip` varchar(30) default null comment '主节点ip',
	`download_time` varchar(100) default null comment '证书下载日期',
	`envir_note` varchar(20) default null comment '环境信息（0：线上生产环境，1：研发测试环境，3：停用状态）',
	`sn_file` varchar(255) default null comment '对应的sn文件',
	`feedback` varchar(20) default null comment '授权反馈情况（0：已反馈，1：未反馈）',
	`note` varchar(255) default null comment '备注',
	`create_time` varchar(100) default null comment '创建时间',
	`update_time` varchar(100) default null comment '修改时间',
	`is_available` varchar(2) default '0' comment '标识是否删除（0：可用，1：删除）'
	) ENGINE=InnoDB default CHARSET=utf8 ROW_FORMAT=DYNAMIC;

da5988b2-5982-4a85-a364-8164e156	CCP	王建武	12345678912	甘肃	临夏	甘肃临夏	AC:1F:6B:4C:02:02	15.180.153.21	20190404	0	au_key_CCASDASDZZ44500A1110010SX_20190404