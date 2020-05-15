/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T18ExtrasPoints.h  
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

class T18ExtrasPoints :public AbsDAL {

public:
	T18ExtrasPoints():AbsDAL(&TableName){}
	~T18ExtrasPoints(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：邀请码 
	 * 字段类型：int  长度：null
	 */
	int scor;
	/**
	 * 字段描述：赛事类型 
	 * 字段类型：char  长度：null
	 */
	string type;
	/**
	 * 字段描述：用户id 
	 * 字段类型：int  长度：null
	 */
	int usrid;
	/**
	 * 字段描述：科目 
	 * 字段类型：char  长度：null
	 */
	string category;
public:
	
	
	T18ExtrasPoints& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T18ExtrasPoints& setScor(int ascor){
		scor = ascor;
		mapSQLTokens["scor"] = to_string(scor);
		return *this;
	}
	int getScor() {
		return scor;
	}
	
	
	T18ExtrasPoints& setType(string atype){
		type = atype;
		
		mapSQLTokens["type"] = "'"+type+"'";
		return *this;
	}
	string getType() {
		return type;
	}
	
	
	T18ExtrasPoints& setUsrid(int ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	int getUsrid() {
		return usrid;
	}
	
	
	T18ExtrasPoints& setCategory(string acategory){
		category = acategory;
		
		mapSQLTokens["category"] = "'"+category+"'";
		return *this;
	}
	string getCategory() {
		return category;
	}
	
};
const string T18ExtrasPoints::TableName = "t18extraspoints";
