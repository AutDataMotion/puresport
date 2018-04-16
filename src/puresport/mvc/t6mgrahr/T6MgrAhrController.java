package puresport.mvc.t6mgrahr;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;

import puresport.constant.ConstantInitMy;
import puresport.mvc.t1usrbsc.T1usrBsc;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/t6MgrAhr
 * /jf/puresport/t6MgrAhr/save
 * /jf/puresport/t6MgrAhr/edit
 * /jf/puresport/t6MgrAhr/update
 * /jf/puresport/t6MgrAhr/view
 * /jf/puresport/t6MgrAhr/delete
 * /puresport/t6MgrAhr/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t6MgrAhr")
public class T6MgrAhrController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrController.class);

	public static final String pthc = "/jf/puresport/t6MgrAhr/";
	public static final String pthv = "/puresport/t6MgrAhr/";

	/**
	 * 列表
	 */
	@Clear
	public void login()
	{
		boolean flag = false;  
        String msg = "";  
        JSONObject json = new JSONObject();  
        
        String mblph_no = getPara("account");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String password = getPara("pwd");  
        T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where mblph_no=?", mblph_no);//根据用户名查询数据库中的用户  
        if(item != null) {  
            if(password.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
                flag = true;  
                getSession().setAttribute("usrid", item.getUsrid());//设置session，保存登录用户的昵称  
            }  
            else {  
                msg = "密码不正确";  
            }  
        }  
        else {  
            msg = "帐号不存在";  
        }  
        json.put("flag", flag);  
        json.put("msg", msg); 
        json.put("url", getCxt()+"/jf/puresport/pagesController/admin"); 
        renderJson(json);  
	}
	@Clear
	public void ImproveAdminInfo()
	{
		boolean flag = false;  
        String msg = "";  
//        String userType = "";
        JSONObject json = new JSONObject();  
        
        Long userID = (Long) getSession().getAttribute("usrid");
        T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);//根据用户名查询数据库中的用户  
        if(item!=null)
        {
//        	
//        		String code = getPara("code");//获取表单数据，这里的参数就是页面表单中的name属性值  
                String company = getPara("company");
                String position = getPara("position");
//                item.setAdiv_cd(code);
//                item.setSpt_prj(competetionitem);
                int res = Db.update("update puresport.t6_mgr_ahr set wrk_unit=?,post=? where usrid=?",company,position,userID);
                if(res>0)
                {
                	flag = true; 
                }
        }
        else {  
            msg = "更新失败";  
        }  
        json.put("flag", flag); 
        json.put("msg", msg); 
        json.put("url", getCxt()+"/jf/puresport/pagesController/admin"); 
        renderJson(json);  
        
	}
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T6MgrAhr.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T6MgrAhrValidator.class)
	public void save() {
		T6MgrAhr t6MgrAhr = getModel(T6MgrAhr.class);
		//other set 
		
		//t6MgrAhr.save();		//guiid
		t6MgrAhr.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T6MgrAhr t6MgrAhr = T6MgrAhr.dao.findById(getPara());	//guuid
		T6MgrAhr t6MgrAhr = T6MgrAhrService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t6MgrAhr", t6MgrAhr);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T6MgrAhrValidator.class)
	public void update() {
		getModel(T6MgrAhr.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T6MgrAhr t6MgrAhr = T6MgrAhr.dao.findById(getPara());	//guuid
		T6MgrAhr t6MgrAhr = T6MgrAhrService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t6MgrAhr", t6MgrAhr);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T6MgrAhrService.service.delete("t6_mgr_ahr", getPara() == null ? ids : getPara());	//guuid
		T6MgrAhrService.service.deleteById("t6_mgr_ahr", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
