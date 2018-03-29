/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T2Adiv.h  
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

class T2Adiv :public AbsDAL {

public:
	T2Adiv():AbsDAL(&TableName){}
	~T2Adiv(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：int  长度：null
	 */
	int adiv_cd;
	/**
	 * 字段描述：单位名称 
	 * 字段类型：varchar  长度：512
	 */
	string unit_nm;
	/**
	 * 字段描述：上一级行政区划代码 
	 * 字段类型：char  长度：6
	 */
	string plvl_adiv_cd;
	/**
	 * 字段描述：行政区划层级 
	 * 字段类型：char  长度：1
	 */
	string adiv_hier;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T2Adiv& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T2Adiv& setAdiv_cd(int aadiv_cd){
		adiv_cd = aadiv_cd;
		mapSQLTokens["adiv_cd"] = to_string(adiv_cd);
		return *this;
	}
	int getAdiv_cd() {
		return adiv_cd;
	}
	
	
	T2Adiv& setUnit_nm(string aunit_nm){
		unit_nm = aunit_nm;
		
		mapSQLTokens["unit_nm"] = "'"+unit_nm+"'";
		return *this;
	}
	string getUnit_nm() {
		return unit_nm;
	}
	
	
	T2Adiv& setPlvl_adiv_cd(string aplvl_adiv_cd){
		plvl_adiv_cd = aplvl_adiv_cd;
		
		mapSQLTokens["plvl_adiv_cd"] = "'"+plvl_adiv_cd+"'";
		return *this;
	}
	string getPlvl_adiv_cd() {
		return plvl_adiv_cd;
	}
	
	
	T2Adiv& setAdiv_hier(string aadiv_hier){
		adiv_hier = aadiv_hier;
		
		mapSQLTokens["adiv_hier"] = "'"+adiv_hier+"'";
		return *this;
	}
	string getAdiv_hier() {
		return adiv_hier;
	}
	
	
	T2Adiv& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T2Adiv::TableName = "t2adiv";
