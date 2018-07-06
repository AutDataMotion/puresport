/*
Navicat MySQL Data Transfer

Source Server         : nanjing
Source Server Version : 50639
Source Host           : 127.0.0.1:3306
Source Database       : puresport

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-04 02:00:49
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='课程信息表';

-- ----------------------------
-- Records of t7_crcl
-- ----------------------------
INSERT INTO `t7_crcl` VALUES ('1', '省运会反兴奋剂教育准入讲座', '1', '省运会反兴奋剂教育准入讲座', '/materials/video/main1.mp4', '1', '1', '1', '2018-06-03 17:10:40', 'main1.jpg');
INSERT INTO `t7_crcl` VALUES ('16', '2015年7月至2016年4月，河南省女子田径运动员刘某某，接受生物护照检查，多项血液样本检测数据异常。经生物护照评估委员会审查，判定为生物护照阳性结果。\r\n          生物护照评估委员会审核了运动员提交的血液样本解释材料，一致认为该检测数据异常不是由生理或病理原因造成，因此判定该运动员兴奋剂违规。之后，运动员方又申请召开听证会，听证委员会一致认为对运动员刘某某检查检测的判定符合相关国际标准和指南，且运动员方未能对其兴奋剂违规提供合理解释。\r\n', '4', '反兴奋剂准入系统操作流程.', '案例篇-十三运反兴奋剂教育准入课件.pptx', '4', '4', '4', '2018-05-19 23:54:22', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('17', '2015年7月至2016年4月，河南省女子田径运动员刘某某，接受生物护照检查，多项血液样本检测数据异常。经生物护照评估委员会审查，判定为生物护照阳性结果。\r\n          生物护照评估委员会审核了运动员提交的血液样本解释材料，一致认为该检测数据异常不是由生理或病理原因造成，因此判定该运动员兴奋剂违规。之后，运动员方又申请召开听证会，听证委员会一致认为对运动员刘某某检查检测的判定符合相关国际标准和指南，且运动员方未能对其兴奋剂违规提供合理解释。\r\n', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-19 23:54:23', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('18', '2015年7月至2016年4月，河南省女子田径运动员刘某某，接受生物护照检查，多项血液样本检测数据异常。经生物护照评估委员会审查，判定为生物护照阳性结果。\r\n          生物护照评估委员会审核了运动员提交的血液样本解释材料，一致认为该检测数据异常不是由生理或病理原因造成，因此判定该运动员兴奋剂违规。之后，运动员方又申请召开听证会，听证委员会一致认为对运动员刘某某检查检测的判定符合相关国际标准和指南，且运动员方未能对其兴奋剂违规提供合理解释。\r\n', '4', '反兴奋剂准入系统操作流程.', '案例篇-十三运反兴奋剂教育准入课件.pptx', '4', '4', '4', '2018-05-19 23:54:24', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('19', '2015年7月至2016年4月，河南省女子田径运动员刘某某，接受生物护照检查，多项血液样本检测数据异常。经生物护照评估委员会审查，判定为生物护照阳性结果。\r\n          生物护照评估委员会审核了运动员提交的血液样本解释材料，一致认为该检测数据异常不是由生理或病理原因造成，因此判定该运动员兴奋剂违规。之后，运动员方又申请召开听证会，听证委员会一致认为对运动员刘某某检查检测的判定符合相关国际标准和指南，且运动员方未能对其兴奋剂违规提供合理解释。\r\n', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-19 23:54:24', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('20', '2015年7月至2016年4月，河南省女子田径运动员刘某某，接受生物护照检查，多项血液样本检测数据异常。经生物护照评估委员会审查，判定为生物护照阳性结果。\r\n          生物护照评估委员会审核了运动员提交的血液样本解释材料，一致认为该检测数据异常不是由生理或病理原因造成，因此判定该运动员兴奋剂违规。之后，运动员方又申请召开听证会，听证委员会一致认为对运动员刘某某检查检测的判定符合相关国际标准和指南，且运动员方未能对其兴奋剂违规提供合理解释。\r\n', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-05-19 23:54:25', 'fyh.jpg');
INSERT INTO `t7_crcl` VALUES ('21', '检查数据分析／违规行为解读\r\n', '2', '检查数据分析／违规行为解读\r\n', '/materials/video/anli1.mp4', '2', '2', '2', '2018-06-02 13:36:44', 'anli1.jpg');
INSERT INTO `t7_crcl` VALUES ('22', '故意使用及误服误用兴奋剂\r\n', '2', '故意使用及误服误用兴奋剂\r\n', '/materials/video/anli2.mp4', '2', '2', '2', '2018-06-02 13:36:44', 'anli2.jpg');
INSERT INTO `t7_crcl` VALUES ('23', '禁用清单变化／生物护照阳性结果\r\n', '4', '禁用清单变化／生物护照阳性结果\r\n', '/materials/video/anli3.mp4', '2', '4', '4', '2018-06-02 13:36:44', 'anli3.jpg');
INSERT INTO `t7_crcl` VALUES ('24', '逃避、错过兴奋剂检查／冒名顶替接受兴奋剂检查\r\n', '4', '逃避、错过兴奋剂检查／冒名顶替接受兴奋剂检查\r\n', '/materials/video/anli4.mp4', '2', '4', '4', '2018-06-02 13:36:44', 'anli4.jpg');
INSERT INTO `t7_crcl` VALUES ('25', '持有、交易、施用、组织使用兴奋剂／禁止合作\r\n', '1', '持有、交易、施用、组织使用兴奋剂／禁止合作\r\n', '/materials/video/anli5.mp4', '2', '1', '1', '2018-06-02 13:36:44', 'anli5.jpg');
INSERT INTO `t7_crcl` VALUES ('31', '兴奋剂检测流程     ', '2', '兴奋剂检测流程     ', '/materials/video/liucheng1.mp4', '3', '2', '2', '2018-06-02 13:56:15', 'liucheng1.jpg');
INSERT INTO `t7_crcl` VALUES ('32', 'TUE申请流程        ', '1', 'TUE申请流程        ', '/materials/video/liucheng2.mp4', '3', '1', '1', '2018-06-02 13:56:15', 'liucheng2.jpg');
INSERT INTO `t7_crcl` VALUES ('33', '生物护照介绍       ', '2', '生物护照介绍       ', '/materials/video/liucheng3.mp4', '3', '2', '2', '2018-06-02 13:56:15', 'liucheng3.jpg');
INSERT INTO `t7_crcl` VALUES ('34', '行踪信息申报流程   ', '4', '行踪信息申报流程   ', '/materials/video/liucheng4.mp4', '3', '4', '4', '2018-06-02 13:56:15', 'liucheng4.jpg');
INSERT INTO `t7_crcl` VALUES ('35', '结果管理流程       ', '4', '结果管理流程       ', '/materials/video/liucheng5.mp4', '3', '4', '4', '2018-06-02 13:56:15', 'liucheng5.jpg');
INSERT INTO `t7_crcl` VALUES ('36', '听证会介绍         ', '1', '听证会介绍         ', '/materials/video/liucheng6.mp4', '3', '1', '1', '2018-06-02 13:56:15', 'liucheng6.jpg');
INSERT INTO `t7_crcl` VALUES ('37', '预防误服误用       ', '4', '预防误服误用       ', '/materials/video/liucheng7.mp4', '3', '4', '4', '2018-06-02 13:56:15', 'liucheng7.jpg');
INSERT INTO `t7_crcl` VALUES ('44', '2015年7月至2016年4月，河南省女子田径运动员刘某某，接受生物护照检查，多项血液样本检测数据异常。经生物护照评估委员会审查，判定为生物护照阳性结果。\r\n          生物护照评估委员会审核了运动员提交的血液样本解释材料，一致认为该检测数据异常不是由生理或病理原因造成，因此判定该运动员兴奋剂违规。之后，运动员方又申请召开听证会，听证委员会一致认为对运动员刘某某检查检测的判定符合相关国际标准和指南，且运动员方未能对其兴奋剂违规提供合理解释。\r\n', '4', '反兴奋剂准入系统操作流程.', '十三运反兴奋剂教育准入手册.pdf', '4', '4', '4', '2018-06-02 13:33:15', 'fyh.jpg');
