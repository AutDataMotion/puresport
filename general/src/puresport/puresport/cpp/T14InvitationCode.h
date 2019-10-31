/**  
 * Date:	 - -  : :   
 * project:	   
 * fileName:	T14InvitationCode.h  
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

class T14InvitationCode :public AbsDAL {

public:
	T14InvitationCode():AbsDAL(&TableName){}
	~T14InvitationCode(){}
	
private:
	static const string TableName;
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	long id;
	/**
	 * 字段描述：邀请码 
	 * 字段类型：varchar  长度：null
	 */
	string invitation_code;
	/**
	 * 字段描述：已验证用户列表 
	 * 字段类型：text  长度：null
	 */
	string invited_user_list;
	/**
	 * 字段描述：赛事类型 
	 * 字段类型：char  长度：null
	 */
	string type;
public:
	
	
	T14InvitationCode& setId(long aid){
		id = aid;
		mapSQLTokens["id"] = to_string(id);
		return *this;
	}
	long getId() {
		return id;
	}
	
	
	T14InvitationCode& setInvitation_code(string ainvitation_code){
		invitation_code = ainvitation_code;
		
		mapSQLTokens["invitation_code"] = "'"+invitation_code+"'";
		return *this;
	}
	string getInvitation_code() {
		return invitation_code;
	}
	
	
	T14InvitationCode& setInvited_user_list(string ainvited_user_list){
		invited_user_list = ainvited_user_list;
		
		mapSQLTokens["invited_user_list"] = "'"+invited_user_list+"'";
		return *this;
	}
	string getInvited_user_list() {
		return invited_user_list;
	}
	
	
	T14InvitationCode& setType(string atype){
		type = atype;
		
		mapSQLTokens["type"] = "'"+type+"'";
		return *this;
	}
	string getType() {
		return type;
	}
	
};
const string T14InvitationCode::TableName = "t14invitationcode";
