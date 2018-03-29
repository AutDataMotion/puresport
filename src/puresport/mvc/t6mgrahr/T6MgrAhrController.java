package puresport.mvc.t6mgrahr;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;
import com.jfinal.aop.Before;

import puresport.constant.ConstantInitMy;


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
