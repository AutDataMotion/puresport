-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: puresport
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t1_usr_bsc`
--

DROP TABLE IF EXISTS `t1_usr_bsc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t1_usr_bsc` (
  `usrid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `usr_tp` varchar(16) NOT NULL DEFAULT '运动员' COMMENT '用户类型',
  `usr_nm` varchar(32) NOT NULL COMMENT '用户名',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` char(16) DEFAULT NULL COMMENT '证件类型',
  `spt_prj` varchar(512) DEFAULT NULL COMMENT '运动项目',
  `crdt_no` varchar(256) DEFAULT NULL COMMENT '证件号',
  `gnd` char(4) DEFAULT '男' COMMENT '性别',
  `pswd` varchar(512) DEFAULT NULL COMMENT '密码',
  `brth_dt` char(10) DEFAULT NULL COMMENT '出生日期',
  `adiv_cd` char(6) DEFAULT NULL COMMENT '行政区划代码',
  `asscid` char(8) DEFAULT NULL COMMENT '协会id',
  `mblph_no` varchar(256) DEFAULT NULL COMMENT '手机号',
  `tms` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '维护时间',
  `cty_prov_city_mgrid` bigint(20) DEFAULT NULL COMMENT '国家省市管理员id',
  `rmrk` varchar(2048) DEFAULT NULL COMMENT '备注',
  `assc_mgrid` int(10) DEFAULT NULL COMMENT '协会管理员用id',
  `email` varchar(512) DEFAULT NULL COMMENT '邮箱',
  `bloodtp` char(1) DEFAULT NULL COMMENT '血型',
  `ethnct` varchar(512) DEFAULT NULL COMMENT '民族',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `typelevel` varchar(8) DEFAULT NULL COMMENT '类型级别',
  `province` varchar(128) DEFAULT NULL COMMENT '省份名称',
  `city` varchar(128) DEFAULT NULL COMMENT '城市名称',
  `institute` varchar(512) DEFAULT NULL COMMENT '协会名称',
  `department` varchar(256) DEFAULT NULL COMMENT '工作单位',
  `post` varchar(128) DEFAULT NULL COMMENT '职务',
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usr_nm` (`usr_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t1_usr_bsc`
--

LOCK TABLES `t1_usr_bsc` WRITE;
/*!40000 ALTER TABLE `t1_usr_bsc` DISABLE KEYS */;
INSERT INTO `t1_usr_bsc` VALUES (1,'运动员','1111','运动员1','军官证','羽毛球','139282192837384731','女','384731','2018-05-16',NULL,NULL,'1111','2018-05-01 14:39:09',NULL,NULL,NULL,'1111@qq.com',NULL,NULL,NULL,'国家','北京','东城区','羽毛球',NULL,NULL),(2,'运动员','2222','运动员2','身份证','羽毛球','139282192837384732','男','384732','31966',NULL,NULL,'2222','2018-04-25 00:04:36',NULL,NULL,NULL,'2222@qq.com',NULL,NULL,NULL,NULL,'河北省','唐山市','羽毛球',NULL,NULL),(3,'运动员','3333','运动员3','身份证','乒乓球','139282192837384733','男','384733','31966',NULL,NULL,'3333','2018-04-25 00:04:36',NULL,NULL,NULL,'3333@qq.com',NULL,NULL,NULL,NULL,'河北省','保定市','乒乓球',NULL,NULL),(4,'运动员','4444','运动员4','身份证','游泳','139282192837384734','男','384734','31966',NULL,NULL,'4444','2018-04-25 00:04:36',NULL,NULL,NULL,'4444@qq.com',NULL,NULL,NULL,NULL,'山东省','济南市','游泳',NULL,NULL),(5,'运动员','5555','运动员5','身份证','排球','139282192837384735','男','384735','31966',NULL,NULL,'5555','2018-04-25 00:04:36',NULL,NULL,NULL,'5555@qq.com',NULL,NULL,NULL,NULL,'湖北省','武汉市','排球',NULL,NULL),(6,'运动员','6666','运动员6','身份证','游泳','139282192837384736','男','384736','31966',NULL,NULL,'6666','2018-04-25 00:04:36',NULL,NULL,NULL,'6666@qq.com',NULL,NULL,NULL,NULL,'山东省','威海市','游泳',NULL,NULL),(7,'运动员','7777','运动员7','身份证','田径','139282192837384737','男','384737','31966',NULL,NULL,'7777','2018-04-25 00:04:36',NULL,NULL,NULL,'7777@qq.com',NULL,NULL,NULL,NULL,'山西僧','太原市','田径',NULL,NULL),(8,'运动员','8888','运动员8','身份证','田径','139282192837384738','男','384738','31966',NULL,NULL,'8888','2018-04-25 00:04:36',NULL,NULL,NULL,'8888@qq.com',NULL,NULL,NULL,NULL,'山西僧','太原市','田径',NULL,NULL),(9,'运动员','9999','运动员9','身份证','田径','139282192837384739','男','384739','31966',NULL,NULL,'9999','2018-04-25 00:04:36',NULL,NULL,NULL,'9999@qq.com',NULL,NULL,NULL,NULL,'山西僧','太原市','田径',NULL,NULL),(10,'运动员','1010','运动员10','身份证','田径','1392821928373847310','男','3847310','31966',NULL,NULL,'1010','2018-04-25 00:04:36',NULL,NULL,NULL,'1010@qq.com',NULL,NULL,NULL,NULL,'山西僧','太原市','田径',NULL,NULL),(11,'运动员','11011','运动员11','身份证','田径','1392821928373847311','男','3847311','31966',NULL,NULL,'11011','2018-04-25 00:04:36',NULL,NULL,NULL,'11011@qq.com',NULL,NULL,NULL,NULL,'山西僧','太原市','田径',NULL,NULL),(12,'运动员','11012','运动员12','身份证','田径','1392821928373847312','男','3847312','31966',NULL,NULL,'12012','2018-04-25 00:04:36',NULL,NULL,NULL,'12012@qq.com',NULL,NULL,NULL,NULL,'山西僧','太原市','田径',NULL,NULL),(13,'运动员','12313','新加运动员','身份证','1','18299828192879','女',NULL,'19280708',NULL,NULL,'12313','2018-05-01 09:50:36',NULL,NULL,NULL,'123',NULL,NULL,NULL,'1','1','1','1',NULL,NULL),(14,'运动员','2','新增2','2','2','2','2',NULL,'2',NULL,NULL,'2','2018-05-01 10:04:26',NULL,NULL,NULL,'2',NULL,NULL,NULL,'2','2','2','2',NULL,NULL);
/*!40000 ALTER TABLE `t1_usr_bsc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t6_mgr_ahr`
--

DROP TABLE IF EXISTS `t6_mgr_ahr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t6_mgr_ahr` (
  `usrid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `usr_tp` varchar(32) NOT NULL DEFAULT '管理员' COMMENT '用户类型',
  `usr_nm` varchar(32) NOT NULL COMMENT '用户名',
  `nm` varchar(32) NOT NULL COMMENT '姓名',
  `crdt_tp` varchar(16) DEFAULT NULL COMMENT '证件类型',
  `crdt_no` varchar(64) DEFAULT NULL COMMENT '证件号',
  `gnd` char(2) DEFAULT NULL COMMENT '性别',
  `wrk_unit` varchar(512) DEFAULT NULL COMMENT '工作单位',
  `pswd` varchar(64) DEFAULT NULL COMMENT '密码',
  `post` varchar(128) DEFAULT NULL COMMENT '职务',
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
  `province` varchar(128) DEFAULT NULL COMMENT '省',
  `city` varchar(128) DEFAULT NULL COMMENT '城市',
  `institute` varchar(512) DEFAULT NULL COMMENT '协会',
  PRIMARY KEY (`usrid`),
  UNIQUE KEY `usr_nm` (`usr_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='管理员权限信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t6_mgr_ahr`
--

LOCK TABLES `t6_mgr_ahr` WRITE;
/*!40000 ALTER TABLE `t6_mgr_ahr` DISABLE KEYS */;
INSERT INTO `t6_mgr_ahr` VALUES (24,'管理员','1111','管理员1','身份证','139282192837384731','男','空间应用中心','384731','工程师','31966',NULL,NULL,'1111','2018-04-20 15:20:48',NULL,NULL,'1111@qq.com',NULL,'国家','河北','保定','羽毛球'),(25,'管理员','2222','管理员2','身份证','139282192837384732','男','空间应用中心','384732','工程师','31966',NULL,NULL,'2222','2018-04-20 15:20:48',NULL,NULL,'2222@qq.com',NULL,'国家','河北','保定','羽毛球'),(26,'管理员','3333','管理员3','身份证','139282192837384733','男','空间应用中心','384733','工程师','31966',NULL,NULL,'3333','2018-04-20 15:20:48',NULL,NULL,'3333@qq.com',NULL,'国家','河北','保定','羽毛球'),(27,'管理员','4444','管理员4','身份证','139282192837384734','男','空间应用中心','384734','工程师','31966',NULL,NULL,'4444','2018-04-20 15:20:48',NULL,NULL,'4444@qq.com',NULL,'国家','河北','保定','羽毛球'),(28,'管理员','5555','管理员5','身份证','139282192837384735','男','空间应用中心','384735','工程师','31966',NULL,NULL,'5555','2018-04-20 15:20:48',NULL,NULL,'5555@qq.com',NULL,'国家','河北','保定','羽毛球'),(29,'管理员','6666','管理员6','身份证','139282192837384736','男','空间应用中心','384736','工程师','31966',NULL,NULL,'6666','2018-04-20 15:20:48',NULL,NULL,'6666@qq.com',NULL,'国家','河北','保定','羽毛球'),(30,'管理员','7777','管理员7','身份证','139282192837384737','男','空间应用中心','384737','工程师','31966',NULL,NULL,'7777','2018-04-20 15:20:48',NULL,NULL,'7777@qq.com',NULL,'国家','河北','保定','羽毛球');
/*!40000 ALTER TABLE `t6_mgr_ahr` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-01 22:53:47
