/*
Navicat MySQL Data Transfer

Source Server         : TT
Source Server Version : 50639
Source Host           : 127.0.0.1:3309
Source Database       : puresport

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2020-01-12 16:41:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t18_extras_points
-- ----------------------------
DROP TABLE IF EXISTS `t18_extras_points`;
CREATE TABLE `t18_extras_points` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scor` int(3) DEFAULT NULL COMMENT '邀请码',
  `type` char(2) DEFAULT '' COMMENT '赛事类型',
  `usrid` int(10) NOT NULL COMMENT '用户id',
  `category` char(2) DEFAULT '' COMMENT '科目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='邀请码信息表';
