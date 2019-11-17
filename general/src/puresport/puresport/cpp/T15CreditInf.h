/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T15CreditInf.h  
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

class T15CreditInf :public AbsDAL {

public:
	T15CreditInf():AbsDAL(&TableName){}
	~T15CreditInf(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：赛事类型 
	 * 字段类型：char  长度：null
	 */
	string type;
	/**
	 * 字段描述：姓名 
	 * 字段类型：varchar  长度：null
	 */
	string nm;
	/**
	 * 字段描述：证件类型 
	 * 字段类型：char  长度：null
	 */
	string crdt_tp;
	/**
	 * 字段描述：证件号 
	 * 字段类型：varchar  长度：null
	 */
	string crdt_no;
	/**
	 * 字段描述：用户id 
	 * 字段类型：bigint  长度：null
	 */
	long usrid;
	/**
	 * 字段描述：生成时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
	/**
	 * 字段描述：类型 
	 * 字段类型：char  长度：null
	 */
	string flag;
	/**
	 * 字段描述：文件路径 
	 * 字段类型：varchar  长度：null
	 */
	string file_path;
	/**
	 * 字段描述：证书编号 
	 * 字段类型：char  长度：null
	 */
	string credit_no;
public:
	
	
	T15CreditInf& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T15CreditInf& setType(string atype){
		type = atype;
		
		mapSQLTokens["type"] = "'"+type+"'";
		return *this;
	}
	string getType() {
		return type;
	}
	
	
	T15CreditInf& setNm(string anm){
		nm = anm;
		
		mapSQLTokens["nm"] = "'"+nm+"'";
		return *this;
	}
	string getNm() {
		return nm;
	}
	
	
	T15CreditInf& setCrdt_tp(string acrdt_tp){
		crdt_tp = acrdt_tp;
		
		mapSQLTokens["crdt_tp"] = "'"+crdt_tp+"'";
		return *this;
	}
	string getCrdt_tp() {
		return crdt_tp;
	}
	
	
	T15CreditInf& setCrdt_no(string acrdt_no){
		crdt_no = acrdt_no;
		
		mapSQLTokens["crdt_no"] = "'"+crdt_no+"'";
		return *this;
	}
	string getCrdt_no() {
		return crdt_no;
	}
	
	
	T15CreditInf& setUsrid(long ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	long getUsrid() {
		return usrid;
	}
	
	
	T15CreditInf& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
	
	T15CreditInf& setFlag(string aflag){
		flag = aflag;
		
		mapSQLTokens["flag"] = "'"+flag+"'";
		return *this;
	}
	string getFlag() {
		return flag;
	}
	
	
	T15CreditInf& setFile_path(string afile_path){
		file_path = afile_path;
		
		mapSQLTokens["file_path"] = "'"+file_path+"'";
		return *this;
	}
	string getFile_path() {
		return file_path;
	}
	
	
	T15CreditInf& setCredit_no(string acredit_no){
		credit_no = acredit_no;
		
		mapSQLTokens["credit_no"] = "'"+credit_no+"'";
		return *this;
	}
	string getCredit_no() {
		return credit_no;
	}
	
};
const string T15CreditInf::TableName = "t15creditinf";
