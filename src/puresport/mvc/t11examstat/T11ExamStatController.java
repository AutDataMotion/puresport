package puresport.mvc.t11examstat;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

import puresport.constant.ConstantInitMy;
import puresport.mvc.t10examgrd.T10ExamGrd;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/t11ExamStat
 * /jf/puresport/t11ExamStat/save
 * /jf/puresport/t11ExamStat/edit
 * /jf/puresport/t11ExamStat/update
 * /jf/puresport/t11ExamStat/view
 * /jf/puresport/t11ExamStat/delete
 * /puresport/t11ExamStat/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t11ExamStat")
public class T11ExamStatController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11ExamStatController.class);

	public static final String pthc = "/jf/puresport/t11ExamStat/";
	public static final String pthv = "/puresport/t11ExamStat/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T11ExamStat.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T11ExamStatValidator.class)
	public void save() {
		T11ExamStat t11ExamStat = getModel(T11ExamStat.class);
		//other set 
		
		//t11ExamStat.save();		//guiid
		t11ExamStat.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T11ExamStat t11ExamStat = T11ExamStat.dao.findById(getPara());	//guuid
		T11ExamStat t11ExamStat = T11ExamStatService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t11ExamStat", t11ExamStat);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T11ExamStatValidator.class)
	public void update() {
		getModel(T11ExamStat.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T11ExamStat t11ExamStat = T11ExamStat.dao.findById(getPara());	//guuid
		T11ExamStat t11ExamStat = T11ExamStatService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t11ExamStat", t11ExamStat);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T11ExamStatService.service.delete("t11_exam_stat", getPara() == null ? ids : getPara());	//guuid
		T11ExamStatService.service.deleteById("t11_exam_stat", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	@Clear
	public void get_exam_grd()
	{
		boolean flag = false;  
          
        String userID = getPara("userID");//获取表单数据，这里的参数就是页面表单中的name属性值  
        List<T11ExamStat> itemlist = T11ExamStat.dao.find("select * from t11_exam_stat where usrid=? and exam_st = '1'", userID);
        if(itemlist!=null)
        {
        	flag = true;
        	JSONObject resjson = new JSONObject();
        	resjson.put("flag", flag);
//        	json.put("itemlist", itemlist);
//        	renderJson(json);
        	JSONArray jsonArray = new JSONArray();
        	for(T11ExamStat item:itemlist)
        	{
        		JSONObject json = new JSONObject();
        		json.put("exam_grd", item.getExam_grd());
        		json.put("exam_name", item.getExam_nm());
        		json.put("tms", item.getTms());
        		
        		jsonArray.add(json);
        	}
        	resjson.put("itemlist", jsonArray);
        	renderJson(resjson);
        }
        else {
        	JSONObject json = new JSONObject();
        	json.put("flag", flag);
        	renderJson(json);
        }
	}
}
