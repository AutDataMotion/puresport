package puresport.mvc.pages;

import java.util.Enumeration;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.ehcache.CacheKit;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.lyf.EmailUtils;
import csuduc.platform.util.lyf.WebsiteSta;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t6mgrahr.T6MgrAhr;


public class pagesController extends BaseController {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(pagesController.class);

	public static final String pthc = "/jf/puresport/pagesController/";
	public static final String pthv = "/f/";
	@Clear
	public void index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		//getSession().setAttribute("userid", "100");
//		setSessionAttr("userID","10");
		
//		long count = WebsiteSta.countPeople();
//		log.debug(count);
		renderWithPath(pthv+"index.html");
		
	}
	@Clear
	public void zhunru_index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		//getSession().setAttribute("userid", "100");
//		setSessionAttr("userID","10");
		
		renderWithPath(pthv+"zhunru_index.html");
		
	}
//	@Clear
	public void zhunru_study() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"zhunru_study.html");
	}
//	@Clear
//	@Before(loginInterceptorlyf.class)  
//	public void zhunru_dotest() {
//		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
//		//renderWithPath(pthv+"list.html");
//		renderWithPath(pthv+"zhunru_dotest.html");
//	}
	@Clear
	public void about() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"about.html");
	}
	@Clear
	public void login() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		
		renderWithPath(pthv+"login.html");
	}
	@Clear
	public void loginout() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		// 清除session
		Enumeration<String> em = getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			getSession().removeAttribute(em.nextElement().toString());
		}

		renderWithPath(pthv+"login.html");
	}
//	@Clear
//	@Before(loginInterceptorlyf.class)  
	public void selfcenter() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		Long userID = Long.valueOf((String)getSession().getAttribute("usrid"));
		String phoneNum = (String)getSession().getAttribute("phoneNum");
		String pwd = (String)getSession().getAttribute("pwd");
		T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", userID);//根据用户名查询数据库中的用户  
        if(item!=null)
        {
        	if(phoneNum.equals((String)item.getMblph_no())&&pwd.equals((String)item.getPswd()))
        	{
        		setAttr("username", (String)item.getNm());
        		renderWithPath(pthv+"selfcenter.html");
        	}
        	else {
        		renderWithPath(pthv+"login.html");
        	}
        	
        }
		
	}
//	@Clear
//	@Before(loginInterceptorlyf.class)  
	public void admin() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		Long userID = Long.valueOf((String)getSession().getAttribute("usrid"));
		String phoneNum = (String)getSession().getAttribute("phoneNum");
		String pwd = (String)getSession().getAttribute("pwd");
		T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);//根据用户名查询数据库中的用户  
		if(item!=null)
        {
        	if(phoneNum.equals((String)item.getMblph_no())&&pwd.equals((String)item.getPswd()))
        	{
        		setAttr("username", (String)item.getNm());
        		renderWithPath(pthv+"admin.html");
        	}
        	else {
        		renderWithPath(pthv+"login.html");
        	}
        	
        }
	}
	
	@Clear
	public void ForgetPwd_getConfirmcodeByEmail() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		boolean flag = false;  
        String msg = "";  
        JSONObject json = new JSONObject();  
        
        //T1usrBsc item = null;
        
        String email = getPara("email");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String userOradmin = getPara("userOradmin");
        if(userOradmin.equals("01"))//运动员及辅助人员
        {
        	T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where email=?", email);//根据用户名查询数据库中的用户  
        	if(item!=null)
        	{
        		flag = true;
        	}
        }
        else {
        	T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where email=?", email);//根据用户名查询数据库中的用户  
        	if(item!=null)
        	{
        		flag = true;
        	}
        }
        
        if(flag) {  
        	//flag=true;
        	String confirmCode = EmailUtils.getRadSix();
        	String subject = "反兴奋剂在线教育平台";
        	String confirmMsg = "尊敬的用户您好，您找回密码的验证码是:"+confirmCode;
        	try {
				flag = EmailUtils.sendTextMail(email,subject, confirmMsg);
				if(flag)
				{
					getSession().setAttribute("emailConfirmCode", confirmCode);//设置session，保存登录用户的昵称
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("--------发送验证码到邮箱失败！！");
				e.printStackTrace();
			}
        }  
        else {  
            msg = "帐号不存在";  
        }  
        json.put("flag", flag); 
        
        json.put("msg", msg); 
        
        renderJson(json); 
		//renderWithPath(pthv+"admin.html");
	}
	@Clear
	public void ForgetPwd_setPwdByEmail(){
		boolean flag = false;  
        String msg = "";  
        JSONObject json = new JSONObject();  
        
        T1usrBsc item = null;
        
        String email = getPara("email");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String userOradmin = getPara("userOradmin");
        String confrimcode = getPara("confrimcode");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String newPwd = getPara("newPwd");
        if(confrimcode.equals((String)getSession().getAttribute("emailConfirmCode")))
        {
        	if(userOradmin.equals("01"))//运动员及辅助人员
            {
        		int res = Db.update("update puresport.t1_usr_bsc set pswd=? where email=?",newPwd,email);
                if(res>0)
                {
                	flag = true; 
                	getSession().removeAttribute("emailConfirmCode");
                }
            }
            else {
            	int res = Db.update("update puresport.t6_mgr_ahr set pswd=? where email=?",newPwd,email);
                if(res>0)
                {
                	flag = true; 
                	getSession().removeAttribute("emailConfirmCode");
                }
            }
        	
        }
        else {
        	msg = "验证码输入错误";  
        }
        json.put("flag", flag);
        json.put("msg", msg); 
        json.put("url", getCxt()+"/jf/puresport/pagesController/login"); 
        renderJson(json); 
        
	}
	@Clear
	public void ResetPwd()
	{
		boolean flag = false;  
        String msg = "";  
        
        JSONObject json = new JSONObject();  
        
        String oldPwd = getPara("oldPwd");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String newPwd = getPara("newPwd");
        String userOradmin = getPara("userOradmin"); 
        
        Long userID = Long.valueOf((String)getSession().getAttribute("usrid"));
        if(userOradmin.equals("01"))
        {
        	
        	T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", userID);
        	if(item != null) {  
                if(oldPwd.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
                    //flag = true; 
                    int res = Db.update("update puresport.t1_usr_bsc set pswd=? where usrid=?",newPwd,userID);
                    if(res>0)
                    {
                    	flag = true; 
                    	
                    }
                }  
                else {  
                    msg = "密码不正确";  
                }  
            }
        	else {
        		msg = "密码重置失败";  
        	}
        }
        else {
        	T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);
        	if(item != null) {  
                if(oldPwd.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
                	int res = Db.update("update puresport.t6_mgr_ahr set pswd=? where usrid=?",newPwd,userID);
//                    flag = true; 
                	if(res>0)
                    {
                    	flag = true; 
                    }
                }  
                else {  
                    msg = "密码不正确";  
                }  
            } 
        	else {
        		msg = "密码重置失败";  
        	}
        }
       
        json.put("flag", flag); 
        //json.put("userType", userType); 
        json.put("msg", msg); 
        //json.put("url", getCxt()+"/jf/puresport/pagesController/selfcenter"); 
        renderJson(json); 
	}
	@Override
	protected void setViewPath() {
		// TODO Auto-generated method stub
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
