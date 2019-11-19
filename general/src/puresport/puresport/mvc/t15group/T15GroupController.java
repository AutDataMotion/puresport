package puresport.mvc.t15group;

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
 * /jf/puresport/t15Group
 * /jf/puresport/t15Group/save
 * /jf/puresport/t15Group/edit
 * /jf/puresport/t15Group/update
 * /jf/puresport/t15Group/view
 * /jf/puresport/t15Group/delete
 * /puresport/t15Group/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t15Group")
public class T15GroupController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15GroupController.class);

	public static final String pthc = "/jf/puresport/t15Group/";
	public static final String pthv = "/puresport/t15Group/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T15Group.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T15GroupValidator.class)
	public void save() {
		T15Group t15Group = getModel(T15Group.class);
		//other set 
		
		//t15Group.save();		//guiid
		t15Group.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T15Group t15Group = T15Group.dao.findById(getPara());	//guuid
		T15Group t15Group = T15GroupService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15Group", t15Group);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T15GroupValidator.class)
	public void update() {
		getModel(T15Group.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T15Group t15Group = T15Group.dao.findById(getPara());	//guuid
		T15Group t15Group = T15GroupService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15Group", t15Group);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T15GroupService.service.delete("t15_group", getPara() == null ? ids : getPara());	//guuid
		T15GroupService.service.deleteById("t15_group", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
