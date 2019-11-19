/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T15Group.h  
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

class T15Group :public AbsDAL {

public:
	T15Group():AbsDAL(&TableName){}
	~T15Group(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：管理者id 
	 * 字段类型：bigint  长度：null
	 */
	long mgr_id;
	/**
	 * 字段描述：标题 
	 * 字段类型：varchar  长度：null
	 */
	string title;
	/**
	 * 字段描述： 
	 * 字段类型：varchar  长度：null
	 */
	string content;
	/**
	 * 字段描述：类型 
	 * 字段类型：int  长度：null
	 */
	int type;
	/**
	 * 字段描述：状态 
	 * 字段类型：int  长度：null
	 */
	int status;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T15Group& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T15Group& setMgr_id(long amgr_id){
		mgr_id = amgr_id;
		mapSQLTokens["mgr_id"] = to_string(mgr_id);
		return *this;
	}
	long getMgr_id() {
		return mgr_id;
	}
	
	
	T15Group& setTitle(string atitle){
		title = atitle;
		
		mapSQLTokens["title"] = "'"+title+"'";
		return *this;
	}
	string getTitle() {
		return title;
	}
	
	
	T15Group& setContent(string acontent){
		content = acontent;
		
		mapSQLTokens["content"] = "'"+content+"'";
		return *this;
	}
	string getContent() {
		return content;
	}
	
	
	T15Group& setType(int atype){
		type = atype;
		mapSQLTokens["type"] = to_string(type);
		return *this;
	}
	int getType() {
		return type;
	}
	
	
	T15Group& setStatus(int astatus){
		status = astatus;
		mapSQLTokens["status"] = to_string(status);
		return *this;
	}
	int getStatus() {
		return status;
	}
	
	
	T15Group& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T15Group::TableName = "t15group";
