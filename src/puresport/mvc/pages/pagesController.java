package puresport.mvc.pages;

import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.ehcache.CacheKit;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.ComUtil;
import csuduc.platform.util.RegexUtils;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.lyf.EmailUtils;
import csuduc.platform.util.lyf.WebsiteSta;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.mvc.sport_item.Sport_Item;
import puresport.mvc.sport_item.Sport_ItemService;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t6mgrahr.T6MgrAhr;
import puresport.mvc.t7crcl.T7Crcl;
import puresport.mvc.t7crcl.T7CrclService;


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
//		setAttr("userCounts",getSession().getAttribute("userCounts"));
//		renderWithPath(pthv+"index.html");
		renderWithPath(pthv+"index.html");
		
		
	}
	
	public void zhunru_index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		//getSession().setAttribute("userid", "100");
//		setSessionAttr("userID","10");

		renderWithPath(pthv+"zhunru_index.html");
		
	}

	public void zhunru_index_common() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		//getSession().setAttribute("userid", "100");
//		setSessionAttr("userID","10");

		renderWithPath(pthv+"zhunru_index_common.html");
		
	}
	@Clear
	public void tuozhan_index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"tuozhan_index.html");
	}
	@Clear
	public void jiangzuo_index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		//反兴奋剂教育准入讲座
		List<T7Crcl> mains = T7CrclService.service.SelectBycrcl_attr("1");
//		List<T7Crcl> anlis = T7CrclService.service.SelectBycrcl_attr("2");
//		List<T7Crcl> liuchengs = T7CrclService.service.SelectBycrcl_attr("3");
		setAttr("mains",mains);
//		setAttr("anlis",anlis);
//		setAttr("liuchengs",liuchengs);
		renderWithPath(pthv+"jiangzuo_index.html");
	}
	@Clear
	public void resource_index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"resource_index.html");
	}
	@Clear
	public void resource_workguide() {//资源-工作指南类
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"resource/resource-workguide.html");
	}
	@Clear
	public void resource_handbook() {//资源-手册折页类
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"resource/resource-handbook.html");
	}
	@Clear
	public void resource_poster() {//资源-海报展板类
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"resource/resource-poster.html");
	}
	@Clear
	public void resource_video() {//资源-视频类
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"resource/resource-video.html");
	}
	@Clear
	public void resource_reference() {//资源-参考资料类
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"resource/resource-reference.html");
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
	public void tips() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"tips.html");
	}
	@Clear
	public void jiangzuo_videoplay() {
		String videoid = getPara("video_id");
		//String videotitle = getPara("videotitle");
		T7Crcl t7 = null;
		if(StringUtils.isNotBlank(videoid)) {
			t7 = T7CrclService.service.SelectByVideoID(videoid);
		} else {
			log.error("课程编号为空，查询课程信息失败！");
		}
		if(null == t7) {
			log.debug("根据课程id查询到课程信息为空，课程id=" + videoid);
		}
		
		setAttr("videoid",videoid);
		if(videoid.equals("258767799"))
		{
			setAttr("videotitle",t7.getCrcl_nm()+"(一)");
		}
		else if(videoid.equals("258768330"))
		{
			setAttr("videotitle",t7.getCrcl_nm()+"(二)");
		}
		else {
			setAttr("videotitle",t7.getCrcl_nm());
		}
		
		renderWithPath(pthv+"video/videoPlay.html");
	}
	@Clear
	public void login() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		List<Sport_Item> sport_items_of_institute = Sport_ItemService.service.SelectByItemId(1);
//		
		List<Sport_Item> sport_items_of_shengyunhui = Sport_ItemService.service.SelectByItemId(2);
		setAttr("sport_items_of_institute",sport_items_of_institute);
		setAttr("sport_items_of_shengyunhui",sport_items_of_shengyunhui);
		renderWithPath(pthv+"login.html");
	}
	@Clear
	public void loginout() {
		// 清除session
		Enumeration<String> em = getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			getSession().removeAttribute(em.nextElement().toString());
		}
		List<Sport_Item> sport_items_of_institute = Sport_ItemService.service.SelectByItemId(1);
