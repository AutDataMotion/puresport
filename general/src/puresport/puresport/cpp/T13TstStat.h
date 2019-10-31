/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T13TstStat.h  
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

class T13TstStat :public AbsDAL {

public:
	T13TstStat():AbsDAL(&TableName){}
	~T13TstStat(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：试题id 
	 * 字段类型：int  长度：null
	 */
	int prblmid;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
	/**
	 * 字段描述：正确答题次数 
	 * 字段类型：bigint  长度：null
	 */
	biginteger right_num;
	/**
	 * 字段描述：错误答题次数 
	 * 字段类型：bigint  长度：null
	 */
	biginteger wrong_num;
public:
	
	
	T13TstStat& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T13TstStat& setPrblmid(int aprblmid){
		prblmid = aprblmid;
		mapSQLTokens["prblmid"] = to_string(prblmid);
		return *this;
	}
	int getPrblmid() {
		return prblmid;
	}
	
	
	T13TstStat& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
	
	T13TstStat& setRight_num(biginteger aright_num){
		right_num = aright_num;
		mapSQLTokens["right_num"] = to_string(right_num);
		return *this;
	}
	biginteger getRight_num() {
		return right_num;
	}
	
	
	T13TstStat& setWrong_num(biginteger awrong_num){
		wrong_num = awrong_num;
		mapSQLTokens["wrong_num"] = to_string(wrong_num);
		return *this;
	}
	biginteger getWrong_num() {
		return wrong_num;
	}
	
};
const string T13TstStat::TableName = "t13tststat";
