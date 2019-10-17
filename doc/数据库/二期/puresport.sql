/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : puresport

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-10-16 17:42:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dt_area
-- ----------------------------
DROP TABLE IF EXISTS `dt_area`;
CREATE TABLE `dt_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域主键',
  `name` varchar(16) DEFAULT NULL COMMENT '区域名称',
  `code` varchar(128) DEFAULT NULL COMMENT '区域代码',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级主键',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=900001 DEFAULT CHARSET=utf8 COMMENT='区域字典';

-- ----------------------------
-- Table structure for sport_item
-- ----------------------------
DROP TABLE IF EXISTS `sport_item`;
CREATE TABLE `sport_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '项目名称',
  `parentid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t10_exam_grd
-- ----------------------------
DROP TABLE IF EXISTS `t10_exam_grd`;
CREATE TABLE `t10_exam_grd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usrid` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `examid` int(10) NOT NULL DEFAULT '0' COMMENT '考试id',
  `exam_grd` int(10) DEFAULT NULL COMMENT '考试成绩',
  `exam_st` char(1) DEFAULT NULL COMMENT '考试状态',
  `prblmid` int(10) NOT NULL DEFAULT '0' COMMENT '试题id',
  `usr_aswr` char(4) DEFAULT NULL COMMENT '用户答案',
  `prblm_aswr` char(4) DEFAULT NULL COMMENT '试题答案',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `prblmno` int(10) NOT NULL COMMENT '试题编号',
  `exam_st_tm` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '答题开始时间',
  `exam_end_tm` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '答题结束时间',
  `result` varchar(8) DEFAULT NULL COMMENT '正确|错误',
  PRIMARY KEY (`id`),
  KEY `indextest` (`usrid`,`examid`,`prblmno`),
  KEY `idxprbid` (`prblmid`,`result`),
  KEY `idxresult` (`result`)
) ENGINE=InnoDB AUTO_INCREMENT=10674309 DEFAULT CHARSET=utf8 COMMENT='考试成绩信息表';

-- ----------------------------
-- Table structure for t10_exam_grd_20180801
-- ----------------------------
DROP TABLE IF EXISTS `t10_exam_grd_20180801`;
CREATE TABLE `t10_exam_grd_20180801` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usrid` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `examid` int(10) NOT NULL DEFAULT '0' COMMENT '考试id',
  `exam_grd` int(10) DEFAULT NULL COMMENT '考试成绩',
  `exam_st` char(1) DEFAULT NULL COMMENT '考试状态',
  `prblmid` int(10) NOT NULL DEFAULT '0' COMMENT '试题id',
  `usr_aswr` char(4) DEFAULT NULL COMMENT '用户答案',
  `prblm_aswr` char(4) DEFAULT NULL COMMENT '试题答案',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `prblmno` int(10) NOT NULL COMMENT '试题编号',
  `exam_st_tm` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '答题开始时间',
  `exam_end_tm` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '答题结束时间',
  `result` varchar(8) DEFAULT NULL COMMENT '正确|错误',
  PRIMARY KEY (`id`),
  KEY `indextest` (`usrid`,`examid`,`prblmno`),
  KEY `idxprbid` (`prblmid`,`result`),
  KEY `idxresult` (`result`)
) ENGINE=InnoDB AUTO_INCREMENT=8308249 DEFAULT CHARSET=utf8 COMMENT='考试成绩信息表';

-- ----------------------------
-- Table structure for t11_exam_stat
-- ----------------------------
DROP TABLE IF EXISTS `t11_exam_stat`;
CREATE TABLE `t11_exam_stat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usrid` int(10) NOT NULL COMMENT '用户id',
  `examid` int(10) NOT NULL COMMENT '考试id',
  `exam_grd` int(10) DEFAULT NULL COMMENT '考试成绩',
  `exam_st` char(1) DEFAULT NULL COMMENT '考试状态',
  `exam_channel` char(2) DEFAULT NULL COMMENT '考试渠道',
  `exam_num` int(10) DEFAULT NULL COMMENT '考试次数',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `exam_nm` varchar(60) DEFAULT '--' COMMENT '考试名称',
  PRIMARY KEY (`id`),
  KEY `indextest` (`exam_st`,`exam_grd`),
  KEY `idxuserid` (`usrid`),
  KEY `idxgrd` (`exam_grd`)
) ENGINE=InnoDB AUTO_INCREMENT=614224 DEFAULT CHARSET=utf8 COMMENT='考试成绩统计表';

