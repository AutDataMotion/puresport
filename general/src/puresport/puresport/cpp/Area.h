/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	Area.h  
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

class Area :public AbsDAL {

public:
	Area():AbsDAL(&TableName){}
	~Area(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：区域主键 
	 * 字段类型：int  长度：null
	 */
	int id;
	/**
	 * 字段描述：区域名称 
	 * 字段类型：varchar  长度：16
	 */
	string name;
	/**
	 * 字段描述：区域代码 
	 * 字段类型：varchar  长度：128
	 */
	string code;
	/**
	 * 字段描述：上级主键 
	 * 字段类型：int  长度：null
	 */
	int parent_id;
public:
	
	
	Area& setId(int aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	int getId() {
		return id;
	}
	
	
	Area& setName(string aname){
		name = aname;
		
		mapSQLTokens["name"] = "'"+name+"'";
		return *this;
	}
	string getName() {
		return name;
	}
	
	
	Area& setCode(string acode){
		code = acode;
		
		mapSQLTokens["code"] = "'"+code+"'";
		return *this;
	}
	string getCode() {
		return code;
	}
	
	
	Area& setParent_id(int aparent_id){
		parent_id = aparent_id;
		mapSQLTokens["parent_id"] = to_string(parent_id);
		return *this;
	}
	int getParent_id() {
		return parent_id;
	}
	
};
const string Area::TableName = "area";
