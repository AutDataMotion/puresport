ALTER TABLE t5_crcl_stdy add COLUMN type char(2) DEFAULT '' COMMENT '赛事类型';
ALTER TABLE t5_crcl_stdy add COLUMN  `category` char(2) DEFAULT '' COMMENT '科目';
ALTER TABLE t7_crcl add COLUMN type char(2) DEFAULT '' COMMENT '赛事类型';
ALTER TABLE t7_crcl add COLUMN  `category` char(2) DEFAULT '' COMMENT '科目';
ALTER TABLE t9_tstlib add COLUMN type char(2) DEFAULT '' COMMENT '赛事类型';
ALTER TABLE t9_tstlib add COLUMN  `category` char(2) DEFAULT '' COMMENT '科目';
ALTER TABLE t10_exam_grd add COLUMN type char(2) DEFAULT '' COMMENT '赛事类型';
ALTER TABLE t10_exam_grd add COLUMN  `category` char(2) DEFAULT '' COMMENT '科目';
ALTER TABLE t11_exam_stat add COLUMN type char(2) DEFAULT '' COMMENT '赛事类型';
ALTER TABLE t11_exam_stat add COLUMN  `category` char(2) DEFAULT '' COMMENT '科目';
ALTER TABLE t12_highest_score add COLUMN type char(2) DEFAULT '' COMMENT '赛事类型';
ALTER TABLE `t1_usr_bsc` ADD COLUMN `email_val` INT(11) NULL DEFAULT 1 COMMENT '邮箱是否验证' AFTER `levelinstitute`;
ALTER TABLE `t1_usr_bsc` ADD COLUMN `mblph_val` INT(11) NULL DEFAULT 0 COMMENT '手机是否验证' AFTER `email_val`;
ALTER TABLE `t6_mgr_ahr` ADD COLUMN `email_val` INT(11) NULL DEFAULT 1 COMMENT '邮箱是否验证' AFTER `institute`;
ALTER TABLE `t6_mgr_ahr` ADD COLUMN `mblph_val` INT(11) NULL DEFAULT 0 COMMENT '手机是否验证' AFTER `email_val`;

ALTER TABLE `t1_usr_bsc` ADD COLUMN `nm_char` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '姓名拼音字母' AFTER `nm`;