//		
		List<Sport_Item> sport_items_of_shengyunhui = Sport_ItemService.service.SelectByItemId(2);
		setAttr("sport_items_of_institute",sport_items_of_institute);
		setAttr("sport_items_of_shengyunhui",sport_items_of_shengyunhui);
		renderWithPath(pthv+"login.html");
	}
	
	@Clear
	public void regist() {
		
		List<Sport_Item> sport_items_of_shengyunhui = Sport_ItemService.service.SelectByItemId(2);
		setAttr("sport_items_of_shengyunhui",sport_items_of_shengyunhui);
		renderWithPath(pthv+"regist.html");
	}
	
//	@Clear
//	@Before(loginInterceptorlyf.class)  
	public void selfcenter() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		List<Sport_Item> sport_items_of_institute = Sport_ItemService.service.SelectByItemId(1);
//		
		List<Sport_Item> sport_items_of_shengyunhui = Sport_ItemService.service.SelectByItemId(2);
		setAttr("sport_items_of_institute",sport_items_of_institute);
		setAttr("sport_items_of_shengyunhui",sport_items_of_shengyunhui);
		
		Long userID = getSessionAttrForStr("usrid", Long.class);
		String crdt_no = getSessionAttr("crdt_no");
		String pwd = getSessionAttr("pwd");
		T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=? limit 1", userID);//根据用户名查询数据库中的用户  
        if(item!=null)
        {
        	if(crdt_no.equals((String)item.getCrdt_no())&&pwd.equals((String)item.getPswd()))
        	{
        		setAttr("username", (String)item.getNm());
        		renderWithPath(pthv+"selfcenter.html");
        	}
        	else {
        		renderWithPath(pthv+"login.html");
        	}
        	
        }
        else {
    		renderWithPath(pthv+"login.html");
    	}
		
	}
