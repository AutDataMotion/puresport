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

CREATE TABLE `r16_group_usr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `ext` varchar(128) DEFAULT '' COMMENT '扩展',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='分组运动员';
