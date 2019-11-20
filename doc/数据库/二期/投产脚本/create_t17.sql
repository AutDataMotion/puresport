/*
Navicat MySQL Data Transfer

Source Server         : TT
Source Server Version : 50639
Source Host           : 127.0.0.1:3309
Source Database       : puresport

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-11-17 14:34:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t17_credit_inf
-- ----------------------------
DROP TABLE IF EXISTS `t17_credit_inf`;
CREATE TABLE `t17_credit_inf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` char(2) NOT NULL DEFAULT '' COMMENT '赛事类型',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` char(16) DEFAULT NULL COMMENT '证件类型',
  `crdt_no` varchar(32) DEFAULT NULL COMMENT '证件号',
  `usrid` bigint(20) NOT NULL COMMENT '用户id',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '生成时间',
  `flag` char(2) NOT NULL DEFAULT '' COMMENT '类型',
  `file_path` varchar(2048) DEFAULT NULL COMMENT '文件路径',
  `credit_no` char(12) DEFAULT NULL COMMENT '证书编号',
  PRIMARY KEY (`id`,`usrid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='邀请码信息表';
