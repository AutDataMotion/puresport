/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T9Tstlib.h  
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

class T9Tstlib :public AbsDAL {

public:
	T9Tstlib():AbsDAL(&TableName){}
	~T9Tstlib(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：试题id 
	 * 字段类型：bigint  长度：null
	 */
	long prblmid;
	/**
	 * 字段描述：出题者 
	 * 字段类型：varchar  长度：512
	 */
	string prblm_ppl;
	/**
	 * 字段描述：试题类型 
	 * 字段类型：char  长度：2
	 */
	string prblm_tp;
	/**
	 * 字段描述：选项 
	 * 字段类型：varchar  长度：512
	 */
	string opt;
	/**
	 * 字段描述：题目 
	 * 字段类型：varchar  长度：512
	 */
	string ttl;
	/**
	 * 字段描述：试题答案 
	 * 字段类型：char  长度：1
	 */
	string prblm_aswr;
	/**
	 * 字段描述：分数 
	 * 字段类型：int  长度：null
	 */
	int scor;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T9Tstlib& setPrblmid(long aprblmid){
		prblmid = aprblmid;
		mapSQLTokens["prblmid"] = to_string(prblmid);
		return *this;
	}
	long getPrblmid() {
		return prblmid;
	}
	
	
	T9Tstlib& setPrblm_ppl(string aprblm_ppl){
		prblm_ppl = aprblm_ppl;
		
		mapSQLTokens["prblm_ppl"] = "'"+prblm_ppl+"'";
		return *this;
	}
	string getPrblm_ppl() {
		return prblm_ppl;
	}
	
	
	T9Tstlib& setPrblm_tp(string aprblm_tp){
		prblm_tp = aprblm_tp;
		
		mapSQLTokens["prblm_tp"] = "'"+prblm_tp+"'";
		return *this;
	}
	string getPrblm_tp() {
		return prblm_tp;
	}
	
	
	T9Tstlib& setOpt(string aopt){
		opt = aopt;
		
		mapSQLTokens["opt"] = "'"+opt+"'";
		return *this;
	}
	string getOpt() {
		return opt;
	}
	
	
	T9Tstlib& setTtl(string attl){
		ttl = attl;
		
		mapSQLTokens["ttl"] = "'"+ttl+"'";
		return *this;
	}
	string getTtl() {
		return ttl;
	}
	
	
	T9Tstlib& setPrblm_aswr(string aprblm_aswr){
		prblm_aswr = aprblm_aswr;
		
		mapSQLTokens["prblm_aswr"] = "'"+prblm_aswr+"'";
		return *this;
	}
	string getPrblm_aswr() {
		return prblm_aswr;
	}
	
	
	T9Tstlib& setScor(int ascor){
		scor = ascor;
		mapSQLTokens["scor"] = to_string(scor);
		return *this;
	}
	int getScor() {
		return scor;
	}
	
	
	T9Tstlib& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T9Tstlib::TableName = "t9tstlib";
