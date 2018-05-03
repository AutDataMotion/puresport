package puresport.mvc.t7crcl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import puresport.entity.ExamEntity;
import puresport.entity.ResultEntity;
import puresport.mvc.t10examgrd.T10ExamGrd;
//import puresport.entity.ExamEntity;
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
	private static Logger LOG = Logger.getLogger(T7CrclController.class);

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
	
	
	// 从30道选择题中随机取10道，从30道判断题中随机取10道构成试卷。并保存到成绩记录表中。	
	// zhuchaobin
		public void generteTest() {
			// 选择题
			String sql = "select * from t9_tstlib t where t.prblm_tp ='01' order by rand() limit 3";
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
					// 答案
					examEntity.setPrblm_aswr((String) t9Tstlib.getPrblm_aswr());
					// 题号
					questionNum ++;
					examEntity.setPrblmno(questionNum);
					examEntityList.add(examEntity);
			}
		
			// 判断题
			List<ExamEntity> examEntityList2 = new ArrayList<ExamEntity>();
			sql = "select * from t9_tstlib t where t.prblm_tp ='02' order by rand() limit 3";
			t9List = T9Tstlib.dao.find(sql);
			for(T9Tstlib t9Tstlib : t9List) {
					ExamEntity examEntity = new ExamEntity();
					examEntity.setTtl((String) t9Tstlib.getTtl());
					examEntity.setPrblmid((Long)t9Tstlib.getPrblmid());
					// 答案
					examEntity.setPrblm_aswr((String) t9Tstlib.getPrblm_aswr());
					// 题号
					questionNum ++;
					examEntity.setPrblmno((Integer)questionNum);
					examEntityList2.add(examEntity);
			}


			// 生成考试ID
			sql = "select * from t10_exam_grd t where t.usrid = '" + "333" + "' order by examid desc";
			List<T10ExamGrd>  t10List = T10ExamGrd.dao.find(sql);	
			Integer examid = 1;
			if((null == t10List) || (0 == t10List.size())) {
				LOG.debug("用户首次考试，考试ID取1");
				examid = 1;
			} else {
				LOG.debug("用户非首次考试，考试ID取上次考试ID + 1");
				T10ExamGrd t10 = t10List.get(0);
				examid = (Integer) t10List.get(0).getExamid() + 1;
			}

			for(ExamEntity examEntity : examEntityList) {
				// 保存试题到“考试成绩信息表”
				T10ExamGrd t10ExamGrd = new T10ExamGrd();
				t10ExamGrd.setUsrid(333);
				// 考试状态：0未考试
				t10ExamGrd.setExam_st("0");
//				// 考试开始时间
				t10ExamGrd.setExamStTm(new Timestamp(System.currentTimeMillis()));
				t10ExamGrd.setExam_st("0");
				t10ExamGrd.setExamid(examid);
				
				t10ExamGrd.setPrblmid(examEntity.getPrblmid());
				t10ExamGrd.setPrblmno(examEntity.getPrblmno());
				t10ExamGrd.setPrblm_aswr(examEntity.getPrblm_aswr());
				t10ExamGrd.saveGenIntId();
			}
			for(ExamEntity examEntity : examEntityList2) {
				
				// 保存试题到“考试成绩信息表”
				T10ExamGrd t10ExamGrd = new T10ExamGrd();
				t10ExamGrd.setUsrid(333);
				// 考试状态：0未考试
				t10ExamGrd.setExam_st("0");
//				// 考试开始时间
				t10ExamGrd.setExamStTm(new Timestamp(System.currentTimeMillis()));
				t10ExamGrd.setExam_st("0");
				t10ExamGrd.setExamid(examid);
				
				t10ExamGrd.setPrblmid(examEntity.getPrblmid());
				t10ExamGrd.setPrblmno(examEntity.getPrblmno());
				t10ExamGrd.setPrblm_aswr(examEntity.getPrblm_aswr());
				t10ExamGrd.saveGenIntId();
			}
			setAttr("examid", examid);
			setAttr("examSelectList", examEntityList);
			setAttr("examDeducList", examEntityList2);
			renderWithPath("/dotest.html");
		}
		
		// 提交考试，判定对错，记录题目记录，考试成绩
		// zhuchaobin
			public void submitExam() {
//				// 处理结果
//				ResultEntity res = null;
					String[] ds = getParaValues("dataSet");
					T10ExamGrd t10 = new T10ExamGrd();
					t10.setUsrid(333);
					t10.setExamid(1);
					// 总成绩
					Integer score = 0;
					for(String id : ds) {
						Integer pos = id.indexOf(":");
						// 题号
						String prblmno = id.substring(0, pos);
						String usr_aswr = "";
						if(id.length() > pos)
							usr_aswr = id.substring(pos + 1);
						// 查询答案
						t10.setPrblmno(Integer.parseInt(prblmno));
						String sql = "select * from t10_exam_grd t where t.usrid = '" + t10.getUsrid() + "' and t.examid ='" 
						+ t10.getExamid() + "' and t.prblmno = '" + t10.getPrblmno() + "'";
						List<T10ExamGrd>  t10List = T10ExamGrd.dao.find(sql);						
						if((null == t10List) || (0 == t10List.size())) {
							LOG.error("试题不在成绩表中，请确认");
						} else {
							LOG.debug("判断答案是否正确");
							T10ExamGrd t10Data = t10List.get(0);
							String answer = t10Data.getPrblm_aswr();
							// 答案比对
							boolean isRight = true;
							if(!StringUtils.isBlank(answer)) {
								// 答案其格式化，去掉多余字符
								answer = answer.replace(",", "");
								answer = answer.replace("'", "");	
								answer = answer.replace("、", "");
								if(answer.length() == usr_aswr.length()) {
									for(int i = 0; i < usr_aswr.length(); i ++) {
										String option = usr_aswr.substring(i, i+1);
										if(-1 == answer.indexOf(option)) {
											isRight = false;
										}
									}
								} else {
									isRight = false;
								}
							} else {
								LOG.error("查询不到答案，无法比对是否正确");
							}
							if(isRight) {
								score ++;
								t10Data.setExam_grd(1);
							} else {
								t10Data.setExam_grd(0);
							}
							t10Data.setUsr_aswr(usr_aswr);
							t10Data.setExam_st("1");
							t10Data.setExamEndTm(new Timestamp(System.currentTimeMillis()));
							t10Data.update();
						}
					}	
//					float fscore = (float) (score * 100.0 / 6.0); 
					ResultEntity res = new ResultEntity("0000", "恭喜您：您已完成测试，您的成绩为：" + score + "分！");
					renderJson(res);
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
