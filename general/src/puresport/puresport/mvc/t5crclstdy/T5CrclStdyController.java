package puresport.mvc.t5crclstdy;

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
 * /jf/puresport/t5CrclStdy
 * /jf/puresport/t5CrclStdy/save
 * /jf/puresport/t5CrclStdy/edit
 * /jf/puresport/t5CrclStdy/update
 * /jf/puresport/t5CrclStdy/view
 * /jf/puresport/t5CrclStdy/delete
 * /puresport/t5CrclStdy/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t5CrclStdy")
public class T5CrclStdyController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T5CrclStdyController.class);

	public static final String pthc = "/jf/puresport/t5CrclStdy/";
	public static final String pthv = "/puresport/t5CrclStdy/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T5CrclStdy.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T5CrclStdyValidator.class)
	public void save() {
		T5CrclStdy t5CrclStdy = getModel(T5CrclStdy.class);
		//other set 
		
		//t5CrclStdy.save();		//guiid
		t5CrclStdy.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T5CrclStdy t5CrclStdy = T5CrclStdy.dao.findById(getPara());	//guuid
		T5CrclStdy t5CrclStdy = T5CrclStdyService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t5CrclStdy", t5CrclStdy);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T5CrclStdyValidator.class)
	public void update() {
		getModel(T5CrclStdy.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T5CrclStdy t5CrclStdy = T5CrclStdy.dao.findById(getPara());	//guuid
		T5CrclStdy t5CrclStdy = T5CrclStdyService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t5CrclStdy", t5CrclStdy);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T5CrclStdyService.service.delete("t5_crcl_stdy", getPara() == null ? ids : getPara());	//guuid
		T5CrclStdyService.service.deleteById("t5_crcl_stdy", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
