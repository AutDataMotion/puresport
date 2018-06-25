/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	Sport_Item.h  
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

class Sport_Item :public AbsDAL {

public:
	Sport_Item():AbsDAL(&TableName){}
	~Sport_Item(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	int id;
	/**
	 * 字段描述：项目名称 
	 * 字段类型：varchar  长度：null
	 */
	string name;
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	int parentid;
public:
	
	
	Sport_Item& setId(int aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	int getId() {
		return id;
	}
	
	
	Sport_Item& setName(string aname){
		name = aname;
		
		mapSQLTokens["name"] = "'"+name+"'";
		return *this;
	}
	string getName() {
		return name;
	}
	
	
	Sport_Item& setParentid(int aparentid){
		parentid = aparentid;
		mapSQLTokens["parentid"] = to_string(parentid);
		return *this;
	}
	int getParentid() {
		return parentid;
	}
	
};
const string Sport_Item::TableName = "sport_item";