//	@Clear
//	@Before(loginInterceptorlyf.class)  
	public void admin() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		Long userID = getSessionAttrForStr("usrid", Long.class);
		String crdt_no = getSessionAttr("crdt_no");
		String pwd = getSessionAttr("pwd");
		T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);//根据用户名查询数据库中的用户  
		if(item!=null)
        {
        	if(crdt_no.equals((String)item.getCrdt_no())&&pwd.equals((String)item.getPswd()))
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
        
        String account = getPara("account");
        String userOradmin = getPara("userOradmin");
        if (ComUtil.haveEmpty(account, userOradmin)) {
			renderText("illegal");
			return;
		}
        
        Integer accountType = RegexUtils.checkPhoneOrEmail(account);
		if (accountType == 0) {
			renderText("illegal");
			return;
		}
		
		String accountColumnStr = accountType == 1?"mblph_no":"email";
       
        if(userOradmin.equals("01"))//运动员及辅助人员
        {
        	T1usrBsc item = T1usrBsc.dao.findFirst(String.format("select * from t1_usr_bsc where %s=?",accountColumnStr), account);
        	flag = item!=null;
        }
        else {
        	T6MgrAhr item = T6MgrAhr.dao.findFirst(String.format("select * from t6_mgr_ahr where %s=?", accountColumnStr), account);
        	flag = item!=null;
        }
        
        if(flag) {  
        	String confirmCode = EmailUtils.getRadSix();
        	log.info("ForgetPwd_getConfirmcode code:" + confirmCode);
        	String subject = "反兴奋剂在线教育平台";
        	String confirmMsg = "尊敬的用户您好，您找回密码的验证码是:"+confirmCode;
        	try {
        		// todo 发送手机验证码
				flag = accountType == 1? true : EmailUtils.sendTextMail(account,subject, confirmMsg);
				if(flag)
				{
					setSessionAttr("emailConfirmCode", confirmCode);//设置session，保存登录用户的昵称
				}
				else {
					msg = "发送失败";  
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("--------发送验证码失败！！");
				e.printStackTrace();
				msg = "发送失败";  
			}
        }  
        else {  
            msg = "帐号不存在";  
        }  
        json.put("flag", flag); 
        
        json.put("msg", msg); 
        
        renderJson(json); 
	}
	@Clear
	public void ForgetPwd_setPwdByEmail(){
		boolean flag = false;  
        String msg = "";  
        JSONObject json = new JSONObject();  
        
        T1usrBsc item = null;
        
        String account = getPara("account");
        String userOradmin = getPara("userOradmin");
        String confrimcode = getPara("confrimcode");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String newPwd = getPara("newPwd");

        if (ComUtil.haveEmpty(account, userOradmin, confrimcode, newPwd)) {
			renderText("illegal");
			return;
		}
        
        Integer accountType = RegexUtils.checkPhoneOrEmail(account);
		if (accountType == 0) {
			renderText("illegal");
			return;
		}
		
		String accountColumnStr = accountType == 1?"mblph_no":"email";
		
        try {
			String encryptpassword = DESUtil.encrypt(newPwd, ConstantInitMy.SPKEY);
			
			if(confrimcode.equals((String)getSessionAttr("emailConfirmCode")))
	        {
				int res = -1;
	        	if(userOradmin.equals("01"))//运动员及辅助人员
	            {
	        		res = ConfMain.db().update(String.format("update t1_usr_bsc set pswd=? where %s=?",accountColumnStr),encryptpassword,account); 
	            }
	            else {
	            	res = ConfMain.db().update(String.format("update t6_mgr_ahr set pswd=? where %s=?",accountColumnStr),encryptpassword,account);
	            }
	        	if(res>0)
                {
                	flag = true; 
                	removeSessionAttr("emailConfirmCode");
                }else {
                	msg = "修改失败，请稍后重试";  
                }
	        }
	        else {
	        	msg = "验证码输入错误";  
	        }
	        json.put("flag", flag);
	        json.put("msg", msg); 
	        json.put("url", getCxt()+"/jf/puresport/pagesController/login"); 
	        renderJson(json); 
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	@Clear
	public void ResetPwd()
	{
		boolean flag = false;  
        String msg = "";  
        
        JSONObject json = new JSONObject();  
        
        String beforeEncrypt_oldPwd = getPara("oldPwd");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String beforeEncrypt_newPwd = getPara("newPwd");
        
        
		try {
			String oldPwd = DESUtil.encrypt(beforeEncrypt_oldPwd, ConstantInitMy.SPKEY);
			String newPwd = DESUtil.encrypt(beforeEncrypt_newPwd, ConstantInitMy.SPKEY);
			
			String userOradmin = getPara("userOradmin"); 
	        
			Long userID = getSessionAttrForStr("usrid", Long.class);
	        if(userOradmin.equals("01"))
	        {
	        	
	        	T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", userID);
	        	if(item != null) {  
	                if(oldPwd.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
	                    //flag = true; 
	                    int res = ConfMain.db().update("update t1_usr_bsc set pswd=? where usrid=?",newPwd,userID);
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
	                	int res = ConfMain.db().update("update t6_mgr_ahr set pswd=? where usrid=?",newPwd,userID);
//	                    flag = true; 
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
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
//        String oldPwd = getPara("oldPwd");//获取表单数据，这里的参数就是页面表单中的name属性值  
//        String newPwd = getPara("newPwd");
//        
        
	}
	@Override
	protected void setViewPath() {
		// TODO Auto-generated method stub
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
//	@Clear
//	public void getSportItems()
//	{
//		List<Sport_Item> sport_items_of_institute = Sport_ItemService.service.SelectByItemId(1);
//		
//		List<Sport_Item> sport_items_of_shengyunhui = Sport_ItemService.service.SelectByItemId(2);
//	}
}
