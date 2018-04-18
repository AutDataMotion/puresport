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
	/**
	 * 字段描述： 
	 * 字段类型：varchar  长度：32
	 */
	string typecnt;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	long cnt_online;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	long cnt_2;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	long cnt_3;
	/**
	 * 字段描述： 
	 * 字段类型：varchar  长度：32
	 */
	string remark;
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
	
	
	T3Statl& setTypecnt(string atypecnt){
		typecnt = atypecnt;
		
		mapSQLTokens["typecnt"] = "'"+typecnt+"'";
		return *this;
	}
	string getTypecnt() {
		return typecnt;
	}
	
	
	T3Statl& setCnt_online(long acnt_online){
		cnt_online = acnt_online;
		mapSQLTokens["cnt_online"] = to_string(cnt_online);
		return *this;
	}
	long getCnt_online() {
		return cnt_online;
	}
	
	
	T3Statl& setCnt_2(long acnt_2){
		cnt_2 = acnt_2;
		mapSQLTokens["cnt_2"] = to_string(cnt_2);
		return *this;
	}
	long getCnt_2() {
		return cnt_2;
	}
	
	
	T3Statl& setCnt_3(long acnt_3){
		cnt_3 = acnt_3;
		mapSQLTokens["cnt_3"] = to_string(cnt_3);
		return *this;
	}
	long getCnt_3() {
		return cnt_3;
	}
	
	
	T3Statl& setRemark(string aremark){
		remark = aremark;
		
		mapSQLTokens["remark"] = "'"+remark+"'";
		return *this;
	}
	string getRemark() {
		return remark;
	}
	
};
const string T3Statl::TableName = "t3statl";
