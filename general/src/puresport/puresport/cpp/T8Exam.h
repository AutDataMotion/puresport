/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T8Exam.h  
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

class T8Exam :public AbsDAL {

public:
	T8Exam():AbsDAL(&TableName){}
	~T8Exam(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：考试id 
	 * 字段类型：bigint  长度：null
	 */
	long examid;
	/**
	 * 字段描述：考试简介 
	 * 字段类型：varchar  长度：2048
	 */
	string exam_brf;
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：int  长度：null
	 */
	int adiv_cd;
	/**
	 * 字段描述：考试名称 
	 * 字段类型：varchar  长度：512
	 */
	string exam_nm;
	/**
	 * 字段描述：考试属性（是否必考） 
	 * 字段类型：char  长度：1
	 */
	string exam_attr;
	/**
	 * 字段描述：学分 
	 * 字段类型：int  长度：null
	 */
	int ty_grd;
	/**
	 * 字段描述：试题id列表 
	 * 字段类型：varchar  长度：2048
	 */
	string prblmid_list;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T8Exam& setExamid(long aexamid){
		examid = aexamid;
		mapSQLTokens["examid"] = to_string(examid);
		return *this;
	}
	long getExamid() {
		return examid;
	}
	
	
	T8Exam& setExam_brf(string aexam_brf){
		exam_brf = aexam_brf;
		
		mapSQLTokens["exam_brf"] = "'"+exam_brf+"'";
		return *this;
	}
	string getExam_brf() {
		return exam_brf;
	}
	
	
	T8Exam& setAdiv_cd(int aadiv_cd){
		adiv_cd = aadiv_cd;
		mapSQLTokens["adiv_cd"] = to_string(adiv_cd);
		return *this;
	}
	int getAdiv_cd() {
		return adiv_cd;
	}
	
	
	T8Exam& setExam_nm(string aexam_nm){
		exam_nm = aexam_nm;
		
		mapSQLTokens["exam_nm"] = "'"+exam_nm+"'";
		return *this;
	}
	string getExam_nm() {
		return exam_nm;
	}
	
	
	T8Exam& setExam_attr(string aexam_attr){
		exam_attr = aexam_attr;
		
		mapSQLTokens["exam_attr"] = "'"+exam_attr+"'";
		return *this;
	}
	string getExam_attr() {
		return exam_attr;
	}
	
	
	T8Exam& setTy_grd(int aty_grd){
		ty_grd = aty_grd;
		mapSQLTokens["ty_grd"] = to_string(ty_grd);
		return *this;
	}
	int getTy_grd() {
		return ty_grd;
	}
	
	
	T8Exam& setPrblmid_list(string aprblmid_list){
		prblmid_list = aprblmid_list;
		
		mapSQLTokens["prblmid_list"] = "'"+prblmid_list+"'";
		return *this;
	}
	string getPrblmid_list() {
		return prblmid_list;
	}
	
	
	T8Exam& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T8Exam::TableName = "t8exam";
