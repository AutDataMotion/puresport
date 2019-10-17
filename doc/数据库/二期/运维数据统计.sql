#数据量统计
SELECT table_schema,table_name,table_rows,CREATE_TIME FROM information_schema.TABLES  WHERE TABLE_SCHEMA='puresport' order by table_rows desc ;
#tps统计
#试题提交
select count(date_format(t.exam_end_tm, '%Y-%m-%d %h:%i:%s' ) ), date_format(t.exam_end_tm, '%Y-%m-%d %h:%i:%s' )  from t10_exam_grd t where
date_format(t.exam_end_tm, '%Y%m%d%h%i%s' ) between '20180710020101' and '20181010020101'
group by date_format(t.exam_end_tm, '%Y-%m-%d %h:%i:%s' )  order by count(date_format(t.exam_end_tm, '%Y-%m-%d %h:%i:%s' ) ) desc ;
#考试提交
select count(date_format(t.tms, '%Y-%m-%d %h:%i:%s' ) )/2, date_format(t.tms, '%Y-%m-%d %h:%i:%s' )  from t11_exam_stat t where
date_format(t.tms, '%Y%m%d%h%i%s' ) between '20180714010101' and '20181010020101'
group by date_format(t.tms, '%Y-%m-%d %h:%i:%s' )  order by count(date_format(t.tms, '%Y-%m-%d %h:%i:%s' ) ) desc ;
#学习提交
select count(date_format(t.tms, '%Y-%m-%d %h:%i:%s' ) )/2, date_format(t.tms, '%Y-%m-%d %h:%i:%s' )  from t5_crcl_stdy t where
date_format(t.tms, '%Y%m%d%h%i%s' ) between '20180714010101' and '20181010020101'
group by date_format(t.tms, '%Y-%m-%d %h:%i:%s' )  order by count(date_format(t.tms, '%Y-%m-%d %h:%i:%s' ) ) desc ;
#考试总人次
select count(t.exam_num), t.exam_nm  from t11_exam_stat t where t.exam_st=1 group by t.exam_nm;
#课程学习总人次
select count(*) from t5_crcl_stdy;
#运动员总数
select date_format(t.tms, '%Y-%m-%d'), count(*) from t1_usr_bsc t GROUP BY date_format(t.tms, '%Y-%m-%d');
#t11_exam_stat考试总次数
select date_format(t.tms, '%Y-%m-%d'), count(*) from t11_exam_stat t where t.exam_st=1 GROUP BY date_format(t.tms, '%Y-%m-%d');
#学习总次数
select date_format(t.tms, '%Y-%m-%d'), count(*) from t5_crcl_stdy t GROUP BY date_format(t.tms, '%Y-%m-%d');