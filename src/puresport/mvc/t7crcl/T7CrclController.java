package puresport.mvc.t7crcl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import puresport.entity.ExamEntity;
import puresport.entity.ResultEntity;
import puresport.mvc.t10examgrd.T10ExamGrd;
import puresport.mvc.t11examstat.T11ExamStat;
import puresport.mvc.t5crclstdy.T5CrclStdy;
import puresport.mvc.t5crclstdy.T5CrclStdyController;
//import puresport.entity.ExamEntity;
import puresport.mvc.t9tstlib.T9Tstlib;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t7Crcl /jf/puresport/t7Crcl/save /jf/puresport/t7Crcl/edit
 * /jf/puresport/t7Crcl/update /jf/puresport/t7Crcl/view
 * /jf/puresport/t7Crcl/delete /puresport/t7Crcl/add.html
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
		// paging(ConstantInitMy.db_dataSource_main, splitPage,
		// BaseModel.sqlId_splitPage_select, T7Crcl.sqlId_splitPage_from);
		// renderWithPath(pthv+"list.html");
		System.out.println("test");
		renderWithPath("/f/index.html");
	}

	public void test() {
		// paging(ConstantInitMy.db_dataSource_main, splitPage,
		// BaseModel.sqlId_splitPage_select, T7Crcl.sqlId_splitPage_from);
		// renderWithPath(pthv+"list.html");
		System.out.println("test");
		renderWithPath("/f/study.html");
	}

	/**
	 * 描述：学习说明_1 zhuchaobin 2018-05-09
	 */
	public void study_notify_1() {
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		System.out.println(usrid);
		List<T7Crcl> t7List = queryCrcl(1, usrid);
		setAttr("t7", t7List.get(0));
		LOG.debug("crclid = " + t7List.get(0).getCrclid());
		// 检查课程是否完成，只要有一门完成即可
		for (T7Crcl t7 : t7List) {
			if ("1".equals(t7.getStdy_st())) {
				setAttr("stdy_st_hidden", "1");
				break;
			}
		}
		renderWithPath("/f/accession/study_notify_1.html");
	}

	/**
	 * 描述：视频播放 zhuchaobin 2018-05-09
	 */
	@Clear
	public void video_play() {
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		System.out.println(usrid);
		String crcl_attr = getPara("crcl_attr");
		String crcl_file_rte = getPara("crcl_file_rte");
		// String stdy_st = getPara("stdy_st");
		System.out.println(crcl_attr);
		System.out.println(crcl_file_rte);
		if ("1".equals(crcl_attr)) {
			setAttr("action", "/jf/puresport/t7Crcl/video2_select_3");// 必修视频2
			// 必修课程1
			String sql = "select * from t7_crcl t where t.crclid='" + getPara("crclid") + "'";
			List<T7Crcl> t7List = T7Crcl.dao.find(sql);
			if ((t7List != null) && (t7List.size() > 0)) {	// 去视频1			
				setAttr("crcl_nm", "必修课程一：" + t7List.get(0).getCrcl_nm());// 课程名称
				setAttr("crcl_brf", t7List.get(0).getCrcl_brf());// 课程简介
			}
			// setAttr("stdy_st", stdy_st);// 必修视频2
		} else if ("2".equals(crcl_attr)) {// 去视频2
			setAttr("action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3
			setAttr("crcl_nm", "必修课程二：" + getPara("crcl_nm"));// 课程名称
		} else if ("3".equals(crcl_attr)) {// 去视频3
			setAttr("crcl_nm", "必修课程三：" + getPara("crcl_nm"));// 课程名称
			setAttr("action", "/jf/puresport/t7Crcl/generteTest");// 生成考试
		}
		setAttr("crcl_file_rte", crcl_file_rte);
		setAttr("stdy_st_hidden", getPara("stdy_st_hidden"));// 本课程学习状态
		String crclid = getPara("crclid");
		setAttr("crclid", getPara("crclid"));// 课程id
		if (!("1".equals(crcl_attr))){
			setAttr("crcl_brf", getPara("crcl_brf"));// 课程简介
		}
		LOG.debug("crclid = " + crclid);
		renderWithPath("/f/accession/video_play.html");
	}

	/**
	 * 描述：视频2选择_3 zhuchaobin 2018-05-09
	 */
	@Clear
	public void video2_select_3() {
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		List<T7Crcl> t7List = queryCrcl(2, usrid);
		setAttr("t7List", t7List);
		setAttr("course_title", "案例篇选学（选择一篇完成观看）");// 课程标题
		// 检查课程是否完成，只要有一门完成即可
		for (T7Crcl t7 : t7List) {
			if ("1".equals(t7.getStdy_st())) {
				setAttr("stdy_st_hidden", "1");
				break;
			}
		}
		renderWithPath("/f/accession/video2_select_3.html");
	}

	/**
	 * 描述：视频3选择_5 zhuchaobin 2018-05-09
	 */
	@Clear
	public void video3_select_5() {
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		List<T7Crcl> t7List = queryCrcl(3, usrid);
		setAttr("t7List", t7List);
		setAttr("course_title", "流程篇选学（选择一篇完成观看）");// 课程标题
		// 检查课程是否完成，只要有一门完成即可
		for (T7Crcl t7 : t7List) {
			if ("1".equals(t7.getStdy_st())) {
				setAttr("stdy_st_hidden", "1");
				break;
			}
		}
		renderWithPath("/f/accession/video2_select_3.html");
	}

	public List<T7Crcl> queryCrcl(Integer flag, Integer usrid) {
		ResultEntity res = null;
		StringBuilder desc = new StringBuilder("");
		boolean isCorse1Fnsh = false;
		boolean isCorse2Fnsh = false;
		boolean isCorse3Fnsh = false;
		List<T7Crcl> t7List1 = new ArrayList<T7Crcl>();
		List<T7Crcl> t7List2 = new ArrayList<T7Crcl>();
		List<T7Crcl> t7List3 = new ArrayList<T7Crcl>();
		List<T7Crcl> t7List4 = new ArrayList<T7Crcl>();
		// 必修课程1
		String sql = "select t.*, s.stdy_st as stdy_st from t7_crcl t LEFT JOIN t5_crcl_stdy s "
				+ "on t.crclid=s.crclid and s.usrid='" + usrid + "' order by t.crclid, t.crcl_attr";
		List<T7Crcl> t7List = T7Crcl.dao.find(sql);
		if ((t7List != null) && (t7List.size() > 0)) {
			for (T7Crcl t7 : t7List) {
				if ("1".equals(t7.getCrcl_attr())) {// 必修1
					// 课程是否已修
					if ("1".equals(t7.getStdy_st())) {
						String path = t7.getThumbnail_rte().toString();
						t7.setThumbnail_rte(path.substring(0, path.length() - 4) + "_ed.jpg");
					}
					t7List1.add(t7);
				} else if ("2".equals(t7.getCrcl_attr())) {// 必修2
					// 课程是否已修
					if ("1".equals(t7.getStdy_st())) {
						String path = t7.getThumbnail_rte().toString();
						t7.setThumbnail_rte(path.substring(0, path.length() - 4) + "_ed.jpg");
					}
					t7List2.add(t7);
				} else if ("3".equals(t7.getCrcl_attr())) {// 必修3
					// 课程是否已修
					if ("1".equals(t7.getStdy_st())) {
						String path = t7.getThumbnail_rte().toString();
						t7.setThumbnail_rte(path.substring(0, path.length() - 4) + "_ed.jpg");
					}
					t7List3.add(t7);
				} else if ("4".equals(t7.getCrcl_attr())) {// 下载资料
					t7List4.add(t7);
				}
			}
			setAttr("t7List1", t7List1);
			setAttr("t7List2", t7List2);
			setAttr("t7List3", t7List3);
			setAttr("t7List4", t7List4);
			if (1 == flag) {
				return t7List1;
			} else if (2 == flag) {
				return t7List2;
			} else if (3 == flag) {
				return t7List3;
			}
			LOG.debug("查询课程信息成功结束.");
		} else {
			LOG.error("查询课程信息失败！");
		}
		return null;
	}

	// 从30道选择题中随机取10道，从30道判断题中随机取10道构成试卷。并保存到成绩记录表中。
	// zhuchaobin
	@Clear
	public void generteTest() {
		if(!isCanTest())
			renderWithPath("/f/accession/dotest.html");;
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		// 选择题
		String sql = "select * from t9_tstlib t where t.prblm_tp ='01' order by rand() limit 10";
		List<T9Tstlib> t9List = T9Tstlib.dao.find(sql);
		List<ExamEntity> examEntityList = new ArrayList<ExamEntity>();
		Integer questionNum = 0;
		for (T9Tstlib t9Tstlib : t9List) {
			ExamEntity examEntity = new ExamEntity();
			examEntity.setTtl((String) t9Tstlib.getTtl());
			examEntity.setPrblmid(Integer.parseInt((String) t9Tstlib.getPrblmid()));
			examEntity.setOpt((String) t9Tstlib.getOpt());
			String option = examEntity.getOpt();
			String[] optionList = option.split("\\|");
			// 4个选项
			if (4 == optionList.length) {
				examEntity.setOptA(optionList[0]);
				examEntity.setOptB(optionList[1]);
				examEntity.setOptC(optionList[2]);
				examEntity.setOptD(optionList[3]);
			} else if (2 == optionList.length) {// 2个选项
				examEntity.setOptA(optionList[0]);
				examEntity.setOptB(optionList[1]);
			}
			// 答案
			examEntity.setPrblm_aswr((String) t9Tstlib.getPrblm_aswr());
			// 题号
			questionNum++;
			examEntity.setPrblmno(questionNum);
			examEntityList.add(examEntity);
		}

		// 判断题
		List<ExamEntity> examEntityList2 = new ArrayList<ExamEntity>();
		sql = "select * from t9_tstlib t where t.prblm_tp ='02' order by rand() limit 10";
		t9List = T9Tstlib.dao.find(sql);
		for (T9Tstlib t9Tstlib : t9List) {
			ExamEntity examEntity = new ExamEntity();
			examEntity.setTtl((String) t9Tstlib.getTtl());
			examEntity.setPrblmid(Integer.parseInt((String) t9Tstlib.getPrblmid()));
			// 答案
			examEntity.setPrblm_aswr((String) t9Tstlib.getPrblm_aswr());
			// 题号
			questionNum++;
			examEntity.setPrblmno((Integer) questionNum);
			examEntityList2.add(examEntity);
		}

		// 生成考试ID
		sql = "select * from t10_exam_grd t where t.usrid = '" + usrid + "' order by examid desc";
		List<T10ExamGrd> t10List = T10ExamGrd.dao.find(sql);
		Integer examid = 1;
		if ((null == t10List) || (0 == t10List.size())) {
			LOG.debug("用户首次考试，考试ID取1");
			examid = 1;
		} else {
			LOG.debug("用户非首次考试，考试ID取上次考试ID + 1");
			T10ExamGrd t10 = t10List.get(0);
			examid = Integer.parseInt((String) t10List.get(0).getExamid()) + 1;
		}

		for (ExamEntity examEntity : examEntityList) {
			// 保存试题到“考试成绩信息表”
			T10ExamGrd t10ExamGrd = new T10ExamGrd();
			t10ExamGrd.setUsrid(usrid);
			// 考试状态：0未考试
			t10ExamGrd.setExam_st("0");
			// // 考试开始时间
			t10ExamGrd.setExamStTm(new Timestamp(System.currentTimeMillis()));
			t10ExamGrd.setExam_st("0");
			t10ExamGrd.setExamid(examid);
			t10ExamGrd.setPrblmid(examEntity.getPrblmid());
			t10ExamGrd.setPrblmno(examEntity.getPrblmno());
			t10ExamGrd.setPrblm_aswr(examEntity.getPrblm_aswr());
			t10ExamGrd.saveGenIntId();
		}
		for (ExamEntity examEntity : examEntityList2) {
			// 保存试题到“考试成绩信息表”
			T10ExamGrd t10ExamGrd = new T10ExamGrd();
			t10ExamGrd.setUsrid(usrid);
			// 考试状态：0未考试
			t10ExamGrd.setExam_st("0");
			// // 考试开始时间
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
		renderWithPath("/f/accession/dotest.html");
	}

	public boolean isCanTest() {
		/*
		 * getModel(T5CrclStdy.class).update(); redirect(pthc);
		 */
		/* String usrid = getPara("usrid"); */
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		ResultEntity res = null;
		StringBuilder desc = new StringBuilder("");
		boolean isCorse1Fnsh = false;
		boolean isCorse2Fnsh = false;
		boolean isCorse3Fnsh = false;
		// 必修课程1
		String crclid = "1";
		String sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid = '" + crclid
				+ "' and t.stdy_st='1'";
		List<T5CrclStdy> t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 课程已修;
			isCorse1Fnsh = true;
		}
		// 必修课程2
		crclid = "'21', '22', '23', '24','25', '26', '27'";
		sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid in(" + crclid
				+ ") and t.stdy_st='1'";
		t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 课程已修;
			isCorse2Fnsh = true;
		}
		// 必修课程3
		crclid = "'31', '32', '33', '34','35', '36', '37'";
		sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid in(" + crclid
				+ ") and t.stdy_st='1'";
		t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 课程已修;
			isCorse3Fnsh = true;
		}
		if (isCorse1Fnsh && isCorse2Fnsh && isCorse3Fnsh) {
			res = new ResultEntity("0000", "课程学习完毕，可以参加考试!");
		} else {
			if (!isCorse1Fnsh)
				desc.append("‘必修课程1’");
			if (!isCorse2Fnsh)
				desc.append("‘必修课程2’");
			if (!isCorse3Fnsh)
				desc.append("‘必修课程3’");
			desc.append("没有完成学习，请完成后再参加考试！");
			res = new ResultEntity("0001", desc.toString());
		}
		renderJson(res);
		if(isCorse1Fnsh && isCorse2Fnsh && isCorse3Fnsh)
			return true;
		else
			return false;
	}
	
	// 提交考试，判定对错，记录题目记录，考试成绩
	// zhuchaobin
	@Clear
	public void submitExam() {
		// // 处理结果
		// ResultEntity res = null;
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		String examid = getPara("examid");
		String[] ds = getParaValues("dataSet");
		T10ExamGrd t10 = new T10ExamGrd();
		t10.setUsrid(usrid);
		t10.setExamid(Integer.parseInt(examid));
		// 总成绩
		Integer score = 0;
		for (String id : ds) {
			Integer pos = id.indexOf(":");
			// 题号
			String prblmno = id.substring(0, pos);
			String usr_aswr = "";
			if (id.length() > pos)
				usr_aswr = id.substring(pos + 1);
			// 查询答案
			t10.setPrblmno(Integer.parseInt(prblmno));
			String sql = "select * from t10_exam_grd t where t.usrid = '" + t10.getUsrid() + "' and t.examid ='"
					+ t10.getExamid() + "' and t.prblmno = '" + t10.getPrblmno() + "'";
			List<T10ExamGrd> t10List = T10ExamGrd.dao.find(sql);
			if ((null == t10List) || (0 == t10List.size())) {
				LOG.error("试题不在成绩表中，请确认");
			} else {
				LOG.debug("判断答案是否正确");
				T10ExamGrd t10Data = t10List.get(0);
				String answer = t10Data.getPrblm_aswr();
				// 答案比对
				boolean isRight = true;
				if (!StringUtils.isBlank(answer)) {
					// 答案其格式化，去掉多余字符
					answer = answer.replace(",", "");
					answer = answer.replace("'", "");
					answer = answer.replace("、", "");
					if (answer.length() == usr_aswr.length()) {
						for (int i = 0; i < usr_aswr.length(); i++) {
							String option = usr_aswr.substring(i, i + 1);
							if (-1 == answer.indexOf(option)) {
								isRight = false;
							}
						}
					} else {
						isRight = false;
					}
				} else {
					LOG.error("查询不到答案，无法比对是否正确");
				}
				if (isRight) {
					score++;
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
		// 更新成绩统计表
		Integer totalScore = score * 5;
		T11ExamStat t11 = new T11ExamStat();
		t11.setUsrid(usrid);// 用户id
		t11.setExamid(Integer.parseInt(examid));// 考试id
		t11.setExam_grd(totalScore);// 考试成绩
		t11.setExam_st("1");// 考试状态
		t11.setExam_channel("01");// 考试渠道,01:互联网站
		t11.setExam_num(Integer.parseInt(examid));// 考试次数
		t11.setTms(new Timestamp(System.currentTimeMillis()));// 维护时间
		t11.setExam_nm("省运会");
		t11.saveGenIntId();
		// float fscore = (float) (score * 100.0 / 6.0);
		ResultEntity res = new ResultEntity("0000", "恭喜您！您已完成测试，您的成绩为：" + score * 5 + "分！");
		// renderWithPath("/f/study.html");
		renderJson(res);
	}

	/**
	 * 保存
	 */
	@Before(T7CrclValidator.class)
	public void save() {
		T7Crcl t7Crcl = getModel(T7Crcl.class);
		// other set

		// t7Crcl.save(); //guiid
		t7Crcl.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T7Crcl t7Crcl = T7Crcl.dao.findById(getPara()); //guuid
		T7Crcl t7Crcl = T7CrclService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t7Crcl", t7Crcl);
		renderWithPath(pthv + "update.html");

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
		// T7Crcl t7Crcl = T7Crcl.dao.findById(getPara()); //guuid
		T7Crcl t7Crcl = T7CrclService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t7Crcl", t7Crcl);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T7CrclService.service.delete("t7_crcl", getPara() == null ? ids : getPara());
		// //guuid
		T7CrclService.service.deleteById("t7_crcl", getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

	public static void main(String[] args) throws Exception {
		// String str = "MOD13Q1.A2001033.h00v08.006.2015141152020.hdf";
		// String[] fileAttr = str.split("\\.");
		// System.out.println(fileAttr.length);
		// for(int i = 0; i < 100; i ++)
		// System.out.println(getRadSix());

		/*
		 * String msgTemplate = ParamUtils.getParam("10000002", "001");
		 * msgTemplate.replace("$1", "杨涛"); msgTemplate.replace("$2", getRadSix());
		 * sendTextMail("nanjing2007@163.com", "泰国农业遥感系统找回密码", msgTemplate);
		 */

		String path = "fdsfds.jpg";
		String rlt = path.substring(0, path.length() - 4) + "_ed.jpg";
		System.out.println(rlt);
	}

}
