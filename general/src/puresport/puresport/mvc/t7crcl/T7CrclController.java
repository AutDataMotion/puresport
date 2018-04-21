package puresport.mvc.t7crcl;

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
 * /jf/puresport/t7Crcl
 * /jf/puresport/t7Crcl/save
 * /jf/puresport/t7Crcl/edit
 * /jf/puresport/t7Crcl/update
 * /jf/puresport/t7Crcl/view
 * /jf/puresport/t7Crcl/delete
 * /puresport/t7Crcl/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t7Crcl")
public class T7CrclController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7CrclController.class);

	public static final String pthc = "/jf/puresport/t7Crcl/";
	public static final String pthv = "/puresport/t7Crcl/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T7Crcl.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T7CrclValidator.class)
	public void save() {
		T7Crcl t7Crcl = getModel(T7Crcl.class);
		//other set 
		
		//t7Crcl.save();		//guiid
		t7Crcl.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T7Crcl t7Crcl = T7Crcl.dao.findById(getPara());	//guuid
		T7Crcl t7Crcl = T7CrclService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t7Crcl", t7Crcl);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T7CrclValidator.class)
	public void update() {
		getModel(T7Crcl.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T7Crcl t7Crcl = T7Crcl.dao.findById(getPara());	//guuid
		T7Crcl t7Crcl = T7CrclService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t7Crcl", t7Crcl);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T7CrclService.service.delete("t7_crcl", getPara() == null ? ids : getPara());	//guuid
		T7CrclService.service.deleteById("t7_crcl", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
