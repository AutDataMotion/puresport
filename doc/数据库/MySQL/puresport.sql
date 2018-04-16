use puresport;
DROP TABLE IF EXISTS t1_usr_bsc;
CREATE TABLE t1_usr_bsc (
	usrid BIGINT(20)  AUTO_INCREMENT  COMMENT'用户id',
	usr_tp INT(10)  NOT NULL  COMMENT'用户类型',
	usr_nm VARCHAR(32)  UNIQUE  NOT NULL  COMMENT'用户名',
	nm VARCHAR(32)  NOT NULL  COMMENT'姓名',
	crdt_tp CHAR(2)  COMMENT'证件类型',
	spt_prj VARCHAR(512)  COMMENT'运动项目',
	crdt_no VARCHAR(256)  COMMENT'证件号',
	gnd CHAR(1)  COMMENT'性别',
	pswd VARCHAR(512)  COMMENT'密码',
	brth_dt CHAR(8)  COMMENT'出生日期',
	adiv_cd CHAR(6)  COMMENT'行政区划代码',
	asscid CHAR(8)  COMMENT'协会id',
	mblph_no VARCHAR(256)  COMMENT'手机号',
	tms TIMESTAMP  COMMENT'维护时间',
	cty_prov_city_mgrid INT(10)  COMMENT'国家省市管理员id',
	rmrk VARCHAR(2048)  COMMENT'备注',
	assc_mgrid INT(10)  COMMENT'协会管理员用id',
	email VARCHAR(512)  COMMENT'邮箱',
	bloodtp CHAR(1)  COMMENT'血型',
	ethnct VARCHAR(512)  COMMENT'民族',
	PRIMARY KEY(usrid)
)
COMMENT='用户基本信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t2_adiv;
CREATE TABLE t2_adiv (
	id BIGINT(20)  AUTO_INCREMENT  COMMENT'id',
	adiv_cd INT(10)  UNIQUE  NOT NULL  COMMENT'行政区划代码',
	unit_nm VARCHAR(512)  COMMENT'单位名称',
	plvl_adiv_cd CHAR(6)  COMMENT'上一级行政区划代码',
	adiv_hier CHAR(1)  COMMENT'行政区划层级',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(id)
)
COMMENT='行政区划信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t3_stat;
CREATE TABLE t3_stat (
	id BIGINT(20)  AUTO_INCREMENT  COMMENT'id',
	login_cnt INT(10)  COMMENT'已登陆次数',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(id)
)
COMMENT='统计信息',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t4_assc;
CREATE TABLE t4_assc (
	id BIGINT(20)  AUTO_INCREMENT  COMMENT'id',
	asscid CHAR(6)  UNIQUE  NOT NULL  COMMENT'协会id',
	assc_nm VARCHAR(512)  NOT NULL  COMMENT'协会名称',
	plvl_asscid CHAR(6)  COMMENT'上一级协会id',
	assc_hier CHAR(1)  COMMENT'协会层级',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(id)
)
COMMENT='协会信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t5_crcl_stdy;
CREATE TABLE t5_crcl_stdy (
	id BIGINT(20)  AUTO_INCREMENT  COMMENT'id',
	usrid INT(10)  COMMENT'用户id',
	crclid INT(10)  COMMENT'课程id',
	stdy_st CHAR(1)  COMMENT'学习状态',
	ty_grd FLOAT  COMMENT'学分',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(id,usrid,crclid)
)
COMMENT='课程学习信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t6_mgr_ahr;
CREATE TABLE t6_mgr_ahr (
	usrid BIGINT(20)  NOT NULL  AUTO_INCREMENT  COMMENT'id',
	usr_tp CHAR(2)  NOT NULL  COMMENT'用户类型',
	usr_nm VARCHAR(32)  UNIQUE  NOT NULL  COMMENT'用户名',
	nm VARCHAR(32)  NOT NULL  COMMENT'姓名',
	crdt_tp CHAR(2)  COMMENT'证件类型',
	crdt_no VARCHAR(512)  COMMENT'证件号',
	gnd CHAR(1)  COMMENT'性别',
	wrk_unit VARCHAR(512)  COMMENT'工作单位',
	pswd VARCHAR(512)  COMMENT'密码',
	post VARCHAR(512)  COMMENT'职务',
	brth_dt CHAR(8)  COMMENT'出生日期',
	adiv_cd CHAR(6)  COMMENT'行政区划代码',
	asscid CHAR(6)  COMMENT'协会id',
	mblph_no VARCHAR(256)  COMMENT'手机号',
	tms TIMESTAMP  COMMENT'维护时间',
	cty_prov_city_mgrid INT(10)  COMMENT'国家省市管理员id',
	assc_mgrid INT(10)  COMMENT'协会管理员id',
	email VARCHAR(512)  COMMENT'邮箱',
	rmrk VARCHAR(2048)  COMMENT'备注',
	PRIMARY KEY(usrid)
)
COMMENT='管理员权限信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t7_crcl;
CREATE TABLE t7_crcl (
	crclid BIGINT(20)  AUTO_INCREMENT  COMMENT'课程id',
	crcl_brf VARCHAR(512)  COMMENT'课程简介',
	adiv INT(10)  COMMENT'行政区划代码',
	crcl_nm VARCHAR(512)  COMMENT'课程名称',
	crcl_file_rte VARCHAR(512)  COMMENT'课程文件路径',
	crcl_attr CHAR(1)  COMMENT'课程属性（是否必修）',
	ty_grd INT(10)  COMMENT'学分',
	crcl_cgy CHAR(2)  COMMENT'课程类别',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(crclid)
)
COMMENT='课程信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t8_exam;
CREATE TABLE t8_exam (
	examid BIGINT(20)  AUTO_INCREMENT  COMMENT'考试id',
	exam_brf VARCHAR(2048)  COMMENT'考试简介',
	adiv_cd INT(10)  COMMENT'行政区划代码',
	exam_nm VARCHAR(512)  COMMENT'考试名称',
	exam_attr CHAR(1)  COMMENT'考试属性（是否必考）',
	ty_grd INT(10)  COMMENT'学分',
	prblmid_list VARCHAR(2048)  COMMENT'试题id列表',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(examid)
)
COMMENT='考试信息',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t9_tstlib;
CREATE TABLE t9_tstlib (
	prblmid BIGINT(20)  AUTO_INCREMENT  COMMENT'试题id',
	prblm_ppl VARCHAR(512)  COMMENT'出题者',
	prblm_tp CHAR(2)  COMMENT'试题类型',
	opt VARCHAR(512)  COMMENT'选项',
	ttl VARCHAR(512)  COMMENT'题目',
	prblm_aswr CHAR(1)  COMMENT'试题答案',
	scor INT(10)  COMMENT'分数',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(prblmid)
)
COMMENT='试题库',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS t10_exam_grd;
CREATE TABLE t10_exam_grd (
	id BIGINT(20)  AUTO_INCREMENT  COMMENT'id',
	usrid INT(10)  COMMENT'用户id',
	examid INT(10)  COMMENT'考试id',
	exam_grd INT(10)  COMMENT'考试成绩',
	exam_st CHAR(1)  COMMENT'考试状态',
	prblmid INT(10)  COMMENT'试题id',
	usr_aswr CHAR(1)  COMMENT'用户答案',
	prblm_aswr CHAR(1)  COMMENT'试题答案',
	tms TIMESTAMP  COMMENT'维护时间',
	PRIMARY KEY(id,usrid,examid,prblmid)
)
COMMENT='考试成绩信息表',
ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;