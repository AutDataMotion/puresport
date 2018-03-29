/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T5CrclStdy.h  
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

class T5CrclStdy :public AbsDAL {

public:
	T5CrclStdy():AbsDAL(&TableName){}
	~T5CrclStdy(){}
	
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
	 * 字段描述：课程id 
	 * 字段类型：int  长度：null
	 */
	int crclid;
	/**
	 * 字段描述：学习状态 
	 * 字段类型：char  长度：1
	 */
	string stdy_st;
	/**
	 * 字段描述：学分 
	 * 字段类型：float  长度：null
	 */
	float ty_grd;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T5CrclStdy& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T5CrclStdy& setUsrid(int ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	int getUsrid() {
		return usrid;
	}
	
	
	T5CrclStdy& setCrclid(int acrclid){
		crclid = acrclid;
		mapSQLTokens["crclid"] = to_string(crclid);
		return *this;
	}
	int getCrclid() {
		return crclid;
	}
	
	
	T5CrclStdy& setStdy_st(string astdy_st){
		stdy_st = astdy_st;
		
		mapSQLTokens["stdy_st"] = "'"+stdy_st+"'";
		return *this;
	}
	string getStdy_st() {
		return stdy_st;
	}
	
	
	T5CrclStdy& setTy_grd(float aty_grd){
		ty_grd = aty_grd;
		mapSQLTokens["ty_grd"] = to_string(ty_grd);
		return *this;
	}
	float getTy_grd() {
		return ty_grd;
	}
	
	
	T5CrclStdy& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T5CrclStdy::TableName = "t5crclstdy";
