/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T1usrBsc.h  
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

class T1usrBsc :public AbsDAL {

public:
	T1usrBsc():AbsDAL(&TableName){}
	~T1usrBsc(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：用户id 
	 * 字段类型：bigint  长度：null
	 */
	long usrid;
	/**
	 * 字段描述：用户类型 
	 * 字段类型：varchar  长度：null
	 */
	string usr_tp;
	/**
	 * 字段描述：用户名 
	 * 字段类型：varchar  长度：null
	 */
	string usr_nm;
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
	 * 字段描述：运动项目 
	 * 字段类型：varchar  长度：null
	 */
	string spt_prj;
	/**
	 * 字段描述：证件号 
	 * 字段类型：varchar  长度：null
	 */
	string crdt_no;
	/**
	 * 字段描述：性别 
	 * 字段类型：varchar  长度：null
	 */
	string gnd;
	/**
	 * 字段描述：密码 
	 * 字段类型：varchar  长度：null
	 */
	string pswd;
	/**
	 * 字段描述：出生日期 
	 * 字段类型：char  长度：null
	 */
	string brth_dt;
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：char  长度：null
	 */
	string adiv_cd;
	/**
	 * 字段描述：协会id 
	 * 字段类型：char  长度：null
	 */
	string asscid;
	/**
	 * 字段描述：手机号 
	 * 字段类型：varchar  长度：null
	 */
	string mblph_no;
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	string tms;
	/**
	 * 字段描述：国家省市管理员id 
	 * 字段类型：bigint  长度：null
	 */
	long cty_prov_city_mgrid;
	/**
	 * 字段描述：备注 
	 * 字段类型：varchar  长度：null
	 */
	string rmrk;
	/**
	 * 字段描述：协会管理员用id 
	 * 字段类型：int  长度：null
	 */
	int assc_mgrid;
	/**
	 * 字段描述：邮箱 
	 * 字段类型：varchar  长度：null
	 */
	string email;
	/**
	 * 字段描述：血型 
	 * 字段类型：char  长度：null
	 */
	string bloodtp;
	/**
	 * 字段描述：民族 
	 * 字段类型：varchar  长度：null
	 */
	string ethnct;
	/**
	 * 字段描述：备注 
	 * 字段类型：varchar  长度：null
	 */
	string remark;
	/**
	 * 字段描述：类型级别 
	 * 字段类型：varchar  长度：null
	 */
	string typelevel;
	/**
	 * 字段描述：省份名称 
	 * 字段类型：varchar  长度：null
	 */
	string province;
	/**
	 * 字段描述：城市名称 
	 * 字段类型：varchar  长度：null
	 */
	string city;
	/**
	 * 字段描述：协会名称 
	 * 字段类型：varchar  长度：null
	 */
	string institute;
	/**
	 * 字段描述：工作单位 
	 * 字段类型：varchar  长度：null
	 */
	string department;
	/**
	 * 字段描述：职务 
	 * 字段类型：varchar  长度：null
	 */
	string post;
	/**
	 * 字段描述：省级别 0不可见 1可见 
	 * 字段类型：int  长度：null
	 */
	int levelprovince;
	/**
	 * 字段描述：市级别0不可见1可见 
	 * 字段类型：int  长度：null
	 */
	int levelcity;
	/**
	 * 字段描述：协会级别0可见1不可见 
	 * 字段类型：int  长度：null
	 */
	int levelinstitute;
public:
	
	
	T1usrBsc& setUsrid(long ausrid){
		usrid = ausrid;
		mapSQLTokens["usrid"] = to_string(usrid);
		return *this;
	}
	long getUsrid() {
		return usrid;
	}
	
	
	T1usrBsc& setUsr_tp(string ausr_tp){
		usr_tp = ausr_tp;
		
		mapSQLTokens["usr_tp"] = "'"+usr_tp+"'";
		return *this;
	}
	string getUsr_tp() {
		return usr_tp;
	}
	
	
	T1usrBsc& setUsr_nm(string ausr_nm){
		usr_nm = ausr_nm;
		
		mapSQLTokens["usr_nm"] = "'"+usr_nm+"'";
		return *this;
	}
	string getUsr_nm() {
		return usr_nm;
	}
	
	
	T1usrBsc& setNm(string anm){
		nm = anm;
		
		mapSQLTokens["nm"] = "'"+nm+"'";
		return *this;
	}
	string getNm() {
		return nm;
	}
	
	
	T1usrBsc& setCrdt_tp(string acrdt_tp){
		crdt_tp = acrdt_tp;
		
		mapSQLTokens["crdt_tp"] = "'"+crdt_tp+"'";
		return *this;
	}
	string getCrdt_tp() {
		return crdt_tp;
	}
	
	
	T1usrBsc& setSpt_prj(string aspt_prj){
		spt_prj = aspt_prj;
		
		mapSQLTokens["spt_prj"] = "'"+spt_prj+"'";
		return *this;
	}
	string getSpt_prj() {
		return spt_prj;
	}
	
	
	T1usrBsc& setCrdt_no(string acrdt_no){
		crdt_no = acrdt_no;
		
		mapSQLTokens["crdt_no"] = "'"+crdt_no+"'";
		return *this;
	}
	string getCrdt_no() {
		return crdt_no;
	}
	
	
	T1usrBsc& setGnd(string agnd){
		gnd = agnd;
		
		mapSQLTokens["gnd"] = "'"+gnd+"'";
		return *this;
	}
	string getGnd() {
		return gnd;
	}
	
	
	T1usrBsc& setPswd(string apswd){
		pswd = apswd;
		
		mapSQLTokens["pswd"] = "'"+pswd+"'";
		return *this;
	}
	string getPswd() {
		return pswd;
	}
	
	
	T1usrBsc& setBrth_dt(string abrth_dt){
		brth_dt = abrth_dt;
		
		mapSQLTokens["brth_dt"] = "'"+brth_dt+"'";
		return *this;
	}
	string getBrth_dt() {
		return brth_dt;
	}
	
	
	T1usrBsc& setAdiv_cd(string aadiv_cd){
		adiv_cd = aadiv_cd;
		
		mapSQLTokens["adiv_cd"] = "'"+adiv_cd+"'";
		return *this;
	}
	string getAdiv_cd() {
		return adiv_cd;
	}
	
	
	T1usrBsc& setAsscid(string aasscid){
		asscid = aasscid;
		
		mapSQLTokens["asscid"] = "'"+asscid+"'";
		return *this;
	}
	string getAsscid() {
		return asscid;
	}
	
	
	T1usrBsc& setMblph_no(string amblph_no){
		mblph_no = amblph_no;
		
		mapSQLTokens["mblph_no"] = "'"+mblph_no+"'";
		return *this;
	}
	string getMblph_no() {
		return mblph_no;
	}
	
	
	T1usrBsc& setTms(string atms){
		tms = atms;
		
		mapSQLTokens["tms"] = "'"+tms+"'";
		return *this;
	}
	string getTms() {
		return tms;
	}
	
	
	T1usrBsc& setCty_prov_city_mgrid(long acty_prov_city_mgrid){
		cty_prov_city_mgrid = acty_prov_city_mgrid;
		mapSQLTokens["cty_prov_city_mgrid"] = to_string(cty_prov_city_mgrid);
		return *this;
	}
	long getCty_prov_city_mgrid() {
		return cty_prov_city_mgrid;
	}
	
	
	T1usrBsc& setRmrk(string armrk){
		rmrk = armrk;
		
		mapSQLTokens["rmrk"] = "'"+rmrk+"'";
		return *this;
	}
	string getRmrk() {
		return rmrk;
	}
	
	
	T1usrBsc& setAssc_mgrid(int aassc_mgrid){
		assc_mgrid = aassc_mgrid;
		mapSQLTokens["assc_mgrid"] = to_string(assc_mgrid);
		return *this;
	}
	int getAssc_mgrid() {
		return assc_mgrid;
	}
	
	
	T1usrBsc& setEmail(string aemail){
		email = aemail;
		
		mapSQLTokens["email"] = "'"+email+"'";
		return *this;
	}
	string getEmail() {
		return email;
	}
	
	
	T1usrBsc& setBloodtp(string abloodtp){
		bloodtp = abloodtp;
		
		mapSQLTokens["bloodtp"] = "'"+bloodtp+"'";
		return *this;
	}
	string getBloodtp() {
		return bloodtp;
	}
	
	
	T1usrBsc& setEthnct(string aethnct){
		ethnct = aethnct;
		
		mapSQLTokens["ethnct"] = "'"+ethnct+"'";
		return *this;
	}
	string getEthnct() {
		return ethnct;
	}
	
	
	T1usrBsc& setRemark(string aremark){
		remark = aremark;
		
		mapSQLTokens["remark"] = "'"+remark+"'";
		return *this;
	}
	string getRemark() {
		return remark;
	}
	
	
	T1usrBsc& setTypelevel(string atypelevel){
		typelevel = atypelevel;
		
		mapSQLTokens["typelevel"] = "'"+typelevel+"'";
		return *this;
	}
	string getTypelevel() {
		return typelevel;
	}
	
	
	T1usrBsc& setProvince(string aprovince){
		province = aprovince;
		
		mapSQLTokens["province"] = "'"+province+"'";
		return *this;
	}
	string getProvince() {
		return province;
	}
	
	
	T1usrBsc& setCity(string acity){
		city = acity;
		
		mapSQLTokens["city"] = "'"+city+"'";
		return *this;
	}
	string getCity() {
		return city;
	}
	
	
	T1usrBsc& setInstitute(string ainstitute){
		institute = ainstitute;
		
		mapSQLTokens["institute"] = "'"+institute+"'";
		return *this;
	}
	string getInstitute() {
		return institute;
	}
	
	
	T1usrBsc& setDepartment(string adepartment){
		department = adepartment;
		
		mapSQLTokens["department"] = "'"+department+"'";
		return *this;
	}
	string getDepartment() {
		return department;
	}
	
	
	T1usrBsc& setPost(string apost){
		post = apost;
		
		mapSQLTokens["post"] = "'"+post+"'";
		return *this;
	}
	string getPost() {
		return post;
	}
	
	
	T1usrBsc& setLevelprovince(int alevelprovince){
		levelprovince = alevelprovince;
		mapSQLTokens["levelprovince"] = to_string(levelprovince);
		return *this;
	}
	int getLevelprovince() {
		return levelprovince;
	}
	
	
	T1usrBsc& setLevelcity(int alevelcity){
		levelcity = alevelcity;
		mapSQLTokens["levelcity"] = to_string(levelcity);
		return *this;
	}
	int getLevelcity() {
		return levelcity;
	}
	
	
	T1usrBsc& setLevelinstitute(int alevelinstitute){
		levelinstitute = alevelinstitute;
		mapSQLTokens["levelinstitute"] = to_string(levelinstitute);
		return *this;
	}
	int getLevelinstitute() {
		return levelinstitute;
	}
	
};
const string T1usrBsc::TableName = "t1usrbsc";
