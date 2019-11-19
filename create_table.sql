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
