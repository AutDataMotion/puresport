package puresport.mvc.area;

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
 * /jf/puresport/area
 * /jf/puresport/area/save
 * /jf/puresport/area/edit
 * /jf/puresport/area/update
 * /jf/puresport/area/view
 * /jf/puresport/area/delete
 * /puresport/area/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/area")
public class AreaController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AreaController.class);

	public static final String pthc = "/jf/puresport/area/";
	public static final String pthv = "/puresport/area/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, Area.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(AreaValidator.class)
	public void save() {
		Area area = getModel(Area.class);
		//other set 
		
		//area.save();		//guiid
		area.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//Area area = Area.dao.findById(getPara());	//guuid
		Area area = AreaService.service.SelectById(getParaToInt());		//serial int id
		setAttr("area", area);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(AreaValidator.class)
	public void update() {
		getModel(Area.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//Area area = Area.dao.findById(getPara());	//guuid
		Area area = AreaService.service.SelectById(getParaToInt());		//serial int id
		setAttr("area", area);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//AreaService.service.delete("dt_area", getPara() == null ? ids : getPara());	//guuid
		AreaService.service.deleteById("dt_area", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
