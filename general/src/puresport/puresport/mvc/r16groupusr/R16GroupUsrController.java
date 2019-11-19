package puresport.mvc.r16groupusr;

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
 * /jf/puresport/r16GroupUsr
 * /jf/puresport/r16GroupUsr/save
 * /jf/puresport/r16GroupUsr/edit
 * /jf/puresport/r16GroupUsr/update
 * /jf/puresport/r16GroupUsr/view
 * /jf/puresport/r16GroupUsr/delete
 * /puresport/r16GroupUsr/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/r16GroupUsr")
public class R16GroupUsrController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R16GroupUsrController.class);

	public static final String pthc = "/jf/puresport/r16GroupUsr/";
	public static final String pthv = "/puresport/r16GroupUsr/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, R16GroupUsr.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(R16GroupUsrValidator.class)
	public void save() {
		R16GroupUsr r16GroupUsr = getModel(R16GroupUsr.class);
		//other set 
		
		//r16GroupUsr.save();		//guiid
		r16GroupUsr.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//R16GroupUsr r16GroupUsr = R16GroupUsr.dao.findById(getPara());	//guuid
		R16GroupUsr r16GroupUsr = R16GroupUsrService.service.SelectById(getParaToInt());		//serial int id
		setAttr("r16GroupUsr", r16GroupUsr);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(R16GroupUsrValidator.class)
	public void update() {
		getModel(R16GroupUsr.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//R16GroupUsr r16GroupUsr = R16GroupUsr.dao.findById(getPara());	//guuid
		R16GroupUsr r16GroupUsr = R16GroupUsrService.service.SelectById(getParaToInt());		//serial int id
		setAttr("r16GroupUsr", r16GroupUsr);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//R16GroupUsrService.service.delete("r16_group_usr", getPara() == null ? ids : getPara());	//guuid
		R16GroupUsrService.service.deleteById("r16_group_usr", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
