package puresport.mvc.t1usrbsc;

import java.io.IOException;
import java.util.List;

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
import puresport.applicat.ExcelParseTool;
import puresport.applicat.MdlExcelRow;
import puresport.constant.EnumStatus;
import puresport.mvc.t6mgrahr.ParamComm;
import puresport.mvc.t6mgrahr.T6MgrAhrService;


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
		renderJson(T1usrBscService.service.selectByPage(getParamComm()));
	}

	@Clear
	public void getDataScore(){
		renderJson(T1usrBscService.service.selectScoreByPage(getParamComm()));
	}
	
	@Clear
	public void getDataPrjStatis(){
		renderJson(T1usrBscService.service.selectPassedPercent(getParamComm()));
	}
	
	@Clear
	public void getDataExamQues(){
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
		if (T1usrBscService.service.insertFromExcel(table, outFailedMdl)) {
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
        String msg = "";  
        int userType = 0;
        JSONObject json = new JSONObject();  
        
        String mblph_no = getPara("account");//获取表单数据，这里的参数就是页面表单中的name属性值  
        String password = getPara("pwd");  
        T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where mblph_no=?", mblph_no);//根据用户名查询数据库中的用户  
        if(item != null) {  
            if(password.equals(item.getPswd())) {//判断数据库中的密码与用户输入的密码是否一致  
                flag = true; 
                userType = item.getUsr_tp();
                getSession().setAttribute("usrid", item.getUsrid());//设置session，保存登录用户的昵称  
                getSession().setAttribute("phoneNum", item.getMblph_no());//设置session，保存登录用户的昵称  
                getSession().setAttribute("pwd", item.getPswd());//设置session，保存登录用户的昵称  
                getSession().setAttribute("usr_tp", item.getUsr_tp());//设置session，保存登录用户的昵称  
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
        json.put("url", getCxt()+"/jf/puresport/pagesController/selfcenter"); 
        renderJson(json);  
	}
	@Clear
	public void ImproveUserInfo()
	{
		boolean flag = false;  
        String msg = "";  
//        String userType = "";
        JSONObject json = new JSONObject();  
        
        Long userID = (Long) getSession().getAttribute("usrid");
        T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", userID);//根据用户名查询数据库中的用户  
        if(item!=null)
        {
//        	userType = getPara("userType");
            if((int)item.getUsr_tp()==0)//运动员
            {

            	String code = getPara("code");//获取表单数据，这里的参数就是页面表单中的name属性值  
                String competetion = getPara("competetion");
                String competetionitem = getPara("competetionitem");
//                item.setAdiv_cd(code);
//                item.setSpt_prj(competetionitem);
                int res = Db.update("update puresport.t1_usr_bsc set adiv_cd=?,spt_prj=? where usrid=?",code,competetionitem,userID);
                if(res>0)
                {
                	flag = true; 
                }
            }
            else {//辅助人员
            	String company = getPara("company");//获取表单数据，这里的参数就是页面表单中的name属性值  
                String position = getPara("position");
                int res = Db.update("update puresport.t1_usr_bsc set wrk_unit=?,post=? where usrid=?",company,position,userID);
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
