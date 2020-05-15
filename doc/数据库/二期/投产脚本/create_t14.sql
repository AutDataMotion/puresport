/*
Navicat MySQL Data Transfer

Source Server         : TT
Source Server Version : 50639
Source Host           : 127.0.0.1:3309
Source Database       : puresport

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-10-24 22:02:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t14_invitation_code
-- ----------------------------
DROP TABLE IF EXISTS `t14_invitation_code`;
CREATE TABLE `t14_invitation_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `invitation_code` varchar(256) NOT NULL COMMENT '邀请码',
  `invited_user_list` text COMMENT '已验证用户列表',
  `type` char(2) NOT NULL DEFAULT '' COMMENT '赛事类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='邀请码信息表';
