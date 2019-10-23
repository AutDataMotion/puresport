/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T12HighestScore.h  
 * description:	
 * author:	zhongweng.hao@qq.com
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 *  - -   : :        Zhongweng       1.0         1.0 Version   
 */
#pragma once
#include <string>
#include "AbsDAL.h"
using namespace std;

class T12HighestScore :public AbsDAL {

public:
	T12HighestScore():AbsDAL(&TableName){}
	~T12HighestScore(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：用户id 
	 * 字段类型：int  长度：null
	 */
	int usrid;
	/**
	 * 字段描述：考试id 
	 * 字段类型：int  长度：null
	 */
	int examid;
	/**
	 * 字段描述：考试成绩 
	 * 字段类型：int  长度：null
	 */
	int exam_grd;
	/**
	 * 字段描述：考试状态 
	 * 字段类型：char  长度：null
	 */
	string exam_st;
	/**
	 * 字段描述：考试渠道 
	 * 字段类型：char  长度：null
	 */
	string exam_channel;
	/**
	 * 字段描述：考试次数 
	 * 字段类型：int  长度：null
	 */
	int exam_num;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
	/**
	 * 字段描述：考试名称 
	 * 字段类型：varchar  长度：null
	 */
	string exam_nm;
public:
	
	
	T12HighestScore& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T12HighestScore& setUsrid(int ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	int getUsrid() {
		return usrid;
	}
	
	
	T12HighestScore& setExamid(int aexamid){
		examid = aexamid;
		mapSQLTokens["examid"] = to_string(examid);
		return *this;
	}
	int getExamid() {
		return examid;
	}
	
	
	T12HighestScore& setExam_grd(int aexam_grd){
		exam_grd = aexam_grd;
		mapSQLTokens["exam_grd"] = to_string(exam_grd);
		return *this;
	}
	int getExam_grd() {
		return exam_grd;
	}
	
	
	T12HighestScore& setExam_st(string aexam_st){
		exam_st = aexam_st;
		
		mapSQLTokens["exam_st"] = "'"+exam_st+"'";
		return *this;
	}
	string getExam_st() {
		return exam_st;
	}
	
	
	T12HighestScore& setExam_channel(string aexam_channel){
		exam_channel = aexam_channel;
		
		mapSQLTokens["exam_channel"] = "'"+exam_channel+"'";
		return *this;
	}
	string getExam_channel() {
		return exam_channel;
	}
	
	
	T12HighestScore& setExam_num(int aexam_num){
		exam_num = aexam_num;
		mapSQLTokens["exam_num"] = to_string(exam_num);
		return *this;
	}
	int getExam_num() {
		return exam_num;
	}
	
	
	T12HighestScore& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
	
	T12HighestScore& setExam_nm(string aexam_nm){
		exam_nm = aexam_nm;
		
		mapSQLTokens["exam_nm"] = "'"+exam_nm+"'";
		return *this;
	}
	string getExam_nm() {
		return exam_nm;
	}
	
};
const string T12HighestScore::TableName = "t12highestscore";
