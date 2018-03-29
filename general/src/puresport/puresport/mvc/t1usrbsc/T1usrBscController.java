package puresport.mvc.t1usrbsc;

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
	public static final String pthv = "/puresport/t1usrBsc/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T1usrBsc.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
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
