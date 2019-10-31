package puresport.mvc.t1usrbsc;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.RegexUtils;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.lyf.EmailUtils;
import csuduc.platform.util.tuple.Tuple2;
import puresport.applicat.ExcelParseTool;
import puresport.applicat.MdlExcelRow;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumStatus;
import puresport.mvc.comm.AuthCodeMdl;
import puresport.mvc.comm.ExportData2Excel;
import puresport.mvc.comm.PageViewSta;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ResTips;
import puresport.mvc.t6mgrahr.T6MgrSession;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t1usrBsc /jf/puresport/t1usrBsc/save
 * /jf/puresport/t1usrBsc/edit /jf/puresport/t1usrBsc/update
 * /jf/puresport/t1usrBsc/view /jf/puresport/t1usrBsc/delete
 * /puresport/t1usrBsc/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t1usrBsc")
public class T1usrBscController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscController.class);

	public static final String pthc = "/jf/puresport/t1usrBsc/";
	public static final String pthv = "/f/";

	public static final String aboutsporterDir = "files/querydata/aboutsporter/";
	public static final String aboutsporterFileName = "运动员.xls";
	
	public static final String keyPhoneCode = "keyPhoneCode";
	public static final String keyEmailCode = "keyEmailCode";
	
	public static final String messageTitle = "反兴奋剂在线教育平台";
	public static final String messageContent = "尊敬的用户您好，您的验证码是:";

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
	public void getAllData() throws Exception {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		List<T1usrBsc> resList = T1usrBscService.service.selectByPage(mgrSession, getParamWithServerPage());
		log.debug("getAllData-----" + resList.size() + "");
		String usrid = getSessionAttr("usrid");

		boolean result = ExportData2Excel.downLoadSporterExcel(resList, aboutsporterDir, usrid, aboutsporterFileName);
		JSONObject res = new JSONObject();
		res.put("flag", result);
		String fileUrl = getCxt() + "/" + aboutsporterDir + usrid + "/" + aboutsporterFileName;
		res.put("fileUrl", fileUrl);
//		renderText("getAllData-----"+resList.size()+"");
		renderJson(res);
	}

	public void addSporter() {
//		T1usrBsc mdl = getModel(T1usrBsc.class);
//	    // 检查手机号的用户是否存在
//		if (T1usrBscService.service.isExist(mdl)) {
//			ResTips errorTips = ResTips.getFailRes()
//					.addErroFiled(T1usrBsc.column_crdt_no, "该证件号用户已存在");
//			renderJson(errorTips);
//			return ;
//		}
//		// 不存在则添加
//		mdl.set(T1usrBsc.column_usr_nm, mdl.get(T1usrBsc.column_nm));
//		if (mdl.saveGenIntId()) {
//			renderJson(ResTips.getSuccRes());
//		} else {
//			renderJson(ResTips.getFailRes());
//		}
	}

	@Clear
	public void editSporter() {
		T1usrBsc mdl = getModel(T1usrBsc.class);
		// 检查手机号的用户是否存在
		if (!T1usrBscService.service.isExist(mdl)) {
			// 不存在则不可以更新
			ResTips errorTips = ResTips.getFailRes().addErroFiled(T1usrBsc.column_crdt_no, "该证件号用户已存在");
			renderJson(errorTips);
			return;
		}
		mdl.set(T1usrBsc.column_usr_nm, mdl.get(T1usrBsc.column_nm));
		if (mdl.update()) {
			renderJson(ResTips.getSuccRes());
		} else {
			renderJson(ResTips.getFailRes());
		}
	}

	public void delSporter() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		ParamComm paramComm = getParamCommDef();
		if (Objects.isNull(paramComm)) {
			log.error("delSporter 参数解析失败");
			renderText("参数解析失败!");
			return;
		}
		renderText(T1usrBscService.service.delete(mgrSession, paramComm).second);
	}

	@Clear
	public void getDataScore() throws InterruptedException {
		// 成绩统计
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJsonForTable(T1usrBscService.service.selectScoreByPage(mgrSession, getParamWithServerPage()));
	}

	@Clear
	public void getDataPrjStatis() {
		// 项目合格率统计
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJsonForTable(T1usrBscService.service.selectPassedPercent(mgrSession, getParamWithServerPage()));
	}

	@Clear
	public void getDataExamQues() {
		// 试题错误率统计
		renderJsonForTable(T1usrBscService.service.selectExamQuestion(getParamWithServerPage()));
	}

	@Clear
	public void inload() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		// 获取上传的excel文件
		String path = "files/upload/".trim();
		String base = this.getRequest().getContextPath().trim();// 应用路径
		UploadFile picFile = getFile("fileexcel");// 得到 文件对象
		String fileName = picFile.getFileName();
		String mimeType = picFile.getContentType();// 得到 上传文件的MIME类型:audio/mpeg

		String mimeTypeSuffix = fileName.substring(fileName.length() - 4);
		String mimeTypeSuffix2 = fileName.substring(fileName.length() - 5);
		log.info(mimeTypeSuffix + " " + mimeTypeSuffix2);

		if (!ExcelParseTool.SUFFIX_2003.equals(mimeTypeSuffix) && !ExcelParseTool.SUFFIX_2007.equals(mimeTypeSuffix2)) {
			log.error("message:上传文件类型错误！！！" + fileName);
			renderText("上传文件类型错误!");
			return;
		}

		List<MdlExcelRow> table = null;
		try {
			table = ExcelParseTool.getWorkBookTable(picFile.getFile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			renderText("文件内容解析有误,请检查内容及格式!");
			return;
		}
		// 存入数据库
		Tuple2<Boolean, String> rt = T1usrBscService.service.insertFromExcel(mgrSession, table);

		if (rt.first) {
			renderText(EnumStatus.Success.getIdText());
		} else {
			log.error(rt.second);
			renderText(rt.second);
		}
		return;
	}

	/**
	 * 列表
	 */
	@Clear
	public void login() {
		boolean flag = false;
		boolean needImproveInfoOrNot = false;
		boolean belongToInstitute = false;
		String msg = "";
		String userType = "";
		JSONObject json = new JSONObject();
		boolean authCode = authCode();

		String crdt_no = getPara("account");// 获取表单数据，这里的参数就是页面表单中的name属性值
		String password = getPara("pwd");
		try {
			if (authCode) {
				String encryptpassword = DESUtil.encrypt(password, ConstantInitMy.SPKEY);

				T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where crdt_no=?", crdt_no);// 根据用户名查询数据库中的用户
				if (item != null) {
					String pwddd = item.getPswd();
					if (encryptpassword.equals(item.getPswd())) {// 判断数据库中的密码与用户输入的密码是否一致
//    	        	if(password.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
						PageViewSta.StaLoginPeopleCountByDay();
						flag = true;
						userType = item.getUsr_tp();
						getSession().setAttribute("usrid", item.getUsrid());// 设置session，保存登录用户的昵称
						getSession().setAttribute("crdt_no", item.getCrdt_no());// 设置session，保存登录用户的昵称
						getSession().setAttribute("pwd", item.getPswd());// 设置session，保存登录用户的昵称
						getSession().setAttribute("usr_tp", item.getUsr_tp());// 设置session，保存登录用户的昵称

						if (item.getInstitute() != null) {
							belongToInstitute = true;
						}
						json.put("belongToInstitute", belongToInstitute);
						if (userType.equals("运动员"))// 运动员表 这个字段的初始值为运动员！
						{
							Object ss = item.getAdiv_cd();
//    	                	if(item.getAdiv_cd()!=null&&item.getSpt_prj()!=null)
//    	                	if(item.getProvince()!="--"&&item.getCity()!="--"&&item.getSpt_prj()!=null)
//    	                	{
//    	                		needImproveInfoOrNot  =false;
//    	                	}	
							if (item.getSpt_prj() != null) {
								needImproveInfoOrNot = false;
							} else {
								needImproveInfoOrNot = true;
							}
						} else {
							if (item.getDepartment() != null && item.getPost() != null) {
								needImproveInfoOrNot = false;
							} else {
								needImproveInfoOrNot = true;
							}
						}
						json.put("needImproveInfoOrNot", needImproveInfoOrNot);
					} else {
						msg = "密码不正确";
					}
				} else {
					msg = "帐号不存在";
				}
			} else {
				msg = "验证码错误";
			}

			json.put("flag", flag);
			json.put("userType", userType);
			json.put("msg", msg);
//	        json.put("url", getCxt()+"/jf/puresport/pagesController/selfcenter"); 
//	        System.out.println("login----"+(String)getSession().getAttribute("RequestURL"));
			if (getSession().getAttribute("RequestURL") != null) {
				json.put("url", (String) getSession().getAttribute("RequestURL"));
			} else {
				json.put("url", getCxt() + "/jf/puresport/pagesController");
			}
			renderJson(json);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Clear
	public void sendEmailCode() {

		String email = getPara("email");
		 if (StringUtils.isBlank(email) || !RegexUtils.checkEmail(email)) {
			 renderTextJson(ResTips.getFailRes("error, illegal email"));
			return ;
		}
		 
		AuthCodeMdl authCodeMdl =  (AuthCodeMdl)getSession().getAttribute(keyEmailCode);
		if (Objects.nonNull(authCodeMdl)) {
			if (!authCodeMdl.hasTimeOut(59)) {
				renderTextJson(ResTips.getFailRes("error, not timeOut"));
				return ;
			} 
		}
		
		authCodeMdl = AuthCodeMdl.createOne();
		
    	try {
			boolean flag = EmailUtils.sendTextMail(email,messageTitle, messageContent+authCodeMdl.getCode());
			if(flag)
			{
				getSession().setAttribute(keyEmailCode, authCodeMdl);
			}
			else {
				renderTextJson(ResTips.getFailRes("邮件发送失败,请您稍后重试"));
				return ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("--------validateEmail 发送验证码到邮箱失败！！");
			e.printStackTrace();
			renderTextJson(ResTips.getFailRes("邮件发送失败,请您联系管理员"));
			return ;
		}

    	renderTextJson(ResTips.SUCCESS);
	}

	@Clear
	public void sendPhoneCode() {

		 String phone = getPara("phone");
		 if (StringUtils.isBlank(phone) || !RegexUtils.checkMobile(phone)) {
			 renderTextJson(ResTips.getFailRes("error, illegal phone"));
			return ;
		}
		 
		AuthCodeMdl authCodeMdl =  (AuthCodeMdl)getSession().getAttribute(keyPhoneCode);
		if (Objects.nonNull(authCodeMdl)) {
			if (!authCodeMdl.hasTimeOut(59)) {
				renderTextJson(ResTips.getFailRes("error, not timeOut"));
				return ;
			} 
		}
		
		authCodeMdl = AuthCodeMdl.createOne();
		// todo send code to phone
		
		getSession().setAttribute(keyPhoneCode, authCodeMdl);
		
		renderTextJson(ResTips.SUCCESS);
	}

	/**
	 * 注册主入口
	 */
	@Clear
	public void regist() {

		AuthCodeMdl authCodeMdlPhone =  (AuthCodeMdl)getSession().getAttribute(keyPhoneCode);
		AuthCodeMdl authCodeMdlEmail =  (AuthCodeMdl)getSession().getAttribute(keyEmailCode);
		if (Objects.isNull(authCodeMdlPhone) && Objects.isNull(authCodeMdlEmail)) {
			renderTextJson(ResTips.getFailRes("验证码已过期，请重新获取验证码"));
			return;
		}
		// 入参 校验
		T1userBscDTO t1userBscDTO = getParamWithClass(T1userBscDTO.class);
		if (Objects.isNull(t1userBscDTO)) {
			renderTextJson(ResTips.getFailRes());
			return;
		}
		// 验证输入
		if (!t1userBscDTO.validate(authCodeMdlPhone, authCodeMdlEmail)) {
			renderTextJson(ResTips.getFailRes(t1userBscDTO.getTipList()));
			return;
		}

		// 数据库是否已经存在该用户
		T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where crdt_no=? limit 1 ",
				t1userBscDTO.getCrdt_no());
		if (item != null) {
			renderTextJson(ResTips.getFailRes(
					String.format("该证件号【%s】已注册，请登录！", t1userBscDTO.getCrdt_no())));
			return ;
		}
		// 入库
		if(T1usrBscService.service.addUserBsc(t1userBscDTO)) {
			renderTextJson(ResTips.getSuccRes());
			return ;
		} else {
			renderTextJson(ResTips.getFailRes());
			return ;
		}
	}

	@Clear
	public void ImproveUserInfo() {
		boolean flag = false;
		String msg = "";
//        String userType = "";
		JSONObject json = new JSONObject();

		String usertype = getPara("usertype");

		Long userID = Long.valueOf((String) getSession().getAttribute("usrid"));
		T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", userID);// 根据用户名查询数据库中的用户
		if (item != null) {
//        	userType = getPara("userType");
//            if(((String)item.getUsr_tp()).equals("运动员"))//运动员
			if (usertype.equals("运动员"))// 运动员
			{

//            	String province = getPara("province");
//            	String city = getPara("city");
				String competetion = getPara("competetion");
				String competetionitem = getPara("competetionitem");

//                int res = Db.update("update puresport.t1_usr_bsc set province=?,city=?,spt_prj=? where usrid=?",province,city,competetionitem,userID);
				int res = Db.update("update puresport.t1_usr_bsc set usr_tp=?,spt_prj=? where usrid=?", usertype,
						competetionitem, userID);
				if (res > 0) {
					flag = true;
					getSession().setAttribute("usr_tp", usertype);// 设置session，保存登录用户的昵称
				}
			} else {// 辅助人员
				String belongToInstitute = getPara("belongToInstitute");
				int res = 0;
				if (StringUtil.notEmpty(belongToInstitute) && belongToInstitute.equals("true")) {
					String company = getPara("company");// 获取表单数据，这里的参数就是页面表单中的name属性值
					String position = getPara("position");
					String spt_prj = getPara("CompetetionItem_user_assist");

					res = Db.update(
							"update puresport.t1_usr_bsc set usr_tp=?,spt_prj=?,department=?,post=? where usrid=?",
							usertype, spt_prj, company, position, userID);
				} else {
					String company = getPara("company");// 获取表单数据，这里的参数就是页面表单中的name属性值
					String position = getPara("position");
					res = Db.update("update puresport.t1_usr_bsc set usr_tp=?,department=?,post=? where usrid=?",
							usertype, company, position, userID);
				}

				if (res > 0) {
					flag = true;
					getSession().setAttribute("usr_tp", usertype);// 设置session，保存登录用户的昵称
				}
			}
		} else {
			msg = "更新失败";
		}
		json.put("flag", flag);
		json.put("msg", msg);
		json.put("url", getCxt() + "/jf/puresport/pagesController/selfcenter");
		renderJson(json);

	}

	/**
	 * 保存
	 */
	@Before(T1usrBscValidator.class)
	public void save() {
		T1usrBsc t1usrBsc = getModel(T1usrBsc.class);
		// other set

		// t1usrBsc.save(); //guiid
		t1usrBsc.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T1usrBsc t1usrBsc = T1usrBsc.dao.findById(getPara()); //guuid
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t1usrBsc", t1usrBsc);
		renderWithPath(pthv + "update.html");

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
		// T1usrBsc t1usrBsc = T1usrBsc.dao.findById(getPara()); //guuid
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t1usrBsc", t1usrBsc);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T1usrBscService.service.delete("t1_usr_bsc", getPara() == null ? ids :
		// getPara()); //guuid
		T1usrBscService.service.deleteById("t1_usr_bsc", getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
