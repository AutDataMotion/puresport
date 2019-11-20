
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `t15_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mgr_id` bigint(20) DEFAULT NULL COMMENT '管理者id',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `content` varchar(256) DEFAULT '',
  `type` int(11) DEFAULT '0' COMMENT '类型',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='分组';
