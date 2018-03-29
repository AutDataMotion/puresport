/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	R4Assc.h  
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

class R4Assc :public AbsDAL {

public:
	R4Assc():AbsDAL(&TableName){}
	~R4Assc(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：协会id 
	 * 字段类型：char  长度：6
	 */
	string asscid;
	/**
	 * 字段描述：协会名称 
	 * 字段类型：varchar  长度：512
	 */
	string assc_nm;
	/**
	 * 字段描述：上一级协会id 
	 * 字段类型：char  长度：6
	 */
	string plvl_asscid;
	/**
	 * 字段描述：协会层级 
	 * 字段类型：char  长度：1
	 */
	string assc_hier;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	R4Assc& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	R4Assc& setAsscid(string aasscid){
		asscid = aasscid;
		
		mapSQLTokens["asscid"] = "'"+asscid+"'";
		return *this;
	}
	string getAsscid() {
		return asscid;
	}
	
	
	R4Assc& setAssc_nm(string aassc_nm){
		assc_nm = aassc_nm;
		
		mapSQLTokens["assc_nm"] = "'"+assc_nm+"'";
		return *this;
	}
	string getAssc_nm() {
		return assc_nm;
	}
	
	
	R4Assc& setPlvl_asscid(string aplvl_asscid){
		plvl_asscid = aplvl_asscid;
		
		mapSQLTokens["plvl_asscid"] = "'"+plvl_asscid+"'";
		return *this;
	}
	string getPlvl_asscid() {
		return plvl_asscid;
	}
	
	
	R4Assc& setAssc_hier(string aassc_hier){
		assc_hier = aassc_hier;
		
		mapSQLTokens["assc_hier"] = "'"+assc_hier+"'";
		return *this;
	}
	string getAssc_hier() {
		return assc_hier;
	}
	
	
	R4Assc& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string R4Assc::TableName = "r4assc";
