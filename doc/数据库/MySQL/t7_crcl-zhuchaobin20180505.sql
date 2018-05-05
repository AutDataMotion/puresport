/*
Navicat MySQL Data Transfer

Source Server         : localhost2
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : puresport

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-05-05 00:24:08
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`crclid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='课程信息表';

-- ----------------------------
-- Records of t7_crcl
-- ----------------------------
INSERT INTO `t7_crcl` VALUES ('1', '1', '1', '反兴奋剂准入系统操作流程.', '1', '1', '1', '1', '2018-05-04 23:36:44', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('2', '2', '2', '反兴奋剂准入系统操作流程.', '2', '2', '2', '2', '2018-05-04 22:12:53', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('3', '4', '4', '反兴奋剂准入系统操作流程.', '4', '2', '4', '4', '2018-05-04 22:12:54', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('4', '4', '4', '反兴奋剂准入系统操作流程.', '4', '2', '4', '4', '2018-05-04 22:12:55', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('5', '1', '1', '反兴奋剂准入系统操作流程.', '1', '2', '1', '1', '2018-05-04 22:12:55', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('6', '2', '2', '反兴奋剂准入系统操作流程.', '2', '2', '2', '2', '2018-05-04 22:12:56', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('7', '4', '4', '反兴奋剂准入系统操作流程.', '4', '2', '4', '4', '2018-05-04 22:12:56', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('8', '4', '4', '反兴奋剂准入系统操作流程.', '4', '2', '4', '4', '2018-05-04 22:12:56', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('9', '1', '1', '反兴奋剂准入系统操作流程.', '1', '3', '1', '1', '2018-05-04 22:12:58', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('10', '2', '2', '反兴奋剂准入系统操作流程.', '2', '3', '2', '2', '2018-05-04 23:36:35', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('11', '4', '4', '反兴奋剂准入系统操作流程.', '4', '3', '4', '4', '2018-05-04 23:36:37', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('12', '4', '4', '反兴奋剂准入系统操作流程.', '4', '3', '4', '4', '2018-05-04 23:36:36', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('13', '1', '1', '反兴奋剂准入系统操作流程.', '1', '3', '1', '1', '2018-05-04 23:36:38', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('14', '2', '2', '反兴奋剂准入系统操作流程.', '2', '3', '2', '2', '2018-05-04 23:36:39', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('15', '4', '4', '反兴奋剂准入系统操作流程.', '4', '3', '4', '4', '2018-05-04 23:36:40', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('16', '4', '4', '反兴奋剂准入系统操作流程.', '案例篇-十三运反兴奋剂教育准入课件.pptx', '4', '4', '4', '2018-05-05 00:01:25', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('17', '4', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-05 00:01:47', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('18', '4', '4', '反兴奋剂准入系统操作流程.', '案例篇-十三运反兴奋剂教育准入课件.pptx', '4', '4', '4', '2018-05-05 00:01:43', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('19', '4', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-05 00:01:48', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('20', '4', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-05 00:01:50', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('21', '4', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-05 00:01:51', 'fyh.jpg');
