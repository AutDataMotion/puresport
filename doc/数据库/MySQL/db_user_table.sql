CREATE TABLE `t1_usr_bsc` (
  `usrid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `usr_tp` int(10) NOT NULL COMMENT '用户类型',
  `usr_nm` varchar(32) NOT NULL COMMENT '用户名',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` char(2) DEFAULT NULL COMMENT '证件类型',
  `spt_prj` varchar(512) DEFAULT NULL COMMENT '运动项目',
  `crdt_no` varchar(256) DEFAULT NULL COMMENT '证件号',
  `gnd` char(1) DEFAULT NULL COMMENT '性别',
  `pswd` varchar(512) DEFAULT NULL COMMENT '密码',
  `brth_dt` char(8) DEFAULT NULL COMMENT '出生日期',
  `adiv_cd` char(6) DEFAULT NULL COMMENT '行政区划代码',
  `asscid` char(8) DEFAULT NULL COMMENT '协会id',
  `mblph_no` varchar(256) DEFAULT NULL COMMENT '手机号',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `cty_prov_city_mgrid` int(10) DEFAULT NULL COMMENT '国家省市管理员id',
  `rmrk` varchar(2048) DEFAULT NULL COMMENT '备注',
  `assc_mgrid` int(10) DEFAULT NULL COMMENT '协会管理员用id',
  `email` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `bloodtp` char(1) DEFAULT NULL COMMENT '血型',
  `ethnct` varchar(512) DEFAULT NULL COMMENT '民族',
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usr_nm` (`usr_nm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

CREATE TABLE `t2_adiv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `adiv_cd` int(10) NOT NULL COMMENT '行政区划代码',
  `unit_nm` varchar(512) DEFAULT NULL COMMENT '单位名称',
  `plvl_adiv_cd` char(6) DEFAULT NULL COMMENT '上一级行政区划代码',
  `adiv_hier` char(1) DEFAULT NULL COMMENT '行政区划层级',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `adiv_cd` (`adiv_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区划信息表';

CREATE TABLE `t3_stat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_cnt` int(10) DEFAULT NULL COMMENT '已登陆次数',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统计信息';

CREATE TABLE `t4_assc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `asscid` char(6) NOT NULL COMMENT '协会id',
  `assc_nm` varchar(512) NOT NULL COMMENT '协会名称',
  `plvl_asscid` char(6) DEFAULT NULL COMMENT '上一级协会id',
  `assc_hier` char(1) DEFAULT NULL COMMENT '协会层级',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `asscid` (`asscid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='协会信息表';

CREATE TABLE `dt_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域主键',
  `name` varchar(16) DEFAULT NULL COMMENT '区域名称',
  `code` varchar(128) DEFAULT NULL COMMENT '区域代码',
  `short` varchar(32) DEFAULT NULL COMMENT '区域简称',
  `is_hot` varchar(1) DEFAULT NULL COMMENT '是否热门(0:否、1:是)',
  `sequence` int(11) DEFAULT NULL COMMENT '区域序列',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级主键',
  `init_date` datetime DEFAULT NULL COMMENT '初始时间',
  `init_addr` varchar(16) DEFAULT NULL COMMENT '初始地址',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=900001 DEFAULT CHARSET=utf8 COMMENT='区域字典';

CREATE TABLE `t6_mgr_ahr` (
  `usrid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usr_tp` char(2) NOT NULL COMMENT '用户类型',
  `usr_nm` varchar(32) NOT NULL COMMENT '用户名',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` char(2) DEFAULT NULL COMMENT '证件类型',
  `crdt_no` varchar(512) DEFAULT NULL COMMENT '证件号',
  `gnd` char(1) DEFAULT NULL COMMENT '性别',
  `wrk_unit` varchar(512) DEFAULT NULL COMMENT '工作单位',
  `pswd` varchar(512) DEFAULT NULL COMMENT '密码',
  `post` varchar(512) DEFAULT NULL COMMENT '职务',
  `brth_dt` char(8) DEFAULT NULL COMMENT '出生日期',
  `adiv_cd` char(6) DEFAULT NULL COMMENT '行政区划代码',
  `asscid` char(6) DEFAULT NULL COMMENT '协会id',
  `mblph_no` varchar(256) DEFAULT NULL COMMENT '手机号',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `cty_prov_city_mgrid` int(10) DEFAULT NULL COMMENT '国家省市管理员id',
  `assc_mgrid` int(10) DEFAULT NULL COMMENT '协会管理员id',
  `email` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `rmrk` varchar(2048) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usr_nm` (`usr_nm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员权限信息表';