-- ----------------------------
-- Table structure for t11_exam_stat_bak_20180911
-- ----------------------------
DROP TABLE IF EXISTS `t11_exam_stat_bak_20180911`;
CREATE TABLE `t11_exam_stat_bak_20180911` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `usrid` int(10) NOT NULL COMMENT '用户id',
  `examid` int(10) NOT NULL COMMENT '考试id',
  `exam_grd` int(10) DEFAULT NULL COMMENT '考试成绩',
  `exam_st` char(1) DEFAULT NULL COMMENT '考试状态',
  `exam_channel` char(2) DEFAULT NULL COMMENT '考试渠道',
  `exam_num` int(10) DEFAULT NULL COMMENT '考试次数',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `exam_nm` varchar(60) DEFAULT '--' COMMENT '考试名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t12_highest_score
-- ----------------------------
DROP TABLE IF EXISTS `t12_highest_score`;
CREATE TABLE `t12_highest_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usrid` int(10) NOT NULL COMMENT '用户id',
  `examid` int(10) NOT NULL COMMENT '考试id',
  `exam_grd` int(10) DEFAULT NULL COMMENT '考试成绩',
  `exam_st` char(1) DEFAULT NULL COMMENT '考试状态',
  `exam_channel` char(2) DEFAULT NULL COMMENT '考试渠道',
  `exam_num` int(10) DEFAULT NULL COMMENT '考试次数',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `exam_nm` varchar(60) DEFAULT '--' COMMENT '考试名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t12_highest_score_idx4` (`usrid`,`exam_nm`) USING HASH,
  KEY `t12_highest_score_idx1` (`usrid`) USING BTREE,
  KEY `t12_highest_score_idx2` (`usrid`,`exam_st`) USING BTREE,
  KEY `t12_highest_score_idx3` (`exam_grd`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=525055 DEFAULT CHARSET=utf8 COMMENT='考试最高成绩统计表';

-- ----------------------------
-- Table structure for t13_tst_stat
-- ----------------------------
DROP TABLE IF EXISTS `t13_tst_stat`;
CREATE TABLE `t13_tst_stat` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `prblmid` int(10) NOT NULL DEFAULT '0' COMMENT '试题id',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `right_num` bigint(32) unsigned DEFAULT '0' COMMENT '正确答题次数',
  `wrong_num` bigint(32) unsigned DEFAULT '0' COMMENT '错误答题次数',
  UNIQUE KEY `t13_tst_stat_idx1` (`prblmid`) USING HASH
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='试题正确错误次数统计';

-- ----------------------------
-- Table structure for t1_usr_bsc
-- ----------------------------
DROP TABLE IF EXISTS `t1_usr_bsc`;
CREATE TABLE `t1_usr_bsc` (
  `usrid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `usr_tp` varchar(16) NOT NULL DEFAULT '运动员' COMMENT '用户类型',
  `usr_nm` varchar(32) NOT NULL COMMENT '用户名',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` char(16) DEFAULT NULL COMMENT '证件类型',
  `spt_prj` varchar(32) DEFAULT NULL COMMENT '运动项目',
  `crdt_no` varchar(32) DEFAULT NULL COMMENT '证件号',
  `gnd` char(4) DEFAULT '男' COMMENT '性别',
  `pswd` varchar(512) DEFAULT NULL COMMENT '密码',
  `brth_dt` char(10) DEFAULT NULL COMMENT '出生日期',
  `adiv_cd` char(6) DEFAULT NULL COMMENT '行政区划代码',
  `asscid` char(8) DEFAULT NULL COMMENT '协会id',
  `mblph_no` varchar(32) DEFAULT NULL COMMENT '手机号',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `cty_prov_city_mgrid` bigint(20) DEFAULT NULL COMMENT '国家省市管理员id',
  `rmrk` varchar(2048) DEFAULT NULL COMMENT '备注',
  `assc_mgrid` int(10) DEFAULT NULL COMMENT '协会管理员用id',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `bloodtp` char(1) DEFAULT NULL COMMENT '血型',
  `ethnct` varchar(16) DEFAULT NULL COMMENT '民族',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `typelevel` varchar(8) DEFAULT NULL COMMENT '类型级别',
  `province` varchar(32) DEFAULT NULL COMMENT '省份名称',
  `city` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `institute` varchar(64) DEFAULT NULL COMMENT '协会名称',
  `department` varchar(256) DEFAULT NULL COMMENT '工作单位',
  `post` varchar(32) DEFAULT NULL COMMENT '职务',
  `levelprovince` int(11) DEFAULT '0' COMMENT '省级别 0不可见 1可见',
  `levelcity` int(11) DEFAULT '0' COMMENT '市级别0不可见1可见',
  `levelinstitute` int(11) DEFAULT '0' COMMENT '协会级别0不可见1可见',
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `crdt_no` (`crdt_no`(18)),
  KEY `idxusername` (`usr_nm`,`crdt_no`),
  KEY `idxsptprj` (`spt_prj`),
  KEY `idxprovince` (`province`,`city`),
  KEY `idx_t1_usr_bsc_province` (`province`),
  KEY `idx_t1_usr_bsc_city` (`city`)
) ENGINE=InnoDB AUTO_INCREMENT=204698 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Table structure for t2_adiv
-- ----------------------------
DROP TABLE IF EXISTS `t2_adiv`;
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

-- ----------------------------
-- Table structure for t3_stat
-- ----------------------------
DROP TABLE IF EXISTS `t3_stat`;
CREATE TABLE `t3_stat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_cnt` int(10) DEFAULT NULL COMMENT '已登陆次数',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=utf8 COMMENT='统计信息';

-- ----------------------------
-- Table structure for t4_assc
-- ----------------------------
DROP TABLE IF EXISTS `t4_assc`;
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

-- ----------------------------
-- Table structure for t5_crcl_stdy
-- ----------------------------
DROP TABLE IF EXISTS `t5_crcl_stdy`;
CREATE TABLE `t5_crcl_stdy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usrid` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `crclid` int(10) NOT NULL DEFAULT '0' COMMENT '课程id',
  `stdy_st` char(1) DEFAULT NULL COMMENT '学习状态',
  `ty_grd` float DEFAULT NULL COMMENT '学分',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`,`usrid`,`crclid`),
  KEY `indextest` (`usrid`,`crclid`,`stdy_st`)
) ENGINE=InnoDB AUTO_INCREMENT=520157 DEFAULT CHARSET=utf8 COMMENT='课程学习信息表';

-- ----------------------------
-- Table structure for t6_mgr_ahr
-- ----------------------------
DROP TABLE IF EXISTS `t6_mgr_ahr`;
CREATE TABLE `t6_mgr_ahr` (
  `usrid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usr_tp` varchar(32) NOT NULL DEFAULT '管理员' COMMENT '用户类型',
  `usr_nm` varchar(32) NOT NULL COMMENT '用户名',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` varchar(16) DEFAULT NULL COMMENT '证件类型',
  `crdt_no` varchar(32) DEFAULT NULL COMMENT '证件号',
  `gnd` char(2) DEFAULT NULL COMMENT '性别',
  `wrk_unit` varchar(512) DEFAULT NULL COMMENT '工作单位',
  `pswd` varchar(64) DEFAULT NULL COMMENT '密码',
  `post` varchar(32) DEFAULT NULL COMMENT '职务',
  `brth_dt` varchar(16) DEFAULT NULL COMMENT '出生日期',
  `adiv_cd` char(6) DEFAULT NULL COMMENT '行政区划代码',
  `asscid` char(6) DEFAULT NULL COMMENT '协会id',
  `mblph_no` varchar(32) DEFAULT NULL COMMENT '手机号',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `cty_prov_city_mgrid` int(10) DEFAULT NULL COMMENT '国家省市管理员id',
  `assc_mgrid` int(10) DEFAULT NULL COMMENT '协会管理员id',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `rmrk` varchar(512) DEFAULT NULL COMMENT '备注',
  `typeleve` char(8) DEFAULT NULL COMMENT '级别类型',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `institute` varchar(64) DEFAULT NULL COMMENT '协会',
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `crdt_no` (`crdt_no`(18)),
  KEY `idxusername` (`usr_nm`,`crdt_no`),
  KEY `idxinstitute` (`institute`),
  KEY `idxprovince` (`province`,`city`)
) ENGINE=InnoDB AUTO_INCREMENT=1130 DEFAULT CHARSET=utf8 COMMENT='管理员权限信息表';

