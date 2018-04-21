package puresport.mvc.t7crcl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import puresport.entity.ExamEntity;
import puresport.mvc.t9tstlib.T9Tstlib;


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
@Controller(controllerKey = "/jf/puresport/t7Crcl")
public class T7CrclController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7CrclController.class);

	public static final String pthc = "/jf/puresport/t7Crcl/";
	public static final String pthv = "/puresport/t7Crcl/";

	/**
	 * 列表
	 */
	public void index() {
//		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T7Crcl.sqlId_splitPage_from);
//		renderWithPath(pthv+"list.html");
		System.out.println("test");
		renderWithPath("/f/index.html");
	}
	
	public void test() {
//		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T7Crcl.sqlId_splitPage_from);
//		renderWithPath(pthv+"list.html");
		System.out.println("test");
		renderWithPath("/f/study.html");
	}
	
	
	public void generteTest() {
//		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T7Crcl.sqlId_splitPage_from);
//		renderWithPath(pthv+"list.html");
		// 选择题
		String sql = "select * from t9_tstlib t where t.prblm_tp ='01'";
		List<T9Tstlib> t9List = T9Tstlib.dao.find(sql);
		List<ExamEntity> examEntityList = new ArrayList<ExamEntity>();
		Integer questionNum = 0;
		for(T9Tstlib t9Tstlib : t9List) {
				ExamEntity examEntity = new ExamEntity();
				examEntity.setTtl((String) t9Tstlib.getTtl());
				examEntity.setPrblmid((Long) t9Tstlib.getPrblmid());
				examEntity.setOpt((String) t9Tstlib.getOpt());
				String option = examEntity.getOpt();
				String[] optionList = option.split("\\|");
				// 4个选项
				if(4 == optionList.length) {
					examEntity.setOptA(optionList[0]);
					examEntity.setOptB(optionList[1]);
					examEntity.setOptC(optionList[2]);
					examEntity.setOptD(optionList[3]);
				} else if(2 == optionList.length) {// 2个选项
					examEntity.setOptA(optionList[0]);
					examEntity.setOptB(optionList[1]);
				} 
				// 题号
				questionNum ++;
				examEntity.setQuestionNum(questionNum);
				examEntityList.add(examEntity);
		}
		List<String> T9List = new ArrayList<String>();
		setAttr("examSelectList", examEntityList);
		
		// 判断题
		questionNum = 0;
		List<ExamEntity> examEntityList2 = new ArrayList<ExamEntity>();
		sql = "select * from t9_tstlib t where t.prblm_tp ='02'";
		t9List = T9Tstlib.dao.find(sql);
		for(T9Tstlib t9Tstlib : t9List) {
				ExamEntity examEntity = new ExamEntity();
				examEntity.setTtl((String) t9Tstlib.getTtl());
				examEntity.setPrblmid((Long) t9Tstlib.getPrblmid());
				// 题号
				questionNum ++;
				examEntity.setQuestionNum(questionNum);
				examEntityList2.add(examEntity);
		}
		setAttr("examDeducList", examEntityList2);
		System.out.println("test");
		renderWithPath("/dotest.html");
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
