/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T6MgrAhr.h  
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

class T6MgrAhr :public AbsDAL {

public:
	T6MgrAhr():AbsDAL(&TableName){}
	~T6MgrAhr(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long usrid;
	/**
	 * 字段描述：用户类型 
	 * 字段类型：varchar  长度：32
	 */
	string usr_tp;
	/**
	 * 字段描述：用户名 
	 * 字段类型：varchar  长度：32
	 */
	string usr_nm;
	/**
	 * 字段描述：姓名 
	 * 字段类型：varchar  长度：32
	 */
	string nm;
	/**
	 * 字段描述：证件类型 
	 * 字段类型：varchar  长度：16
	 */
	string crdt_tp;
	/**
	 * 字段描述：证件号 
	 * 字段类型：varchar  长度：64
	 */
	string crdt_no;
	/**
	 * 字段描述：性别 
	 * 字段类型：char  长度：2
	 */
	string gnd;
	/**
	 * 字段描述：工作单位 
	 * 字段类型：varchar  长度：512
	 */
	string wrk_unit;
	/**
	 * 字段描述：密码 
	 * 字段类型：varchar  长度：64
	 */
	string pswd;
	/**
	 * 字段描述：职务 
	 * 字段类型：varchar  长度：128
	 */
	string post;
	/**
	 * 字段描述：出生日期 
	 * 字段类型：varchar  长度：16
	 */
	string brth_dt;
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：char  长度：6
	 */
	string adiv_cd;
	/**
	 * 字段描述：协会id 
	 * 字段类型：char  长度：6
	 */
	string asscid;
	/**
	 * 字段描述：手机号 
	 * 字段类型：varchar  长度：32
	 */
	string mblph_no;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
	/**
	 * 字段描述：国家省市管理员id 
	 * 字段类型：int  长度：null
	 */
	int cty_prov_city_mgrid;
	/**
	 * 字段描述：协会管理员id 
	 * 字段类型：int  长度：null
	 */
	int assc_mgrid;
	/**
	 * 字段描述：邮箱 
	 * 字段类型：varchar  长度：64
	 */
	string email;
	/**
	 * 字段描述：备注 
	 * 字段类型：varchar  长度：512
	 */
	string rmrk;
	/**
	 * 字段描述：级别类型 
	 * 字段类型：char  长度：8
	 */
	string typeleve;
	/**
	 * 字段描述：省 
	 * 字段类型：varchar  长度：128
	 */
	string province;
	/**
	 * 字段描述：城市 
	 * 字段类型：varchar  长度：128
	 */
	string city;
	/**
	 * 字段描述：协会 
	 * 字段类型：varchar  长度：512
	 */
	string institute;
public:
	
	
	T6MgrAhr& setUsrid(long ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	long getUsrid() {
		return usrid;
	}
	
	
	T6MgrAhr& setUsr_tp(string ausr_tp){
		usr_tp = ausr_tp;
		
		mapSQLTokens["usr_tp"] = "'"+usr_tp+"'";
		return *this;
	}
	string getUsr_tp() {
		return usr_tp;
	}
	
	
	T6MgrAhr& setUsr_nm(string ausr_nm){
		usr_nm = ausr_nm;
		
		mapSQLTokens["usr_nm"] = "'"+usr_nm+"'";
		return *this;
	}
	string getUsr_nm() {
		return usr_nm;
	}
	
	
	T6MgrAhr& setNm(string anm){
		nm = anm;
		
		mapSQLTokens["nm"] = "'"+nm+"'";
		return *this;
	}
	string getNm() {
		return nm;
	}
	
	
	T6MgrAhr& setCrdt_tp(string acrdt_tp){
		crdt_tp = acrdt_tp;
		
		mapSQLTokens["crdt_tp"] = "'"+crdt_tp+"'";
		return *this;
	}
	string getCrdt_tp() {
		return crdt_tp;
	}
	
	
	T6MgrAhr& setCrdt_no(string acrdt_no){
		crdt_no = acrdt_no;
		
		mapSQLTokens["crdt_no"] = "'"+crdt_no+"'";
		return *this;
	}
	string getCrdt_no() {
		return crdt_no;
	}
	
	
	T6MgrAhr& setGnd(string agnd){
		gnd = agnd;
		
		mapSQLTokens["gnd"] = "'"+gnd+"'";
		return *this;
	}
	string getGnd() {
		return gnd;
	}
	
	
	T6MgrAhr& setWrk_unit(string awrk_unit){
		wrk_unit = awrk_unit;
		
		mapSQLTokens["wrk_unit"] = "'"+wrk_unit+"'";
		return *this;
	}
	string getWrk_unit() {
		return wrk_unit;
	}
	
	
	T6MgrAhr& setPswd(string apswd){
		pswd = apswd;
		
		mapSQLTokens["pswd"] = "'"+pswd+"'";
		return *this;
	}
	string getPswd() {
		return pswd;
	}
	
	
	T6MgrAhr& setPost(string apost){
		post = apost;
		
		mapSQLTokens["post"] = "'"+post+"'";
		return *this;
	}
	string getPost() {
		return post;
	}
	
	
	T6MgrAhr& setBrth_dt(string abrth_dt){
		brth_dt = abrth_dt;
		
		mapSQLTokens["brth_dt"] = "'"+brth_dt+"'";
		return *this;
	}
	string getBrth_dt() {
		return brth_dt;
	}
	
	
	T6MgrAhr& setAdiv_cd(string aadiv_cd){
		adiv_cd = aadiv_cd;
		
		mapSQLTokens["adiv_cd"] = "'"+adiv_cd+"'";
		return *this;
	}
	string getAdiv_cd() {
		return adiv_cd;
	}
	
	
	T6MgrAhr& setAsscid(string aasscid){
		asscid = aasscid;
		
		mapSQLTokens["asscid"] = "'"+asscid+"'";
		return *this;
	}
	string getAsscid() {
		return asscid;
	}
	
	
	T6MgrAhr& setMblph_no(string amblph_no){
		mblph_no = amblph_no;
		
		mapSQLTokens["mblph_no"] = "'"+mblph_no+"'";
		return *this;
	}
	string getMblph_no() {
		return mblph_no;
	}
	
	
	T6MgrAhr& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
	
	T6MgrAhr& setCty_prov_city_mgrid(int acty_prov_city_mgrid){
		cty_prov_city_mgrid = acty_prov_city_mgrid;
		mapSQLTokens["cty_prov_city_mgrid"] = to_string(cty_prov_city_mgrid);
		return *this;
	}
	int getCty_prov_city_mgrid() {
		return cty_prov_city_mgrid;
	}
	
	
	T6MgrAhr& setAssc_mgrid(int aassc_mgrid){
		assc_mgrid = aassc_mgrid;
		mapSQLTokens["assc_mgrid"] = to_string(assc_mgrid);
		return *this;
	}
	int getAssc_mgrid() {
		return assc_mgrid;
	}
	
	
	T6MgrAhr& setEmail(string aemail){
		email = aemail;
		
		mapSQLTokens["email"] = "'"+email+"'";
		return *this;
	}
	string getEmail() {
		return email;
	}
	
	
	T6MgrAhr& setRmrk(string armrk){
		rmrk = armrk;
		
		mapSQLTokens["rmrk"] = "'"+rmrk+"'";
		return *this;
	}
	string getRmrk() {
		return rmrk;
	}
	
	
	T6MgrAhr& setTypeleve(string atypeleve){
		typeleve = atypeleve;
		
		mapSQLTokens["typeleve"] = "'"+typeleve+"'";
		return *this;
	}
	string getTypeleve() {
		return typeleve;
	}
	
	
	T6MgrAhr& setProvince(string aprovince){
		province = aprovince;
		
		mapSQLTokens["province"] = "'"+province+"'";
		return *this;
	}
	string getProvince() {
		return province;
	}
	
	
	T6MgrAhr& setCity(string acity){
		city = acity;
		
		mapSQLTokens["city"] = "'"+city+"'";
		return *this;
	}
	string getCity() {
		return city;
	}
	
	
	T6MgrAhr& setInstitute(string ainstitute){
		institute = ainstitute;
		
		mapSQLTokens["institute"] = "'"+institute+"'";
		return *this;
	}
	string getInstitute() {
		return institute;
	}
	
};
const string T6MgrAhr::TableName = "t6mgrahr";
