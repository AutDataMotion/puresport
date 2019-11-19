/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	R16GroupUsr.h  
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

class R16GroupUsr :public AbsDAL {

public:
	R16GroupUsr():AbsDAL(&TableName){}
	~R16GroupUsr(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	long group_id;
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	long user_id;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	int type;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	int status;
	/**
	 * 字段描述：扩展 
	 * 字段类型：varchar  长度：null
	 */
	string ext;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	R16GroupUsr& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	R16GroupUsr& setGroup_id(long agroup_id){
		group_id = agroup_id;
		mapSQLTokens["group_id"] = to_string(group_id);
		return *this;
	}
	long getGroup_id() {
		return group_id;
	}
	
	
	R16GroupUsr& setUser_id(long auser_id){
		user_id = auser_id;
		mapSQLTokens["user_id"] = to_string(user_id);
		return *this;
	}
	long getUser_id() {
		return user_id;
	}
	
	
	R16GroupUsr& setType(int atype){
		type = atype;
		mapSQLTokens["type"] = to_string(type);
		return *this;
	}
	int getType() {
		return type;
	}
	
	
	R16GroupUsr& setStatus(int astatus){
		status = astatus;
		mapSQLTokens["status"] = to_string(status);
		return *this;
	}
	int getStatus() {
		return status;
	}
	
	
	R16GroupUsr& setExt(string aext){
		ext = aext;
		
		mapSQLTokens["ext"] = "'"+ext+"'";
		return *this;
	}
	string getExt() {
		return ext;
	}
	
	
	R16GroupUsr& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string R16GroupUsr::TableName = "r16groupusr";
