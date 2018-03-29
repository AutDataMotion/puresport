/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T3Statl.h  
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

class T3Statl :public AbsDAL {

public:
	T3Statl():AbsDAL(&TableName){}
	~T3Statl(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：已登陆次数 
	 * 字段类型：int  长度：null
	 */
	int login_cnt;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T3Statl& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T3Statl& setLogin_cnt(int alogin_cnt){
		login_cnt = alogin_cnt;
		mapSQLTokens["login_cnt"] = to_string(login_cnt);
		return *this;
	}
	int getLogin_cnt() {
		return login_cnt;
	}
	
	
	T3Statl& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T3Statl::TableName = "t3statl";
