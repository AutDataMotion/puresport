package puresport.mvc.t1usrbsc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.ComOutMdl;
import puresport.applicat.ExcelParseTool;
import puresport.applicat.MdlExcelRow;
import puresport.constant.EnumStatus;
import puresport.mvc.comm.ResTips;
import puresport.mvc.t6mgrahr.T6MgrSession;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/t1usrBsc
 * /jf/puresport/t1usrBsc/save
 * /jf/puresport/t1usrBsc/edit
 * /jf/puresport/t1usrBsc/update
 * /jf/puresport/t1usrBsc/view
 * /jf/puresport/t1usrBsc/delete
 * /puresport/t1usrBsc/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t1usrBsc")
public class T1usrBscController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscController.class);

	public static final String pthc = "/jf/puresport/t1usrBsc/";
	public static final String pthv = "/f/";


	@Clear
	public void index() {
		setAttr("username", "test");
		renderWithPath(pthv + "admin.html");
	}

	@Clear
	public void getData() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJsonForTable(T1usrBscService.service.selectByPage(mgrSession, getParamWithServerPage()));
	}
	@Clear
	public void addSporter(){
		T1usrBsc mdl = getModel(T1usrBsc.class);
	    // 检查手机号的用户是否存在
		if (T1usrBscService.service.isExist(mdl)) {
			ResTips errorTips = ResTips.getFailRes()
					.addErroFiled(T1usrBsc.column_mblph_no, "该手机号已存在");
			renderJson(errorTips);
			return ;
		}
		// 不存在则添加
		// 用户名设置为手机号
		mdl.set(T1usrBsc.column_usr_nm, mdl.get(T1usrBsc.column_mblph_no));
		mdl.saveGenIntId();
		renderJson(ResTips.getSuccRes());
	}

	@Clear
	public void editSporter(){
		T1usrBsc mdl = getModel(T1usrBsc.class);
	    // 检查手机号的用户是否存在
		if (!T1usrBscService.service.isExist(mdl)) {
			// 不存在则不可以更新
			ResTips errorTips =ResTips.getFailRes()
					.addErroFiled(T1usrBsc.column_mblph_no, "该手机号不存在");
			renderJson(errorTips);
			return ;
		}
		// 用户名设置为手机号
		mdl.set(T1usrBsc.column_usr_nm, mdl.get(T1usrBsc.column_mblph_no));
		mdl.update();
		renderJson(ResTips.getSuccRes());
	}
	
	@Clear
	public void delSporter(){
		Map<String, String[]> paramMap = getParaMap();
		paramMap.entrySet().stream().forEach(e->{
			System.out.println(e.getKey()+"--"+e.getValue());
		});
	}
	
	@Clear
	public void getDataScore(){
		// 成绩统计
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJson(T1usrBscService.service.selectScoreByPage(mgrSession, getParamComm()));
	}
	
	@Clear
	public void getDataPrjStatis(){
		// 项目合格率统计
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJson(T1usrBscService.service.selectPassedPercent(mgrSession, getParamComm()));
	}
	
	@Clear
	public void getDataExamQues(){
		// 试题错误率统计
		renderJson(T1usrBscService.service.selectExamQuestion(getParamComm()));
	}
	
	@Clear
	public void inload() {
		// 获取上传的excel文件
		String path = "files/upload/".trim();
		String base = this.getRequest().getContextPath().trim();// 应用路径
		UploadFile picFile = getFile("fileexcel");// 得到 文件对象
		String fileName = picFile.getFileName();
		String mimeType = picFile.getContentType();// 得到 上传文件的MIME类型:audio/mpeg
		System.out.println(mimeType);
		// if(!"image/gif".equals(mimeType) && !"image/jpeg".equals(mimeType)
		// &&!"image/x-png".equals(mimeType) &&!"image/png".equals(mimeType)){
		// setAttr("message","上传文件类型错误！！！");
		// renderJson();
		// return ;
		// }
		// String realpath = getSession().getServletContext().getRealPath(path);
		// String lastName = fileName.substring(fileName.lastIndexOf(".")); //
		// 获取文件的后缀
		// String newName= "2018"+lastName;

		List<MdlExcelRow> table = null;
		try {
			table = ExcelParseTool.getWorkBookTable(picFile.getFile());
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 存入数据库
		ComOutMdl<List<MdlExcelRow>> outFailedMdl = new ComOutMdl<>();
		
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		
		if (T1usrBscService.service.insertFromExcel(mgrSession, table, outFailedMdl)) {
			renderText(String.valueOf(EnumStatus.Success.getId()));
		} else {
			log.error(outFailedMdl.get());
			renderJson(outFailedMdl.get());
		}
		return;
	}

	
	/**
	 * 列表
	 */
	@Clear
	public void login()
	{
		boolean flag = false;  
		boolean needImproveInfoOrNot = false;
        String msg = "";  
        String userType = "";
        JSONObject json = new JSONObject();  
        
        String crdt_no = getPara("account");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String password = getPara("pwd");  
        T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where crdt_no=?", crdt_no);//根据用户名查询数据库中的用户  
        if(item != null) {  
            if(password.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
                flag = true; 
                userType = item.getUsr_tp();
                getSession().setAttribute("usrid", item.getUsrid());//设置session，保存登录用户的昵称  
                getSession().setAttribute("crdt_no", item.getCrdt_no());//设置session，保存登录用户的昵称  
                getSession().setAttribute("pwd", item.getPswd());//设置session，保存登录用户的昵称  
                getSession().setAttribute("usr_tp", item.getUsr_tp());//设置session，保存登录用户的昵称
                if(userType.equals("运动员"))
                {
                	Object ss = item.getAdiv_cd();
//                	if(item.getAdiv_cd()!=null&&item.getSpt_prj()!=null)
                	if(item.getProvince()!=null&&item.getCity()!=null&&item.getSpt_prj()!=null)
                	{
                		needImproveInfoOrNot  =false;
                	}	
                	else {
                		needImproveInfoOrNot  =true;
                	}
                }	
                else {
                	if(item.getDepartment()!=null&&item.getPost()!=null)
                	{
                		needImproveInfoOrNot  =false;
                	}	
                	else {
                		needImproveInfoOrNot  =true;
                	}
                }
                json.put("needImproveInfoOrNot", needImproveInfoOrNot); 
            }  
            else {  
                msg = "密码不正确";  
            }  
        }  
        else {  
            msg = "帐号不存在";  
        }  
        json.put("flag", flag); 
        json.put("userType", userType); 
        json.put("msg", msg); 
//        json.put("url", getCxt()+"/jf/puresport/pagesController/selfcenter"); 
//        System.out.println("login----"+(String)getSession().getAttribute("RequestURL"));
        if(getSession().getAttribute("RequestURL")!=null)
        {
        	json.put("url", (String)getSession().getAttribute("RequestURL")); 
        }
        else {
        	json.put("url", getCxt()+"/jf/puresport/pagesController"); 
        }
        renderJson(json);  
	}
	@Clear
	public void ImproveUserInfo()
	{
		boolean flag = false;  
        String msg = "";  
//        String userType = "";
        JSONObject json = new JSONObject();  
        
        Long userID = Long.valueOf((String)getSession().getAttribute("usrid"));
        T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", userID);//根据用户名查询数据库中的用户  
        if(item!=null)
        {
//        	userType = getPara("userType");
            if(((String)item.getUsr_tp()).equals("运动员"))//运动员
            {

//            	String code = getPara("code");//获取表单数据，这里的参数就是页面表单中的name属性值  
            	String province = getPara("province");
            	String city = getPara("city");
                String competetion = getPara("competetion");
                String competetionitem = getPara("competetionitem");
//                item.setAdiv_cd(code);
//                item.setSpt_prj(competetionitem);
                int res = Db.update("update puresport.t1_usr_bsc set province=?,city=?,spt_prj=? where usrid=?",province,city,competetionitem,userID);
                if(res>0)
                {
                	flag = true; 
                }
            }
            else {//辅助人员
            	String company = getPara("company");//获取表单数据，这里的参数就是页面表单中的name属性值  
                String position = getPara("position");
                int res = Db.update("update puresport.t1_usr_bsc set department=?,post=? where usrid=?",company,position,userID);
                if(res>0)
                {
                	flag = true; 
                }
            }
        }
        else {  
            msg = "更新失败";  
        }  
        json.put("flag", flag); 
        json.put("msg", msg); 
        json.put("url", getCxt()+"/jf/puresport/pagesController/selfcenter"); 
        renderJson(json);  
        
	}
	
	/**
	 * 保存
	 */
	@Before(T1usrBscValidator.class)
	public void save() {
		T1usrBsc t1usrBsc = getModel(T1usrBsc.class);
		//other set 
		
		//t1usrBsc.save();		//guiid
		t1usrBsc.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T1usrBsc t1usrBsc = T1usrBsc.dao.findById(getPara());	//guuid
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t1usrBsc", t1usrBsc);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T1usrBscValidator.class)
	public void update() {
		getModel(T1usrBsc.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T1usrBsc t1usrBsc = T1usrBsc.dao.findById(getPara());	//guuid
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t1usrBsc", t1usrBsc);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T1usrBscService.service.delete("t1_usr_bsc", getPara() == null ? ids : getPara());	//guuid
		T1usrBscService.service.deleteById("t1_usr_bsc", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
