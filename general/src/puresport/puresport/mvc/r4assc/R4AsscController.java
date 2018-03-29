package puresport.mvc.r4assc;

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
 * /jf/puresport/r4Assc
 * /jf/puresport/r4Assc/save
 * /jf/puresport/r4Assc/edit
 * /jf/puresport/r4Assc/update
 * /jf/puresport/r4Assc/view
 * /jf/puresport/r4Assc/delete
 * /puresport/r4Assc/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/r4Assc")
public class R4AsscController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R4AsscController.class);

	public static final String pthc = "/jf/puresport/r4Assc/";
	public static final String pthv = "/puresport/r4Assc/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, R4Assc.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(R4AsscValidator.class)
	public void save() {
		R4Assc r4Assc = getModel(R4Assc.class);
		//other set 
		
		//r4Assc.save();		//guiid
		r4Assc.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//R4Assc r4Assc = R4Assc.dao.findById(getPara());	//guuid
		R4Assc r4Assc = R4AsscService.service.SelectById(getParaToInt());		//serial int id
		setAttr("r4Assc", r4Assc);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(R4AsscValidator.class)
	public void update() {
		getModel(R4Assc.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//R4Assc r4Assc = R4Assc.dao.findById(getPara());	//guuid
		R4Assc r4Assc = R4AsscService.service.SelectById(getParaToInt());		//serial int id
		setAttr("r4Assc", r4Assc);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//R4AsscService.service.delete("t4_assc", getPara() == null ? ids : getPara());	//guuid
		R4AsscService.service.deleteById("t4_assc", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
