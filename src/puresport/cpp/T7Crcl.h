/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T7Crcl.h  
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

class T7Crcl :public AbsDAL {

public:
	T7Crcl():AbsDAL(&TableName){}
	~T7Crcl(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：课程id 
	 * 字段类型：bigint  长度：null
	 */
	long crclid;
	/**
	 * 字段描述：课程简介 
	 * 字段类型：varchar  长度：512
	 */
	string crcl_brf;
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：int  长度：null
	 */
	int adiv;
	/**
	 * 字段描述：课程名称 
	 * 字段类型：varchar  长度：512
	 */
	string crcl_nm;
	/**
	 * 字段描述：课程文件路径 
	 * 字段类型：varchar  长度：512
	 */
	string crcl_file_rte;
	/**
	 * 字段描述：课程属性（是否必修） 
	 * 字段类型：char  长度：1
	 */
	string crcl_attr;
	/**
	 * 字段描述：学分 
	 * 字段类型：int  长度：null
	 */
	int ty_grd;
	/**
	 * 字段描述：课程类别 
	 * 字段类型：char  长度：2
	 */
	string crcl_cgy;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
public:
	
	
	T7Crcl& setCrclid(long acrclid){
		crclid = acrclid;
		mapSQLTokens["crclid"] = to_string(crclid);
		return *this;
	}
	long getCrclid() {
		return crclid;
	}
	
	
	T7Crcl& setCrcl_brf(string acrcl_brf){
		crcl_brf = acrcl_brf;
		
		mapSQLTokens["crcl_brf"] = "'"+crcl_brf+"'";
		return *this;
	}
	string getCrcl_brf() {
		return crcl_brf;
	}
	
	
	T7Crcl& setAdiv(int aadiv){
		adiv = aadiv;
		mapSQLTokens["adiv"] = to_string(adiv);
		return *this;
	}
	int getAdiv() {
		return adiv;
	}
	
	
	T7Crcl& setCrcl_nm(string acrcl_nm){
		crcl_nm = acrcl_nm;
		
		mapSQLTokens["crcl_nm"] = "'"+crcl_nm+"'";
		return *this;
	}
	string getCrcl_nm() {
		return crcl_nm;
	}
	
	
	T7Crcl& setCrcl_file_rte(string acrcl_file_rte){
		crcl_file_rte = acrcl_file_rte;
		
		mapSQLTokens["crcl_file_rte"] = "'"+crcl_file_rte+"'";
		return *this;
	}
	string getCrcl_file_rte() {
		return crcl_file_rte;
	}
	
	
	T7Crcl& setCrcl_attr(string acrcl_attr){
		crcl_attr = acrcl_attr;
		
		mapSQLTokens["crcl_attr"] = "'"+crcl_attr+"'";
		return *this;
	}
	string getCrcl_attr() {
		return crcl_attr;
	}
	
	
	T7Crcl& setTy_grd(int aty_grd){
		ty_grd = aty_grd;
		mapSQLTokens["ty_grd"] = to_string(ty_grd);
		return *this;
	}
	int getTy_grd() {
		return ty_grd;
	}
	
	
	T7Crcl& setCrcl_cgy(string acrcl_cgy){
		crcl_cgy = acrcl_cgy;
		
		mapSQLTokens["crcl_cgy"] = "'"+crcl_cgy+"'";
		return *this;
	}
	string getCrcl_cgy() {
		return crcl_cgy;
	}
	
	
	T7Crcl& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
};
const string T7Crcl::TableName = "t7crcl";
