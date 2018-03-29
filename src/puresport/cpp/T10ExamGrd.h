/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T10ExamGrd.h  
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

class T10ExamGrd :public AbsDAL {

public:
	T10ExamGrd():AbsDAL(&TableName){}
	~T10ExamGrd(){}
	
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
	 * 字段类型：char  长度：1
	 */
	string exam_st;
	/**
	 * 字段描述：试题id 
	 * 字段类型：int  长度：null
	 */
	int prblmid;
	/**
	 * 字段描述：用户答案 
	 * 字段类型：char  长度：1
	 */
	string usr_aswr;
	/**
	 * 字段描述：试题答案 
	 * 字段类型：char  长度：1
	 */
	string prblm_aswr;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T10ExamGrd& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T10ExamGrd& setUsrid(int ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	int getUsrid() {
		return usrid;
	}
	
	
	T10ExamGrd& setExamid(int aexamid){
		examid = aexamid;
		mapSQLTokens["examid"] = to_string(examid);
		return *this;
	}
	int getExamid() {
		return examid;
	}
	
	
	T10ExamGrd& setExam_grd(int aexam_grd){
		exam_grd = aexam_grd;
		mapSQLTokens["exam_grd"] = to_string(exam_grd);
		return *this;
	}
	int getExam_grd() {
		return exam_grd;
	}
	
	
	T10ExamGrd& setExam_st(string aexam_st){
		exam_st = aexam_st;
		
		mapSQLTokens["exam_st"] = "'"+exam_st+"'";
		return *this;
	}
	string getExam_st() {
		return exam_st;
	}
	
	
	T10ExamGrd& setPrblmid(int aprblmid){
		prblmid = aprblmid;
		mapSQLTokens["prblmid"] = to_string(prblmid);
		return *this;
	}
	int getPrblmid() {
		return prblmid;
	}
	
	
	T10ExamGrd& setUsr_aswr(string ausr_aswr){
		usr_aswr = ausr_aswr;
		
		mapSQLTokens["usr_aswr"] = "'"+usr_aswr+"'";
		return *this;
	}
	string getUsr_aswr() {
		return usr_aswr;
	}
	
	
	T10ExamGrd& setPrblm_aswr(string aprblm_aswr){
		prblm_aswr = aprblm_aswr;
		
		mapSQLTokens["prblm_aswr"] = "'"+prblm_aswr+"'";
		return *this;
	}
	string getPrblm_aswr() {
		return prblm_aswr;
	}
	
	
	T10ExamGrd& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T10ExamGrd::TableName = "t10examgrd";