-- ----------------------------
-- Table structure for t7_crcl
-- ----------------------------
DROP TABLE IF EXISTS `t7_crcl`;
CREATE TABLE `t7_crcl` (
  `crclid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `crcl_brf` varchar(512) DEFAULT NULL COMMENT '课程简介',
  `adiv` int(10) DEFAULT NULL COMMENT '行政区划代码',
  `crcl_nm` varchar(512) DEFAULT NULL COMMENT '课程名称',
  `crcl_file_rte` varchar(512) DEFAULT NULL COMMENT '课程文件路径',
  `crcl_attr` char(1) DEFAULT NULL COMMENT '课程属性（是否必修）',
  `ty_grd` int(10) DEFAULT NULL COMMENT '学分',
  `crcl_cgy` char(2) DEFAULT NULL COMMENT '课程类别',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `thumbnail_rte` varchar(512) DEFAULT NULL COMMENT '缩略图路径',
  PRIMARY KEY (`crclid`),
  KEY `indextest` (`crclid`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='课程信息表';

-- ----------------------------
-- Table structure for t8_exam
-- ----------------------------
DROP TABLE IF EXISTS `t8_exam`;
CREATE TABLE `t8_exam` (
  `examid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '考试id',
  `exam_brf` varchar(2048) DEFAULT NULL COMMENT '考试简介',
  `adiv_cd` int(10) DEFAULT NULL COMMENT '行政区划代码',
  `exam_nm` varchar(512) DEFAULT NULL COMMENT '考试名称',
  `exam_attr` char(1) DEFAULT NULL COMMENT '考试属性（是否必考）',
  `ty_grd` int(10) DEFAULT NULL COMMENT '学分',
  `prblmid_list` varchar(2048) DEFAULT NULL COMMENT '试题id列表',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`examid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试信息';

-- ----------------------------
-- Table structure for t9_tstlib
-- ----------------------------
DROP TABLE IF EXISTS `t9_tstlib`;
CREATE TABLE `t9_tstlib` (
  `prblmid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '试题id',
  `prblm_ppl` varchar(512) DEFAULT NULL COMMENT '出题者',
  `prblm_tp` char(2) DEFAULT NULL COMMENT '试题类型',
  `opt` varchar(512) DEFAULT NULL COMMENT '选项',
  `ttl` varchar(512) DEFAULT NULL COMMENT '题目',
  `prblm_aswr` char(4) DEFAULT NULL COMMENT '试题答案',
  `scor` int(10) DEFAULT NULL COMMENT '分数',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`prblmid`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='试题库';

-- ----------------------------
-- View structure for prjgroupansweredcnt
-- ----------------------------
DROP VIEW IF EXISTS `prjgroupansweredcnt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prjgroupansweredcnt` AS select `p`.`spt_prj` AS `spt_prj`,`p`.`province` AS `province`,`p`.`city` AS `city`,count(1) AS `cnt` from `sporterscorestatis` `p` where (`p`.`exam_grd` is not null) group by `p`.`spt_prj`,`p`.`province`,`p`.`city` ;

-- ----------------------------
-- View structure for prjgroupcnt
-- ----------------------------
DROP VIEW IF EXISTS `prjgroupcnt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prjgroupcnt` AS select `p`.`spt_prj` AS `spt_prj`,`p`.`province` AS `province`,`p`.`city` AS `city`,count(1) AS `cnt` from `sporterscorestatis` `p` group by `p`.`spt_prj`,`p`.`province`,`p`.`city` ;

-- ----------------------------
-- View structure for prjgrouppassedcnt
-- ----------------------------
DROP VIEW IF EXISTS `prjgrouppassedcnt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prjgrouppassedcnt` AS select `p`.`spt_prj` AS `spt_prj`,`p`.`province` AS `province`,`p`.`city` AS `city`,count(1) AS `cnt` from `sporterscorestatis` `p` where (`p`.`exam_grd` >= 80) group by `p`.`spt_prj`,`p`.`province`,`p`.`city` ;

-- ----------------------------
-- View structure for prjgroupstatis
-- ----------------------------
DROP VIEW IF EXISTS `prjgroupstatis`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prjgroupstatis` AS select `t`.`spt_prj` AS `spt_prj`,`t`.`province` AS `province`,`t`.`city` AS `city`,`t`.`cnt` AS `cnt_total`,`a`.`cnt` AS `cnt_answered`,`p`.`cnt` AS `cnt_passed` from ((`prjgroupcnt` `t` left join `prjgroupansweredcnt` `a` on(((`t`.`spt_prj` = `a`.`spt_prj`) and (`t`.`province` = `a`.`province`) and (`t`.`city` = `a`.`city`)))) left join `prjgrouppassedcnt` `p` on(((`t`.`spt_prj` = `p`.`spt_prj`) and (`t`.`province` = `p`.`province`) and (`t`.`city` = `p`.`city`)))) ;

-- ----------------------------
-- View structure for problemcnt
-- ----------------------------
DROP VIEW IF EXISTS `problemcnt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `problemcnt` AS select `t10_exam_grd`.`prblmid` AS `prblmid`,count(1) AS `cnt` from `t10_exam_grd` group by `t10_exam_grd`.`prblmid` ;

-- ----------------------------
-- View structure for problemstatis
-- ----------------------------
DROP VIEW IF EXISTS `problemstatis`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `problemstatis` AS select `b`.`prblmid` AS `prblmid`,`b`.`prblm_ppl` AS `prblm_ppl`,`b`.`prblm_tp` AS `prblm_tp`,`b`.`opt` AS `opt`,`b`.`ttl` AS `ttl`,`b`.`prblm_aswr` AS `prblm_aswr`,`b`.`scor` AS `scor`,`b`.`tms` AS `tms`,(`ca`.`right_num` + `ca`.`wrong_num`) AS `cntall`,`ca`.`wrong_num` AS `cntwrong` from (`t9_tstlib` `b` left join `t13_tst_stat` `ca` on((`b`.`prblmid` = `ca`.`prblmid`))) order by `ca`.`wrong_num` desc ;

-- ----------------------------
-- View structure for problemwrongcnt
-- ----------------------------
DROP VIEW IF EXISTS `problemwrongcnt`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `problemwrongcnt` AS select `t10_exam_grd`.`prblmid` AS `prblmid`,count(1) AS `cnt` from `t10_exam_grd` where (`t10_exam_grd`.`result` = '错误') group by `t10_exam_grd`.`prblmid` ;

-- ----------------------------
-- View structure for sporterscorestatis
-- ----------------------------
DROP VIEW IF EXISTS `sporterscorestatis`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sporterscorestatis` AS select `u`.`usrid` AS `usrid`,`u`.`usr_tp` AS `usr_tp`,`u`.`usr_nm` AS `usr_nm`,`u`.`nm` AS `nm`,`u`.`crdt_tp` AS `crdt_tp`,`u`.`spt_prj` AS `spt_prj`,`u`.`crdt_no` AS `crdt_no`,`u`.`gnd` AS `gnd`,`u`.`pswd` AS `pswd`,`u`.`brth_dt` AS `brth_dt`,`u`.`adiv_cd` AS `adiv_cd`,`u`.`asscid` AS `asscid`,`u`.`mblph_no` AS `mblph_no`,`u`.`tms` AS `tms`,`u`.`cty_prov_city_mgrid` AS `cty_prov_city_mgrid`,`u`.`rmrk` AS `rmrk`,`u`.`assc_mgrid` AS `assc_mgrid`,`u`.`email` AS `email`,`u`.`bloodtp` AS `bloodtp`,`u`.`ethnct` AS `ethnct`,`u`.`remark` AS `remark`,`u`.`typelevel` AS `typelevel`,`u`.`province` AS `province`,`u`.`city` AS `city`,`u`.`institute` AS `institute`,`u`.`department` AS `department`,`u`.`post` AS `post`,`s`.`examid` AS `examid`,`s`.`exam_nm` AS `exam_nm`,`s`.`exam_grd` AS `exam_grd` from (`t1_usr_bsc` `u` left join `t12_highest_score` `s` on((`u`.`usrid` = `s`.`usrid`))) ;

-- ----------------------------
-- Procedure structure for array
-- ----------------------------
DROP PROCEDURE IF EXISTS `array`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `array`()
BEGIN 

set @provinceName = '新疆维吾尔%';
set @provinceId = 650000;
    
SET @array_content="和田市
伊犁哈萨克自治州
伊宁市
奎屯市
霍尔果斯市
塔城市
乌苏市
石河子市
阿拉尔市
图木舒克市
五家渠市
北屯市
铁门关市
双河市
可克达拉市
昆玉市
"; 

SET @i=1;
-- 得出数组成员总数 
SET @count=CHAR_LENGTH(@array_content)-CHAR_LENGTH(REPLACE(@array_content,char(10),'')) +1; 

-- drop table if exists tooltmp;
-- CREATE TABLE tooltmp(field1 VARCHAR(100)); 
WHILE @i <= @count 
DO    
-- 依次每个成员  
	SET @namecity=SUBSTRING_INDEX(SUBSTRING_INDEX(@array_content,char(10),@i),char(10),-1);
	set @cityId = 0;
	set @isExsit = 0;
	select @isExsit:= count(1) from dt_area where `name` = @namecity and parent_id = @provinceId;
-- 没有则插入 
	if @isExsit=0 and @namecity!='' then
		select @cityId:= (max(id)+100) from dt_area where parent_id = @provinceId;
		insert into dt_area(id,name,parent_id) values(@cityId, @namecity,@provinceId);
	end if;

SET @i=@i+1; 

END WHILE; 
END
;;
DELIMITER ;
