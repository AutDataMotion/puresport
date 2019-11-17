package puresport.mvc.t15creditinf;

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
 * /jf/puresport/t15CreditInf
 * /jf/puresport/t15CreditInf/save
 * /jf/puresport/t15CreditInf/edit
 * /jf/puresport/t15CreditInf/update
 * /jf/puresport/t15CreditInf/view
 * /jf/puresport/t15CreditInf/delete
 * /puresport/t15CreditInf/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t15CreditInf")
public class T15CreditInfController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15CreditInfController.class);

	public static final String pthc = "/jf/puresport/t15CreditInf/";
	public static final String pthv = "/puresport/t15CreditInf/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T15CreditInf.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T15CreditInfValidator.class)
	public void save() {
		T15CreditInf t15CreditInf = getModel(T15CreditInf.class);
		//other set 
		
		//t15CreditInf.save();		//guiid
		t15CreditInf.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T15CreditInf t15CreditInf = T15CreditInf.dao.findById(getPara());	//guuid
		T15CreditInf t15CreditInf = T15CreditInfService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15CreditInf", t15CreditInf);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T15CreditInfValidator.class)
	public void update() {
		getModel(T15CreditInf.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T15CreditInf t15CreditInf = T15CreditInf.dao.findById(getPara());	//guuid
		T15CreditInf t15CreditInf = T15CreditInfService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15CreditInf", t15CreditInf);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T15CreditInfService.service.delete("t15_credit_inf", getPara() == null ? ids : getPara());	//guuid
		T15CreditInfService.service.deleteById("t15_credit_inf", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
