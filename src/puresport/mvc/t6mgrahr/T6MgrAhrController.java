package puresport.mvc.t6mgrahr;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.JsonUtils;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.tuple.Tuple2;
import puresport.applicat.ExcelParseTool;
import puresport.applicat.MdlExcelRow;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumStatus;
import puresport.constant.EnumTypeLevel;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ResTips;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t6MgrAhr /jf/puresport/t6MgrAhr/save
 * /jf/puresport/t6MgrAhr/edit /jf/puresport/t6MgrAhr/update
 * /jf/puresport/t6MgrAhr/view /jf/puresport/t6MgrAhr/delete
 * /puresport/t6MgrAhr/add.html
 * 
 */
// @Controller(controllerKey = "/jf/puresport/t6MgrAhr")
public class T6MgrAhrController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrController.class);

	public static final String pthc = "/jf/puresport/t6MgrAhr/";
	public static final String pthv = "/f/";
	/**
	 * 列表
	 */
	@Clear
	public void login() {
		boolean flag = false;
		boolean needImproveInfoOrNot = false;
		String msg = "";
		JSONObject json = new JSONObject();
		boolean authCode = authCode();
		String crdt_no = getPara("account");// 获取表单数据，这里的参数就是页面表单中的name属性值
		String password = getPara("pwd");
		
		try {
			if(authCode)
            {
				String encryptpassword = DESUtil.encrypt(password, ConstantInitMy.SPKEY);
				
				T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where crdt_no=?", crdt_no);// 根据用户名查询数据库中的用户
				if (item != null) {
					if (encryptpassword.equals(item.getPswd())) {// 判断数据库中的密码与用户输入的密码是否一致
						flag = true;
						getSession().setAttribute("usrid", item.getUsrid());// 设置session，保存登录用户的昵称
						getSession().setAttribute("crdt_no", item.getCrdt_no());// 设置session，保存登录用户的昵称
						getSession().setAttribute("pwd", item.getPswd());// 设置session，保存登录用户的昵称
						getSession().setAttribute("usr_tp", item.getUsr_tp());//设置session，保存登录用户的昵称
						getSession().setAttribute("typeleve", item.getTypeleve());//设置session，保存登录用户的昵称
						T6MgrSession mgrSession = new T6MgrSession(item);
						setSessionAttr(T6MgrSession.KeyName, mgrSession);// 管理员session对象	
						
						/*if(item.getWrk_unit()!=null&&item.getPost()!=null&&item.getInstitute()!=null)
		            	{
		            		needImproveInfoOrNot  =false;
		            	}	
		            	else {
		            		needImproveInfoOrNot  =true;
		            		if(item.getWrk_unit()==null||item.getPost()==null)
		            		{
		            			json.put("needImproveWrk_unitAndPostOrNot", true); 
		            		}
		            		else {
		            			json.put("needImproveWrk_unitAndPostOrNot", false); 
		            		}
		            		if(item.getInstitute()==null)
		            		{
		            			json.put("needImproveInstituteOrNot", true); 
		            		}
		            		else {
		            			json.put("needImproveInstituteOrNot", false); 
		            		}
		            	}
		            	*/	
						if(item.getTypeleve().equals("中心协会级")&&item.getInstitute()==null)
						{
							needImproveInfoOrNot  =true;
							json.put("needImproveInstituteOrNot", true); 
						}
						else {
							needImproveInfoOrNot  = false;
						}
						json.put("needImproveInfoOrNot", needImproveInfoOrNot); 
						
					} else {
						msg = "密码不正确";
					}
				} else {
					msg = "帐号不存在";
				}
            }
			else {
				msg = "验证码错误";
			}
			
			json.put("flag", flag);
			json.put("msg", msg);
			json.put("url", getCxt() + "/jf/puresport/pagesController/admin");
			renderJson(json);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Clear
	public void ImproveAdminInfo() {
		boolean flag = false;
		String msg = "";
		// String userType = "";
		JSONObject json = new JSONObject();

		Long userID = Long.valueOf((String)getSession().getAttribute("usrid"));
		T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);// 根据用户名查询数据库中的用户
		if (item != null) {
			//
			// String code = getPara("code");//获取表单数据，这里的参数就是页面表单中的name属性值
//			String company = getPara("company");
//			String position = getPara("position");
			String xiehuiItemName = getPara("xiehuiItemName");
			// item.setAdiv_cd(code);
			// item.setSpt_prj(competetionitem);
			/*int res = Db.update("update puresport.t6_mgr_ahr set wrk_unit=?,post=? where usrid=?", company, position,
					userID);*/
			int res = Db.update("update puresport.t6_mgr_ahr set institute=? where usrid=?", xiehuiItemName,userID);
			if (res > 0) {
				flag = true;
				
				T6MgrAhr newitem = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);// 根据用户名查询数据库中的用户
				T6MgrSession mgrSession = new T6MgrSession(newitem);
				setSessionAttr(T6MgrSession.KeyName, mgrSession);// 管理员session对象	
			}
		} else {
			msg = "更新失败";
		}
		json.put("flag", flag);
		json.put("msg", msg);
		json.put("url", getCxt() + "/jf/puresport/pagesController/admin");
		renderJson(json);

	}

	@Clear
	public void index() {
		setAttr("username", "test");
		renderWithPath(pthv + "admin.html");
	}

	@Clear
	public void getData() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			 return ;
		}
		renderJsonForTable(T6MgrAhrService.service.selectByPage(mgrSession, getParamWithServerPage()));
	}
	
	public void deleteManager(){
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			 return ;
		}
		ParamComm paramComm = getParamCommDef();
		if (Objects.isNull(paramComm)) {
			log.error("deleteManager 参数解析失败");
			renderText("参数解析失败!");
			 return ;
		}
		renderText(T6MgrAhrService.service.delete(mgrSession, paramComm).second);
	}
	public void addTable(){
//		T6MgrAhr mdl = getModel(T6MgrAhr.class);
//	    // 检查手机号的用户是否存在
//		if (T6MgrAhrService.service.isExist(mdl)) {
//			ResTips errorTips = ResTips.getFailRes().addErroFiled(T6MgrAhr.column_crdt_no,"该证件号用户已存在");
//			renderJson(errorTips);
//			return ;
//		}
//		// 不存在则添加
//		mdl.set(T6MgrAhr.column_usr_nm, mdl.get(T6MgrAhr.column_nm));
//		
//		if (mdl.saveGenIntId()) {
//			renderJson(ResTips.getSuccRes());
//		} else {
//			renderJson(ResTips.getFailRes());
//		}
	}

	@Clear
	public void editTable(){
		T6MgrAhr mdl =getModel(T6MgrAhr.class);
	    // 检查手机号的用户是否存在
		if (!T6MgrAhrService.service.isExist(mdl)) {
			// 不存在则不可以更新
			ResTips errorTips = ResTips.getFailRes()
					.addErroFiled(T6MgrAhr.column_crdt_no, "该证件号用户不存在");
			renderJson(errorTips);
			return ;
		}
		mdl.set(T6MgrAhr.column_usr_nm, mdl.get(T6MgrAhr.column_nm));
		if (mdl.update()) {
			renderJson(ResTips.getSuccRes());
		} else {
			renderJson(ResTips.getFailRes());
		}
	}
	@Clear
	public void inload() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			 return ;
		}
		// 市级管理员不可导入管理员
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			renderText("您没有该权限!");
			 return ;
		}
		// 获取上传的excel文件
		String path = "files/upload/".trim();
		String base = this.getRequest().getContextPath().trim();// 应用路径
		UploadFile picFile = getFile("fileexcel");// 得到 文件对象
		String fileName = picFile.getFileName();
		String mimeType = picFile.getContentType();// 得到 上传文件的MIME类型:audio/mpeg
		
		String mimeTypeSuffix = fileName.substring(fileName.length() -4);
		String mimeTypeSuffix2 = fileName.substring(fileName.length() -5);

		log.info(mimeTypeSuffix + " " + mimeTypeSuffix2);

		 if(!ExcelParseTool.SUFFIX_2003.equals(mimeTypeSuffix) && !ExcelParseTool.SUFFIX_2007.equals(mimeTypeSuffix2)){
			 log.error("message:上传文件类型错误！！！"+fileName);
			 renderText("上传文件类型错误!");
			 return ;
		 }

		List<MdlExcelRow> table = null;
		try {
			table = ExcelParseTool.getWorkBookTable(picFile.getFile());
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			renderText("文件内容解析有误,请检查内容及格式!");
			return ;
		}
		// 存入数据库
		Tuple2<Boolean, String> rt = T6MgrAhrService.service.insertAdmin(mgrSession,table);
		if (rt.first) {
			renderText(EnumStatus.Success.getIdText());
		} else {
			log.error(rt.second);
			renderText(rt.second);
		}
		return;
	}

	/**
	 * 保存
	 */
	@Before(T6MgrAhrValidator.class)
	public void save() {
		T6MgrAhr t6MgrAhr = getModel(T6MgrAhr.class);
		// other set

		// t6MgrAhr.save(); //guiid
		t6MgrAhr.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T6MgrAhr t6MgrAhr = T6MgrAhr.dao.findById(getPara()); //guuid
		T6MgrAhr t6MgrAhr = T6MgrAhrService.service.SelectById(getParaToInt()); // serial
																				// int
																				// id
		setAttr("t6MgrAhr", t6MgrAhr);
		renderWithPath(pthv + "update.html");

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
		// T6MgrAhr t6MgrAhr = T6MgrAhr.dao.findById(getPara()); //guuid
		T6MgrAhr t6MgrAhr = T6MgrAhrService.service.SelectById(getParaToInt()); // serial
																				// int
																				// id
		setAttr("t6MgrAhr", t6MgrAhr);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T6MgrAhrService.service.delete("t6_mgr_ahr", getPara() == null ? ids
		// : getPara()); //guuid
		T6MgrAhrService.service.deleteById("t6_mgr_ahr", getPara() == null ? ids : getPara()); // serial
																								// int
																								// id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
