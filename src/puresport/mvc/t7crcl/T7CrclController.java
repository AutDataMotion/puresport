package puresport.mvc.t7crcl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import puresport.config.ConfMain;
import puresport.constant.EnumCompetition;
import puresport.entity.ExamEntity;
import puresport.entity.ResultEntity;
import puresport.mvc.t10examgrd.T10ExamGrd;
import puresport.mvc.t11examstat.T11ExamStat;
import puresport.mvc.t11examstat.T11ExamStatService;
import puresport.mvc.t12highestscore.T12HighestScore;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t5crclstdy.T5CrclStdy;
//import puresport.entity.ExamEntity;
import puresport.mvc.t9tstlib.T9Tstlib;
import puresport.mvc.t14invitationcode.T14InvitationCode;
import puresport.mvc.t17creditInf.T17CreditInf;
import puresport.mvc.pages.FunctionInterceptor;

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
	 * 描述：学习说明
	 * 
	 * @author zhuchaobin 2018-06-03
	 */
	public void study_notify_1() {
		// 判断是省运会、亚运会、青奥会
		String which_competition = getPara("which_competition");
		if (StringUtils.isBlank(which_competition)) {
			getSession().setAttribute("which_competition", which_competition);// 设置session，保存
			renderWithPath("/f/zhunru_index.html");
			return;
		}
		if (which_competition.equals(EnumCompetition.ShengYunHui.getIndex_str())) {
			getSession().setAttribute("which_competition", EnumCompetition.ShengYunHui.getCompetitionName());
		} else if (which_competition.equals(EnumCompetition.YaYunHui.getIndex_str())) {
			getSession().setAttribute("which_competition", EnumCompetition.YaYunHui.getCompetitionName());
		} else if (which_competition.equals(EnumCompetition.QingAoHui.getIndex_str())) {
			getSession().setAttribute("which_competition", EnumCompetition.QingAoHui.getCompetitionName());
		} else if (which_competition.equals(EnumCompetition.JunYunHui.getIndex_str())) {
			getSession().setAttribute("which_competition", EnumCompetition.JunYunHui.getCompetitionName());
		}

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
	 * 描述：东京奥运会学习说明
	 * 
	 * @author zhuchaobin 2019-10-20
	 */
	public void study_notify_tokyo_1() {
		// 判断是省运会、亚运会、青奥会
		String which_competition = getPara("which_competition");
		System.out.println("study_notify_tokyo_1 which_competition=" + which_competition);
		if (StringUtils.isBlank(which_competition)) {
			renderWithPath("/f/zhunru_index_pre.html");
			return;
		}
		if (which_competition.equals(EnumCompetition.DongJingAoYunHui.getIndex_str())) {
			getSession().setAttribute("which_competition", EnumCompetition.DongJingAoYunHui.getCompetitionName());
		}

		Integer usrid = Integer.parseInt(getSession().getAttribute("usrid") + "");
		System.out.println(usrid);
		/*
		 * List<T7Crcl> t7List = queryCrcl(1, usrid); setAttr("t7", t7List.get(0));
		 * LOG.debug("crclid = " + t7List.get(0).getCrclid());
		 */
		getSession().setAttribute("which_competition", "东京奥运会");// 设置session，保存为东京奥会
		renderWithPath("/f/accession/study_notify_tokyo_1.html");
	}

	/**
	 * 描述：东京奥运会学习
	 * 
	 * @author zhuchaobin 2019-10-20
	 */
	public void course_list_tokyo_2() {
		System.out.println("course_list_tokyo_2");
		Integer usrid = Integer.parseInt(getSession().getAttribute("usrid") + "");
		System.out.println(usrid);
		List<T7Crcl> t7List = queryCrcl(5, usrid);
		// if(null != t11List && t11List.size() > 0
		// 查询考试情况,来源t11,判定当前各课程考试情况
		String sql = "select * from t11_exam_stat t where  t.type = '05' and t.usrid = '" + usrid
				+ "'order by t.category desc";
		List<T11ExamStat> t11List = T11ExamStat.dao.find(sql);
		List<T7Crcl> t7ListRlt = new ArrayList<T7Crcl>();
		Integer unLockCatagory = 1;
		for (T7Crcl t7 : t7List) {
			// 解锁
			// 查询考试次数及最高成绩
			Integer examedNum = 0;
			Integer hightestScore = 0;
			for (T11ExamStat t11 : t11List) {
				if (t11.getCategory().equals(t7.getCategory())) {
					if (t11.getExam_st().equals("9"))
						hightestScore = Integer.parseInt(t11.getExam_grd());
					else
						examedNum++;
					if (unLockCatagory < (Integer.parseInt(t11.getCategory()) + 1))
						unLockCatagory = Integer.parseInt(t11.getCategory()) + 1;
				}
			}
			String canDoColor = "#0065AC";
			String forbiddenColor = "#D87C31";
			String lockColor = "#707070";
			if (examedNum > 0 && examedNum < 3) {
				String testRltDesc = "您已考试" + examedNum + "次，取得学分:" + hightestScore + "分！";
				t7.setTestRltDesc(testRltDesc);
				t7.setTestDisabled("");
				t7.setTestColor(canDoColor);
				t7.setTestLockIcon("fa fa-unlock");
				t7.setTestTitle("重新考试");
				t7.setTestUrl("/course/scormcontent/index.html");
				System.out.println(testRltDesc);
			} else if (examedNum >= 3) {
				String testRltDesc = "您已考试" + examedNum + "次，次数达上限。取得学分:" + hightestScore + "分！";
				t7.setTestRltDesc(testRltDesc);
				t7.setTestDisabled("disabled=\"disabled\"");
				t7.setTestColor(forbiddenColor);
				t7.setTestLockIcon("fa fa-ban");
				t7.setTestTitle("无法再考");
				t7.setTestUrl("/course/scormcontent/index.html");
				System.out.println(testRltDesc);
			} else {
				String testRltDesc = "您尚未参加考试！";
				t7.setTestRltDesc(testRltDesc);
				if (Integer.parseInt(t7.getCategory()) == unLockCatagory) {
					t7.setTestDisabled("");
					t7.setTestColor(canDoColor);
					t7.setTestLockIcon("fa fa-unlock");
					t7.setTestTitle("点击进入考试");
				} else if (Integer.parseInt(t7.getCategory()) > unLockCatagory) {
					t7.setTestDisabled("disabled=\"disabled\"");
					t7.setTestColor(lockColor);
					t7.setTestLockIcon("fa fa-lock");
					t7.setTestTitle("尚未解锁");
				}
				t7.setTestUrl("/course/scormcontent/index.html");
				System.out.println(testRltDesc);
			}

			// 设置课程
			if (Integer.parseInt(t7.getCategory()) <= unLockCatagory) {
				t7.setCourseDisabled("");
				t7.setCourseColor(canDoColor);
				t7.setCourseLockIcon("fa fa-unlock");
				t7.setCourseTitle("点击进入该课程学习");
				t7.setCourseUrl("/course/scormcontent/index.html");
			} else {
				// 未解锁
				t7.setCourseDisabled("disabled=\"disabled\"");
				t7.setCourseColor(lockColor);
				t7.setCourseLockIcon("fa fa-lock");
				t7.setCourseTitle("该课程尚未解锁，请顺序参加先修课程学习并考试!");
			}
		}

		setAttr("t7", t7List);
		LOG.debug("t7List.size() = " + t7List.size());
		renderWithPath("/f/accession/course_list_tokyo_2.html");
		// renderWithPath("/f/accession/tokyo/course/scormcontent/index.html");
	}

	/**
	 * 描述：查询证书
	 * 
	 * @author zhuchaobin 2019-11-10
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Clear
	public void queryCredit() throws URISyntaxException, IOException {
		Integer usrid = Integer.parseInt(getSession().getAttribute("usrid") + "");
		System.out.println(usrid);
		String creditNo = getPara("credit_no");
		String crdtNo = getPara("crdt_no");
		String certificatePath_1 = "";
		String certificatePath_2 = "";
		// // 处理结果
		ResultEntity res = null;

		String path = Class.class.getResource("/").toURI().getPath();
		String filepath = new File(path).getParentFile().getParentFile().getCanonicalPath();

		if (StringUtils.isNotBlank(crdtNo)) {
			T1usrBsc t1 = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where crdt_no=?", crdtNo);// 根据用户证件号查询数据库中的用户
			if (null != t1) {
				certificatePath_1 = "/images_zcb/certificates/" + "反兴奋剂教育准入合格证书_" + t1.getNm() + "_" + t1.getUsrid()
						+ ".jpg";
				if (!isFileExsit(filepath + certificatePath_1))
					certificatePath_1 = "";
			}
		}
		if (StringUtils.isNotBlank(creditNo)) {
			certificatePath_2 = "/images_zcb/certificates/" + creditNo + ".jpg";
			if (!isFileExsit(filepath + certificatePath_2))
				certificatePath_2 = "";
		}

		if (StringUtils.isNotBlank(certificatePath_1) && StringUtils.isNotBlank(certificatePath_2)) {
			if (certificatePath_1.equals(certificatePath_2)) {
				// 查询到，返回
				// setAttr("certificatePath", certificatePath_1);
				res = new ResultEntity("2000", "查询证书成功!", certificatePath_1, certificatePath_2, "");
			} else {
				// 条件组合后，没查到
				res = new ResultEntity("0001", "查询证书失败!", "", "", "");
			}
		} else if (StringUtils.isNotBlank(certificatePath_1)) {
			// 查询到返回certificatePath_1
			// setAttr("certificatePath", certificatePath_1);
			res = new ResultEntity("1000", "查询证书成功!", certificatePath_1, "", "");
		} else if (StringUtils.isNotBlank(certificatePath_2)) {
			// 查询到返回certificatePath_2
			// setAttr("certificatePath", certificatePath_2);
			res = new ResultEntity("1000", "查询证书成功!", certificatePath_2, "", "");
		} else {
			// 没查询到证书
			res = new ResultEntity("0001", "查询证书失败!", "", "", "");
		}
		renderJson(res);
		return;
	}

	/**
	 * 描述：展示证书
	 * 
	 * @author zhuchaobin 2019-11-10
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Clear
	public void showCredit() {
		String certificatePath_1 = getPara("certificatePath_1");
		String certificatePath_2 = getPara("certificatePath_2");
		LOG.debug("certificatePath=" + certificatePath_1);
		setAttr("certificatePath", certificatePath_1);
		renderWithPath("/f/accession/showCredit.html");
	}

	/**
	 * 描述：文件是否存在
	 * 
	 * @author zhuchaobin 2019-06-03
	 * @throws URISyntaxException
	 */
	@Clear
	public boolean isFileExsit(String filePathDir) {
		try {
			File file = new File(filePathDir);
			if (!judeFileExists(file)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 描述：查询证书
	 * 
	 * @author zhuchaobin 2018-06-03
	 * @throws URISyntaxException
	 */
	@Clear
	public void queryCetifate() {
		String flag = getPara("flag");
		// 考试不及格的情况
		if ("2".equals(flag)) {
			setAttr("totalScore", getPara("totalScore"));
			renderWithPath("/f/accession/failed.html");
			return;
		}
		String usrid = getPara("usrid");
		setAttr("usrid", usrid);
		String certificatePath = "";
		// 查询用户信息
		T1usrBsc t1 = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", usrid);// 根据用户名查询数据库中的用户
		if (t1 == null) {
			LOG.error("查询用户信息失败.");
			setAttr("certificatePath", "/images_zcb/certificates/certificateDefault.jpg");
			LOG.debug("certificatePath=" + certificatePath);
			setAttr("class", "");
			renderWithPath("/f/accession/certificate.html");
			return;
		} else {
			setAttr("pageHead", "反兴奋剂教育准入合格证书-" + t1.getNm());
			/*
			 * // 取身份证号码第1位+ 最后1位 String crdt_no_endStr = ""; if
			 * (!StringUtils.isBlank(crdt_no)) { crdt_no_endStr = crdt_no.substring(0, 1) +
			 * crdt_no.substring(crdt_no.length() - 2, crdt_no.length() - 1); }
			 */
			certificatePath = "/images_zcb/certificates/" + "反兴奋剂教育准入合格证书_" + t1.getNm() + "_" + usrid + ".jpg";
			setAttr("certificatePath", certificatePath);
		}
		// 判定证书文件是否存在,不存在则返回默认未取得证书路径
		// 是否有证书
		boolean isCertificated = false;
		try {
			// 优先查询新版证书，是否存在
			String sql = "select * from t17_credit_inf t where t.usrid = '" + usrid + "' and t.flag ='01'";
			T17CreditInf t17Rlt = T17CreditInf.dao.findFirst(sql);
			if(null != t17Rlt) {
				certificatePath = t17Rlt.getFile_path();
				setAttr("certificatePath", certificatePath);
				setAttr("class", "img-thumbnail");
				isCertificated = true;
			} else {// 查询旧版证书是否存在			
				String path = Class.class.getResource("/").toURI().getPath();
				String filepath = new File(path).getParentFile().getParentFile().getCanonicalPath();
				File file = new File(filepath + certificatePath);
				if (!judeFileExists(file)) {
					setAttr("certificatePath", "/images_zcb/certificates/certificateDefault.jpg");
					setAttr("class", "");
				} else {
					isCertificated = true;
					setAttr("class", "img-thumbnail");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 是否参加过考试
		boolean isExamed = false;
		// 查询个人排名
		String sql = "select * from t11_exam_stat t where  t.exam_st = '9' order by exam_grd desc";
		List<T11ExamStat> t11List = T11ExamStat.dao.find(sql);
		int mingci = 0;
		for (T11ExamStat t11 : t11List) {
			mingci++;
			if (t11.getUsrid().equals(t1.getUsrid())) {
				isExamed = true;
				break;
			}
		}
		// 根据是否有证书和是否参加过考试，决定显示效果
		if (isCertificated) {
			setAttr("shareDisplay", "inline");
			setAttr("rankDisplay", "inline");
			setAttr("disappointDisplay", "inline");
		} else if (isExamed) {
			setAttr("rankDisplay", "inline");
			setAttr("disappointDisplay", "inline");
		} else {
			setAttr("startDisplay", "inline");
		}
		setAttr("mingci", mingci);
		setAttr("totalExamer", t11List.size());
		double percent = (t11List.size() + 1 - mingci) * 1.0 / (t11List.size());
		// 最后一名特殊处理
		if (t11List.size() == mingci) {
			percent = 0.0;
		}
		NumberFormat nf = java.text.NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(1);// 小数点后保留几位
		LOG.info(percent);
		String koPercent = nf.format(percent);
		setAttr("koPercent", koPercent);
		LOG.debug("certificatePath=" + certificatePath);
		renderWithPath("/f/accession/certificate.html");
	}

	/**
	 * 描述：查询证书
	 * 
	 * @author zhuchaobin 2019-10-27
	 * @throws URISyntaxException
	 */
	@Clear
	public void queryCetifate_tokyo_5() {
		String flag = getPara("flag");
		String category = getPara("category");
		// 考试不及格的情况
		if ("2".equals(flag)) {
			setAttr("totalScore", getPara("totalScore"));
			renderWithPath("/f/accession/failed_tokyo_5.html");
			return;
		}
		String usrid = getPara("usrid");
		setAttr("usrid", usrid);
		String certificatePath = "";
		// 查询用户信息
		T1usrBsc t1 = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", usrid);// 根据用户名查询数据库中的用户
		if (t1 == null) {
			LOG.error("查询用户信息失败.");
			setAttr("certificatePath", "/images_zcb/certificates/certificateDefault.jpg");
			LOG.debug("certificatePath=" + certificatePath);
			renderWithPath("/f/accession/certificate.html");
			return;
		} else {
			setAttr("pageHead", "反兴奋剂教育准入合格证书-" + t1.getNm());
			/*
			 * // 取身份证号码第1位+ 最后1位 String crdt_no_endStr = ""; if
			 * (!StringUtils.isBlank(crdt_no)) { crdt_no_endStr = crdt_no.substring(0, 1) +
			 * crdt_no.substring(crdt_no.length() - 2, crdt_no.length() - 1); }
			 */
//			certificatePath = "/images_zcb/certificates/" + "反兴奋剂教育准入合格证书_" + t1.getNm() + "_" + usrid + ".jpg";
			
			String sql = "select * from t17_credit_inf t where t.usrid = '" + usrid + "' and t.flag ='01'";
			T17CreditInf t17Rlt = T17CreditInf.dao.findFirst(sql);
			if(null != t17Rlt) {
				certificatePath = t17Rlt.getFile_path();
			} 
	
			setAttr("certificatePath", certificatePath);
		}
		// 判定证书文件是否存在,不存在则返回默认未取得证书路径
		// 是否有证书
		boolean isCertificated = false;
		try {
			String path = Class.class.getResource("/").toURI().getPath();
			String filepath = new File(path).getParentFile().getParentFile().getCanonicalPath();
			File file = new File(filepath + certificatePath);
			if (!judeFileExists(file)) {
				setAttr("certificatePath", "/images_zcb/certificates/certificateDefault.jpg");
			} else {
				isCertificated = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 是否参加过考试
		boolean isExamed = false;
		// 查询个人排名
		String sql = "select * from t11_exam_stat t where  t.exam_st = '9' and t.type ='05' order by exam_grd desc";
		List<T11ExamStat> t11List = T11ExamStat.dao.find(sql);
		int mingci = 0;
		for (T11ExamStat t11 : t11List) {
			mingci++;
			if (t11.getUsrid().equals(t1.getUsrid())) {
				isExamed = true;
				break;
			}
		}
		// 根据是否有证书和是否参加过考试，决定显示效果
		if (isCertificated) {
			setAttr("shareDisplay", "inline");
			setAttr("rankDisplay", "inline");
			setAttr("disappointDisplay", "inline");
		} else if (isExamed) {
			setAttr("rankDisplay", "inline");
			setAttr("disappointDisplay", "inline");
		} else {
			setAttr("startDisplay", "inline");
		}
		setAttr("mingci", mingci);
		setAttr("totalExamer", t11List.size());
		double percent = (t11List.size() + 1 - mingci) * 1.0 / (t11List.size());
		// 最后一名特殊处理
		if (t11List.size() == mingci) {
			percent = 0.0;
		}
		NumberFormat nf = java.text.NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(1);// 小数点后保留几位
		LOG.info(percent);
		String koPercent = nf.format(percent);
		setAttr("koPercent", koPercent);
		LOG.debug("certificatePath=" + certificatePath);
		renderWithPath("/f/accession/certificate_tokyo.html");
	}

	/**
	 * 描述：根据课程id，获取课程信息
	 * 
	 * @author zhuchaobin 2018-06-08 @throws
	 */
	public T7Crcl getCrclInfo(String crclid) {
		T7Crcl t7 = null;
		if (StringUtils.isNotBlank(crclid)) {
			t7 = T7CrclService.service.SelectById(Integer.parseInt(crclid));
		} else {
			LOG.error("课程编号为空，查询课程信息失败！");
		}
		if (null == t7) {
			LOG.debug("根据课程id查询到课程信息为空，课程id=" + crclid);
		}
		return t7;
	}

	/**
	 * 描述：高分榜
	 * 
	 * @author zhuchaobin 2018-06-05
	 * @throws URISyntaxException
	 */
	@Clear
	public void heroList() {
		String crdt_no = getPara("crdt_no");
		setAttr("crdt_no", crdt_no);
		String certificatePath = "";

		String useridStr = (String) getSession().getAttribute("usrid");
		// 插入或者更新成绩统计表最后一次成绩
		String sql = "select t.*, r.nm, r.spt_prj, r.province, (case r.city when '--' then '' when '-' then '' else r.city end) as city from t11_exam_stat t "
				+ "JOIN t1_usr_bsc r on t.exam_st = '9' and t.usrid = r.usrid order by exam_grd desc, tms asc limit 10 ";
		List<T11ExamStat> heroList = T11ExamStat.dao.find(sql);
		List<T11ExamStat> heroListRlt = new ArrayList<T11ExamStat>();
		// 名次，名次缩略图赋值
		for (int i = 0; i < 10; i++) {
			T11ExamStat t11 = new T11ExamStat();
			String rankImg = "rank" + (i + 1) + ".png";
			if (i < heroList.size()) {
				t11 = heroList.get(i);
			} else {
				;
			}
			t11.setRankImg(rankImg);
			LOG.debug("t11.getUsrid()" + t11.getUsrid());
			if (null != useridStr) {
				if (useridStr.equals((t11.getUsrid() + ""))) {
					t11.setRank("#FF0202");
				}
			}

			// // 默认city没有的话，默认值是“--”，特殊处理
			// if(t11.getCity().toString().contains("-")) {
			// t11.setCity(null);
			// }
			heroListRlt.add(t11);
		}
		setAttr("heroList", heroListRlt);
		renderWithPath("/f/accession/hero_list.html");
	}

	// 判断文件是否存在
	public boolean judeFileExists(File file) {

		if (file.exists()) {
			return true;
		} else {
			System.out.println(file + ":file not exists...");
			return false;
		}

	}

	/**
	 * 描述：视频播放
	 * 
	 * @author zhuchaobin 2018-05-09
	 */
	/* @Clear */
	public void video_play() {
		/*
		 * Integer usrid = Integer.parseInt((String)
		 * getSession().getAttribute("usrid"));
		 */
		// 实时检查学习状态
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		Integer crcl_flag = 0;

		String crcl_attr = getPara("crcl_attr");
		String crclid = getPara("crclid");
		String crcl_file_rte = getPara("crcl_file_rte");
		// String stdy_st = getPara("stdy_st");
		System.out.println(crcl_attr);
		System.out.println(crcl_file_rte);
		if ("1".equals(crcl_attr)) {
			setAttr("action", "/jf/puresport/t7Crcl/video2_select_3");// 必修视频2
			setAttr("pre_action", "/jf/puresport/t7Crcl/study_notify_1");// 必修课程1
			setAttr("crcl_nm", "必修课程一：" + getCrclInfo(crclid).getCrcl_nm());// 课程名称
			/*
			 * setAttr("crcl_brf", getCrclInfo(crclid).getCrcl_brf());// 课程简介
			 */
			/*
			 * // 必修课程1 String sql = "select * from t7_crcl t where t.crclid='" +
			 * getPara("crclid") + "'"; List<T7Crcl> t7List = T7Crcl.dao.find(sql); if
			 * ((t7List != null) && (t7List.size() > 0)) { // 去视频1 setAttr("crcl_nm",
			 * "必修课程一：" + getCrclInfo(crclid).getCrcl_nm());// 课程名称 setAttr("crcl_brf",
			 * t7List.get(0).getCrcl_brf());// 课程简介 }
			 */
			// setAttr("stdy_st", stdy_st);// 必修视频2
			crcl_flag = 1;
		} else if ("2".equals(crcl_attr)) {// 去视频2
			setAttr("action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3
			setAttr("pre_action", "/jf/puresport/t7Crcl/video2_select_3");// 必修视频2
			setAttr("crcl_nm", "必修课程二：" + getCrclInfo(crclid).getCrcl_nm());// 课程名称
			crcl_flag = 2;
		} else if ("3".equals(crcl_attr)) {// 去视频3
			setAttr("crcl_nm", "必修课程三：" + getCrclInfo(crclid).getCrcl_nm());// 课程名称
			setAttr("action", "/jf/puresport/t7Crcl/generteTest");// 生成考试
			setAttr("pre_action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3
			crcl_flag = 3;
		}

		List<T7Crcl> t7List = queryCrcl(crcl_flag, usrid);
		setAttr("t7List", t7List);
		setAttr("course_title", "案例篇选学（选择一篇完成观看）");// 课程标题
		// 检查课程是否完成，只要有一门完成即可
		for (T7Crcl t7 : t7List) {
			if ("1".equals(t7.getStdy_st())) {
				setAttr("stdy_st_hidden", "1");
				// setAttr("action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3
				break;
			}
		}

		setAttr("crcl_file_rte", crcl_file_rte);
		// setAttr("stdy_st_hidden", getPara("stdy_st_hidden"));// 本课程学习状态
		setAttr("crclid", crclid);// 课程id
		/*
		 * if (!("1".equals(crcl_attr))) { setAttr("crcl_brf", getPara("crcl_brf"));//
		 * 课程简介 }
		 */
		LOG.debug("crclid = " + crclid);
		renderWithPath("/f/accession/video_play.html");
	}

	/**
	 * 描述：视频2选择_3
	 * 
	 * @author zhuchaobin 2018-05-09
	 */
	public void video2_select_3() {
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		List<T7Crcl> t7List = queryCrcl(2, usrid);
		setAttr("t7List", t7List);
		setAttr("course_title", "案例篇选学（选择一篇完成观看）");// 课程标题
		// 检查课程是否完成，只要有一门完成即可
		for (T7Crcl t7 : t7List) {
			if ("1".equals(t7.getStdy_st())) {
				setAttr("stdy_st_hidden", "1");
				setAttr("action_hidden", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3
				setAttr("pre_action", "/jf/puresport/t7Crcl/video_play?crcl_attr=1&crcl_file_rte=258767799&crclid=1");// 准入必修课程1
				// setAttr("action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3
				break;
			}
		}
		renderWithPath("/f/accession/video2_select_3.html");
	}

	/**
	 * 描述：视频3选择_5
	 * 
	 * @author zhuchaobin 2018-05-09
	 */
	public void video3_select_5() {
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		List<T7Crcl> t7List = queryCrcl(3, usrid);
		setAttr("t7List", t7List);
		setAttr("course_title", "流程篇选学（选择一篇完成观看）");// 课程标题
		// 检查课程是否完成，只要有一门完成即可
		for (T7Crcl t7 : t7List) {
			if ("1".equals(t7.getStdy_st())) {
				setAttr("stdy_st_hidden", "1");
				// setAttr("action", "/jf/puresport/t7Crcl/generteTest");// 生成考试
				setAttr("action_hidden", "/jf/puresport/t7Crcl/generteTest");// 生成考试
				break;
			}
		}
		setAttr("pre_action", "/jf/puresport/t7Crcl/video2_select_3");// 准入学习说明页
		renderWithPath("/f/accession/video2_select_3.html");
	}

	/**
	 * 描述：课程信息查询
	 * 
	 * @author zhuchaobin 2018-05-12
	 */
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
		List<T7Crcl> t7List5 = new ArrayList<T7Crcl>();
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

				else if ("5".equals(t7.getCrcl_attr())) { // 冬奥会
					t7List5.add(t7);
				}
			}
			setAttr("t7List1", t7List1);
			setAttr("t7List2", t7List2);
			setAttr("t7List3", t7List3);
			setAttr("t7List4", t7List4);
			setAttr("t7List5", t7List5);
			if (1 == flag) {
				return t7List1;
			} else if (2 == flag) {
				return t7List2;
			} else if (3 == flag) {
				return t7List3;
			} else if (5 == flag) {
				return t7List5;
			}
			LOG.debug("查询课程信息成功结束.");
		} else {
			LOG.error("查询课程信息失败！");
		}
		return null;
	}

	/**
	 * 描述：新浪视频测试
	 * 
	 * @author zhuchaobin 2018-06-07
	 */
	@Clear
	public void videoTest() {
		renderWithPath("/f/accession/playertest.html");
		/* renderWithPath("/f/accession/video_play.html"); */
	}

	/**
	 * 描述：从30道选择题中随机取10道，从30道判断题中随机取10道构成试卷。并保存到成绩记录表中。
	 * 
	 * @author zhuchaobin 2018-05-21
	 */
	@Before(FunctionInterceptor.class)
	public void generteTest() {
		// 判断赛事类型是否为空
		String which_competition = (String) getSession().getAttribute("which_competition");
		if (StringUtils.isBlank(which_competition)) {
			LOG.error("session中获取赛事名称获取为空");
			renderWithPath("/f/zhunru_index.html");
			return;
		} else {
			setAttr("which_competition", which_competition);
		}
		/*
		 * if (!isCanTest()) renderWithPath("/f/accession/dotest.html");
		 */
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));

		// List<T11ExamStat> T11ExamStat_num = null;
		/*
		 * if(which_competition.equals("青奥会")) { //
		 * 查询一共考了多少次，规则修改为青奥会考试次数不能超过3次，2018-09-04 List<T11ExamStat> T11ExamStat_num =
		 * T11ExamStat.dao.
		 * find("select * from t11_exam_stat t where t.exam_st = '1' and t.exam_nm = '青奥会' and t.usrid='"
		 * + usrid + "'"); if(T11ExamStat_num.size()>=3) {
		 * LOG.debug("generteTest----"+"总答题次数已满3次！！"); setAttr("tpsMsg",
		 * "对不起，每人最多只能答题三次。您已答题三次，不能再参加考试。"); renderWithPath("/f/tips.html"); return; }
		 * } else {//省运会、亚运会 //查询当天已经考试了几次 List<T11ExamStat> T11ExamStat_num =
		 * T11ExamStatService.service.SelectByUserIdAndTime(usrid,which_competition);
		 * if(T11ExamStat_num.size()>=3) { LOG.debug("generteTest----"+"今日答题次数已满！！");
		 * setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
		 * renderWithPath("/f/tips.html"); return; } }
		 */
		// 查询一共考了多少次，规则修改为不能超过3次，2019-10-30
		List<T11ExamStat> T11ExamStat_num = T11ExamStat.dao
				.find("select * from t11_exam_stat t where t.exam_st = '1' and t.usrid='" + usrid + "'  and t.exam_nm = '"+which_competition+"'" );
		if (T11ExamStat_num.size() >= 3) {
			LOG.debug("generteTest----" + "总答题次数已满3次！！");
			setAttr("tpsMsg", "对不起，每人最多只能答题三次。您已答题三次，不能再参加考试。");
			renderWithPath("/f/tips.html");
			return;
		}

		// 查询当天已经考试了几次
		// List<T11ExamStat> T11ExamStat_num =
		// T11ExamStatService.service.SelectByUserIdAndTime(usrid);
		// if(T11ExamStat_num.size()>=4)
		// {
		// LOG.debug("generteTest----"+"今日答题次数已满！！");
		// setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
		// renderWithPath("/f/tips.html");
		// } else {

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
		setAttr("pre_action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3选择
		renderWithPath("/f/accession/dotest.html");

		// }

	}

	/**
	 * 描述：检测是否具备考试条件
	 * 
	 * @author zhuchaobin 2018-05-23
	 */
	public boolean isCanTest() {
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
		crclid = "'21', '22', '23', '24','25'";
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
		if (isCorse1Fnsh && isCorse2Fnsh && isCorse3Fnsh)
			return true;
		else
			return false;
	}

	/**
	 * 描述：提交考试，判定对错，记录题目记录，考试成绩
	 * 
	 * @author zhuchaobin 2018-05-25
	 */
	@Before(FunctionInterceptor.class)
	public void submitExam() {

		String which_competition = (String) getSession().getAttribute("which_competition");

		String type = getPara("type");
		String category = getPara("category");

		System.out.println("type=" + type);
		System.out.println("category=" + category);

		// // 处理结果
		ResultEntity res = null;
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));

		if (which_competition.equals("青奥会")) {
			// 查询一共考了多少次，规则修改为青奥会考试次数不能超过3次，2018-09-04
			List<T11ExamStat> T11ExamStat_num = T11ExamStat.dao
					.find("select * from t11_exam_stat t where t.exam_st = '1' and t.exam_nm = '青奥会' and t.usrid='"
							+ usrid + "'");
			if (T11ExamStat_num.size() >= 3) {
				LOG.debug("generteTest----" + "总答题次数已满3次！！");
				setAttr("tpsMsg", "对不起，每人最多只能答题三次。您已答题三次，不能再参加考试。");
				renderWithPath("/f/tips.html");
				return;
			}
		} else if (which_competition.equals("东京奥运会")) {
			// 查询一共考了多少次，没门课程不超过3次
			List<T11ExamStat> T11ExamStat_num = T11ExamStat.dao.find("select * from t11_exam_stat t where t.type = '"
					+ type + "' and t.category = '" + category + "' and t.usrid='" + usrid + "' and t.exam_st != '9'");
			if (T11ExamStat_num.size() >= 3) {
				LOG.debug("generteTest----" + "总答题次数已满3次！！");
				setAttr("tpsMsg", "对不起，该课程只能答题三次。您已答题三次，不能再参加考试。");
				renderWithPath("/f/tips.html");
				return;
			}
		} else {// 省运会、亚运会
			// 查询当天已经考试了几次
			List<T11ExamStat> T11ExamStat_num = T11ExamStatService.service.SelectByUserIdAndTime(usrid,
					which_competition);
			if (T11ExamStat_num.size() >= 3) {
				LOG.debug("generteTest----" + "今日答题次数已满！！");
				setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
				renderWithPath("/f/tips.html");
				return;
			}
		}

		// 查询一共考了多少次，规则修改为所有赛事都考试次数不能超过3次，2018-09-04
		// List<T11ExamStat> T11ExamStat_num = T11ExamStat.dao.find("select * from
		// t11_exam_stat t where t.exam_st = '1' and t.usrid='" + usrid + "'");
		// if(T11ExamStat_num.size()>=3)
		// {
		// LOG.debug("generteTest----"+"总答题次数已满3次！！");
		// setAttr("tpsMsg", "对不起，每人最多只能答题三次。您已答题三次，不能再参加考试。");
		// renderWithPath("/f/tips.html");
		// }

		// 查询当天已经考试了几次
		/*
		 * List<T11ExamStat> T11ExamStat_num =
		 * T11ExamStatService.service.SelectByUserIdAndTime(usrid);
		 * if(T11ExamStat_num.size()>=4) { LOG.debug("submitExam----"+"今日答题次数已满！！");
		 * setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
		 * renderWithPath("/f/tips.html"); }
		 */

		// else {

		String examid = getPara("examid");
		String[] ds = getParaValues("dataSet");
		// 承诺人姓名
		String commimentNm = getPara("commimentNm");
		// 承诺人姓名全拼
		String commimentPinyin = getPara("commimentPinyin");
		// 查询用户信息
		T1usrBsc t1 = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", usrid);// 根据用户名查询数据库中的用户
		if (t1 == null) {
			LOG.error("查询用户信息失败，无法提交考试成绩.");
			res = new ResultEntity("0001", "查询用户信息失败，无法提交考试成绩.");
			renderJson(res);
			return;
		} else {
			if (commimentNm.equals(t1.getNm())) {
				LOG.debug("commimentNm= " + commimentNm + "用户姓名= " + t1.getNm() + "承诺人姓名无误.");
			} else {
				LOG.error("commimentNm= " + commimentNm + "用户姓名= " + t1.getNm() + "承诺人姓名与用户姓名不一致，无法提交考试成绩.");
				res = new ResultEntity("0002", "承诺人姓名与用户姓名不一致，无法提交考试成绩!");
				renderJson(res);
				return;
			}
			// 2019-11-30 朱朝彬，承诺人姓名全拼校验 
			if (StringTools.isIgnoreEqueal(commimentPinyin, t1.getNmChar())) {
				LOG.debug("commimentPinyin= " + commimentPinyin + "用户姓名拼音= " + t1.getNmChar() + "承诺人姓名拼音无误.");
			} else {
				LOG.error("commimentPinyin= " + commimentPinyin + "用户姓名拼音= " + t1.getNmChar() + "承诺人姓名拼音与用户姓名拼音不一致，无法提交考试成绩.");
				res = new ResultEntity("0002", "承诺人姓名拼音与用户姓名拼音不一致，无法提交考试成绩!");
				renderJson(res);
				return;
			}
			
		}

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
				int prblmId = Integer.parseInt(t10Data.getPrblmid());
				System.out.println("prblmId=" + prblmId);
				if (isRight) {
					score++;
					t10Data.setExam_grd(5);
					t10Data.setResult("正确");
					// 更新答对次数
					ConfMain.db().update("update t13_tst_stat t set t.right_num = t.right_num + 1 where prblmid ="
							+ prblmId);
				} else {
					t10Data.setExam_grd(0);
					t10Data.setResult("错误");
					// 更新答对次数

					ConfMain.db().update("update t13_tst_stat t set t.wrong_num = t.wrong_num + 1 where prblmid ="
							+ prblmId);
				}
				t10Data.setUsr_aswr(usr_aswr);
				t10Data.setExam_st("1");
				t10Data.setExamEndTm(new Timestamp(System.currentTimeMillis()));
				t10Data.setType(type);
				t10Data.setCategory(category);
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
		// 从session中获取赛事名称

		// String which_competition = (String)
		// getSession().getAttribute("which_competition");

		if (StringUtils.isBlank(which_competition)) {
			LOG.error("获取赛事名称失败！");
			which_competition = "";
		} else {
			LOG.debug("赛事名称：" + which_competition);
		}
		t11.setExam_nm(which_competition);
		t11.setType(type);
		t11.setCategory(category);
		t11.saveGenIntId();

		// 插入或者更新成绩统计表最后一次成绩
		String sql = "select * from t11_exam_stat t where t.usrid = '" + t10.getUsrid() + "' and t.exam_st = '9'";
		if ("东京奥运会".equals(which_competition)) {
			sql = "select * from t11_exam_stat t where t.usrid = '" + t10.getUsrid()
					+ "' and t.exam_st = '9' and t.category = '" + category + "' and t.type='05'";
		}
		T11ExamStat t11Rlt = T11ExamStat.dao.findFirst(sql);
		if (null == t11Rlt) {
			t11Rlt = new T11ExamStat();
			t11Rlt.setUsrid(usrid);// 用户id
			t11Rlt.setExamid(Integer.parseInt(examid));// 考试id
			t11Rlt.setExam_grd(totalScore);// 考试成绩
			t11Rlt.setExam_st("1");// 考试状态
			t11Rlt.setExam_channel("01");// 考试渠道,01:互联网站
			t11Rlt.setExam_num(Integer.parseInt(examid));// 考试次数
			t11Rlt.setTms(new Timestamp(System.currentTimeMillis()));// 维护时间
			t11Rlt.setExam_nm(which_competition);
			t11Rlt.setExam_st("9");// 考试状态，9表示最终成绩
			t11Rlt.setType(type);
			t11Rlt.setCategory(category);
			t11Rlt.saveGenIntId();
		} else {
			/*
			 * t11.setExam_st("9");// 考试状态，9表示最终成绩
			 * t11.setId(Long.parseLong(t11Rlt.getId()));
			 */
			// t11Rlt.setUsrid(usrid);// 用户id
			t11Rlt.setExamid(Integer.parseInt(examid));// 考试id
			if ("东京奥运会".equals(which_competition)) {
				if (totalScore < Integer.parseInt(t11Rlt.getExam_grd()))
					totalScore = Integer.parseInt(t11Rlt.getExam_grd());
			}
			t11Rlt.setExam_grd(totalScore);// 考试成绩
			t11Rlt.setExam_st("1");// 考试状态
			t11Rlt.setExam_channel("01");// 考试渠道,01:互联网站
			t11Rlt.setExam_num(Integer.parseInt(examid));// 考试次数
			t11Rlt.setTms(new Timestamp(System.currentTimeMillis()));// 维护时间
			t11Rlt.setExam_nm(which_competition);
			t11Rlt.setExam_st("9");// 考试状态，9表示最终成绩
			t11Rlt.setType(type);
			t11Rlt.setCategory(category);
			t11Rlt.update();
		}
		// 更新最高成绩
		if ("东京奥运会".equals(which_competition)) {
			// 计算东京奥运会最高成绩
			String sql2 = "select * from t11_exam_stat t where t.usrid='" + usrid
					+ "' and t.type='05' and t.exam_st='9'";
			List<T11ExamStat> t11List = T11ExamStat.dao.find(sql2);
			totalScore = 0;
			if (null != t11List && t11List.size() > 0) {
				for (T11ExamStat t11Ele : t11List) {
					totalScore += (Integer.parseInt(t11Ele.getExam_grd()));
				}
			}
		}

		// 插入或者更新成绩统计表最后一次成绩
		String sql_highest_score = "select * from t12_highest_score t where t.usrid = '" + t10.getUsrid()
				+ "' and t.exam_nm = '" + which_competition + "'";
		T12HighestScore t12 = T12HighestScore.dao.findFirst(sql_highest_score);
		if (null == t12) {
			t12 = new T12HighestScore();
			t12.setUsrid(usrid);// 用户id
			t12.setExamid(Integer.parseInt(examid));// 考试id
			t12.setExam_grd(totalScore);// 考试成绩
			t12.setExam_st("1");// 考试状态
			t12.setExam_channel("01");// 考试渠道,01:互联网站
			t12.setExam_num(Integer.parseInt(examid));// 考试次数
			t12.setTms(new Timestamp(System.currentTimeMillis()));// 维护时间
			t12.setExam_nm(which_competition);
			t12.setType(type);
			t12.saveGenIntId();
		} else {
			/*
			 * t11.setExam_st("9");// 考试状态，9表示最终成绩
			 * t11.setId(Long.parseLong(t11Rlt.getId()));
			 */
			// t11Rlt.setUsrid(usrid);// 用户id
			// 如果本次成绩高于已有最高成绩，则更新最高成绩
			if (totalScore > Integer.parseInt(t12.getExam_grd())) {
				// 转入操作
				ConfMain.db().update(
						"update t12_highest_score t set t.exam_grd = ? , t.tms = ? where usrid = ? and t.exam_nm = ?",
						totalScore, new Timestamp(System.currentTimeMillis()), t10.getUsrid(), which_competition);
			}
		}

		LOG.debug("totalScore=" + totalScore);
		// 东京奥运会积分制合格证书生成
		if ("东京奥运会".equals(which_competition)) {
			// 判断是否可以取得合格证书
/*						if (totalScore >= 80) {
						} else {
							res = new ResultEntity("0003", "考试成绩不合格.", totalScore.toString(), "", t1.getUsrid() + "");
							// setAttr("certificatePath", certificatePath);
							// renderWithPath("/f/accession/certificate.html");
							renderJson(res);
							return;
						}*/
						// ResultEntity res = new ResultEntity("0000", "恭喜您！您已完成测试，您的成绩为：" + toltalScore
						// + "分！");
						String certificatePath = "";
						Date date = new Date();
						DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
						String dataTime = format.format(date);
						// 获取工程路径
						String webContentPath = "";
						try {
							String path = Class.class.getResource("/").toURI().getPath();
							webContentPath = new File(path).getParentFile().getParentFile().getCanonicalPath();
							LOG.info("webContentPath=" + webContentPath);
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// DateFormat类的静态工厂方法
						System.out.println(format.getInstance().format(date));
						String srcImg = webContentPath + "\\images_zcb\\certificateTemplate01.jpg";
						DateFormat formatYear = new SimpleDateFormat("yyyy");
						String year = formatYear.format(date);
						// 生成证书编号
						String creditNo = "01" + year + String.format("%06d", Long.parseLong(t1.getUsrid()));
						certificatePath = "\\images_zcb\\certificates\\" + "反兴奋剂教育准入合格证书_" + t1.getNm() + creditNo + ".jpg";
						String dscImg = webContentPath + certificatePath;
						LOG.info("srcImg=" + srcImg);
						LOG.info("dscImg=" + dscImg);
						LOG.info("certificatePath=" + certificatePath);
						LOG.info("证书姓名"+ t1.getNm()+"("+StringTools.strConver(t1.getNmChar().toString().toLowerCase())+")");
						waterMark(t1.getNm()+"("+StringTools.strConver(t1.getNmChar().toString().toLowerCase())+")", srcImg, dscImg, 943, 2007, 180);
						waterMark(dataTime+"(yyyy/mm/dd)", dscImg, dscImg, 2230, 3407, 120);
						waterMark(which_competition, dscImg, dscImg, 1995, 3519, 120);
						waterMark(totalScore.toString(), dscImg, dscImg, 1911, 3619, 120);
						waterMark(creditNo, dscImg, dscImg, 1655, 3763, 120);
						LOG.info(totalScore.toString() + t1.getNm() + dataTime);
						
						// 记录或者更新证书信息表
						T17CreditInf t17 = new T17CreditInf();
						t17.setUsrid(Long.parseLong(t1.getUsrid()));
						t17.setNm(t1.getNm());
						t17.setCrdt_tp(t1.getCrdt_tp());
						t17.setCrdt_no(t1.getCrdt_no());
						t17.setCredit_no(creditNo);
						t17.setFile_path(certificatePath);
						t17.setTms(new Timestamp(date.getTime()));
						t17.setType("05");
						t17.setFlag("01");
						saveCreditInf(t17);
		} else {// 通用模块合格证书生成
			// 判断是否可以取得合格证书
			if (totalScore >= 80) {
			} else {
				res = new ResultEntity("0003", "考试成绩不合格.", totalScore.toString(), "", t1.getUsrid() + "");
				// setAttr("certificatePath", certificatePath);
				// renderWithPath("/f/accession/certificate.html");
				renderJson(res);
				return;
			}
			// ResultEntity res = new ResultEntity("0000", "恭喜您！您已完成测试，您的成绩为：" + toltalScore
			// + "分！");
			String certificatePath = "";
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String dataTime = format.format(date);
			// 获取工程路径
			String webContentPath = "";
			try {
				String path = Class.class.getResource("/").toURI().getPath();
				webContentPath = new File(path).getParentFile().getParentFile().getCanonicalPath();
				LOG.info("webContentPath=" + webContentPath);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// DateFormat类的静态工厂方法
			System.out.println(format.getInstance().format(date));
			//String srcImg = webContentPath + "\\images_zcb\\certificateTemp.jpg";
			String srcImg = webContentPath + "\\images_zcb\\certificateTemplate01.jpg";
			// // 取身份证号码第1位+ 最后1位
			// String crdt_no = t1.getCrdt_no().toString();
			// String crdt_no_endStr = "";
			// if (!StringUtils.isBlank(crdt_no)) {
			// crdt_no_endStr = crdt_no.substring(0, 1)
			// + crdt_no.substring(crdt_no.length() - 2, crdt_no.length() - 1);
			// }
			DateFormat formatYear = new SimpleDateFormat("yyyy");
			String year = formatYear.format(date);
			// 生成证书编号
			String creditNo = "02" + year + String.format("%06d", Long.parseLong(t1.getUsrid()));
//			certificatePath = "\\images_zcb\\certificates\\" + "反兴奋剂教育准入合格证书_" + t1.getNm() + "_" + t1.getUsrid()
//					+ ".jpg";
			certificatePath = "\\images_zcb\\certificates\\" + "反兴奋剂教育准入合格证书_" + t1.getNm() + creditNo + ".jpg";
			String dscImg = webContentPath + certificatePath;
			LOG.info("srcImg=" + srcImg);
			LOG.info("dscImg=" + dscImg);
			LOG.info("certificatePath=" + certificatePath);
/*			waterMark(totalScore.toString(), srcImg, dscImg, 212, 616);
			waterMark(t1.getNm(), dscImg, dscImg, 212, 671);
			waterMark(dataTime, dscImg, dscImg, 212, 731);*/
			///
//			waterMark(t1.getNm(), srcImg, dscImg, 511, 1170, 100);
//			waterMark(dataTime+"(yyyy/mm/dd)", dscImg, dscImg, 1405, 1912, 55);
//			waterMark(totalScore.toString(), dscImg, dscImg, 1090, 2027, 55);
			
			waterMark(t1.getNm()+"("+StringTools.strConver(t1.getNmChar())+")", srcImg, dscImg, 943, 2007, 180);
			waterMark(dataTime+"(yyyy/mm/dd)", dscImg, dscImg, 2230, 3407, 120);
			waterMark(which_competition, dscImg, dscImg, 1995, 3519, 120);
			waterMark(totalScore.toString(), dscImg, dscImg, 1911, 3619, 120);
			//waterMark(creditNo, dscImg, dscImg, 923, 2114, 55);
			waterMark(creditNo, dscImg, dscImg, 1655, 3763, 120);
			// 记录或者更新证书信息表
			T17CreditInf t17 = new T17CreditInf();
			t17.setUsrid(Long.parseLong(t1.getUsrid()));
			t17.setNm(t1.getNm());
			t17.setCrdt_tp(t1.getCrdt_tp());
			t17.setCrdt_no(t1.getCrdt_no());
			t17.setCredit_no(creditNo);
			t17.setFile_path(certificatePath);
			t17.setTms(new Timestamp(date.getTime()));
			t17.setType("05");
			t17.setFlag("02");
			saveCreditInf(t17);
			///
			LOG.info(totalScore.toString() + t1.getNm() + dataTime);
		}
		// 合格证书加水印
		// String hostAddress = "";
		// try {
		// InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址
		// //PC-20140317PXKX/192.168.0.121
		// hostAddress = address.getHostAddress();// 192.168.0.121
		//
		// } catch (UnknownHostException e) {
		// e.printStackTrace();
		// }
		// res = new ResultEntity("0000", "考试成绩提交成功.", certificatePath, hostAddress,
		// t1.getCrdt_no());
		res = new ResultEntity("0000", "考试成绩提交成功.", "", category, t1.getUsrid() + "");

		// setAttr("certificatePath", certificatePath);
		// renderWithPath("/f/accession/certificate.html");
		renderJson(res);

		// }

	}

	/**
	 * 描述：加水印，在合格证书上打印考试结果信息
	 * 
	 * @author zhuchaobin 2018-06-01
	 */
	public static void waterMark(String waterMsg, String inputImg, String outImg, Integer x, Integer y, Integer fontSize) {
		try {
			// 1.jpg是你的 主图片的路径
			InputStream is = new FileInputStream(inputImg);

			// 通过JPEG图象流创建JPEG数据流解码器
			JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
			// 解码当前JPEG数据流，返回BufferedImage对象
			BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
			// 得到画笔对象
			Graphics g = buffImg.getGraphics();

			// 设置颜色。
			g.setColor(Color.BLACK);

			// 最后一个参数用来设置字体的大小
			if(null == fontSize || 0 == fontSize)
				fontSize = 45;
			Font f = new Font("黑体", Font.BOLD, fontSize);
	//		Color mycolor = Color.darkGray;// new Color(0, 0, 255);
			Color mycolor = Color.darkGray;
			g.setColor(mycolor);
			g.setFont(f);

			// 10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
			g.drawString(waterMsg, x, y);
			g.dispose();

			OutputStream os;

			// os = new FileOutputStream("d:/union.jpg");
			String shareFileName = outImg;
			os = new FileOutputStream(shareFileName);
			// 创键编码器，用于编码内存中的图象数据。
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			en.encode(buffImg);

			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	/**
	 * 描述：高分榜100名
	 * 
	 * @author zhuchaobin 2018-10-19 二期 东京奥运会
	 * @throws URISyntaxException
	 */
	@Clear
	public void heroList100() {
		String crdt_no = getPara("crdt_no");
		setAttr("crdt_no", crdt_no);
		String certificatePath = "";

		String useridStr = getSession().getAttribute("usrid") + "";
		// 插入或者更新成绩统计表最后一次成绩
		String sql = "select t.*, r.nm, r.spt_prj, r.province, (case r.city when '--' then '' when '-' then '' else r.city end) as city from t11_exam_stat t "
				+ "JOIN t1_usr_bsc r on t.exam_st = '9' and t.usrid = r.usrid order by exam_grd desc, tms asc";
		List<T11ExamStat> heroList = T11ExamStat.dao.find(sql);
		List<T11ExamStat> heroListRlt10 = new ArrayList<T11ExamStat>();
		List<T11ExamStat> heroListRlt100 = new ArrayList<T11ExamStat>();
		T11ExamStat t11Self = new T11ExamStat();
		String t11SelfDisplay = "display:none;";
		if (null != useridStr) {
			for(int j = 0; j < heroList.size(); j++) {				
					if (useridStr.equals((heroList.get(j).getUsrid() + ""))) {
						t11Self = heroList.get(j);
						t11Self.setRank("#FF0202");
						t11Self.setId(Long.parseLong((j + 1) + ""));
						t11SelfDisplay = "";
					}
			}
		}

		// 名次，名次缩略图赋值
		for (int i = 0; i < 100; i++) {
			T11ExamStat t11 = new T11ExamStat();
			if (i < heroList.size()) {
				t11 = heroList.get(i);
			} else {
				if (i >= 10)
					break;
			}

			LOG.debug("t11.getUsrid()" + t11.getUsrid());
			if (null != useridStr) {
				if (useridStr.equals((t11.getUsrid() + ""))) {
					t11.setRank("#FF0202");
				}
			}

			if (i < 10) {
				String rankImg = "rank" + (i + 1) + ".png";
				t11.setRankImg(rankImg);
				heroListRlt10.add(t11);
			} else {
				t11.setId(Long.parseLong((i + 1) + ""));
				heroListRlt100.add(t11);
			}

			// // 默认city没有的话，默认值是“--”，特殊处理
			// if(t11.getCity().toString().contains("-")) {
			// t11.setCity(null);
			// }

		}
		setAttr("heroList10", heroListRlt10);
		setAttr("heroList100", heroListRlt100);
		setAttr("t11Self", t11Self);
		setAttr("t11SelfDisplay", t11SelfDisplay);
		renderWithPath("/f/accession/hero_list_100.html");
	}

	/**
	 * 描述：从30道选择题中随机取10道，从30道判断题中随机取10道构成试卷。并保存到成绩记录表中。
	 * 
	 * @author zhuchaobin 2019-10-20
	 */
	// @Before(FunctionInterceptor.class)
	public void generteTest_tokyo_5() {

		String type = getPara("type");
		String category = getPara("category");

		System.out.println("type=" + type);
		System.out.println("category=" + category);
		// 判断赛事类型是否为空
		String which_competition = (String) getSession().getAttribute("which_competition");
		if (StringUtils.isBlank(which_competition)) {
			LOG.error("session中获取赛事名称获取为空");
			renderWithPath("/f/zhunru_index_pre.html");
			return;
		} else {
			setAttr("which_competition", which_competition);
		}
		/*
		 * if (!isCanTest()) renderWithPath("/f/accession/dotest.html");
		 */
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));

		// List<T11ExamStat> T11ExamStat_num = null;
		if (which_competition.equals("青奥会")) {
			// 查询一共考了多少次，规则修改为青奥会考试次数不能超过3次，2018-09-04
			List<T11ExamStat> T11ExamStat_num = T11ExamStat.dao
					.find("select * from t11_exam_stat t where t.type = '" + type + "' and t.usrid='" + usrid + "'");
			if (T11ExamStat_num.size() >= 3) {
				LOG.debug("generteTest----" + "总答题次数已满3次！！");
				setAttr("tpsMsg", "对不起，每人最多只能答题三次。您已答题三次，不能再参加考试。");
				renderWithPath("/f/tips.html");
				return;
			}
		} else if (which_competition.equals("东京奥运会")) {
			// 查询一共考了多少次，没门课程不超过3次
			List<T11ExamStat> T11ExamStat_num = T11ExamStat.dao.find("select * from t11_exam_stat t where t.type = '"
					+ type + "' and t.category = '" + category + "' and t.usrid='" + usrid + "' and t.exam_st != '9'");
			if (T11ExamStat_num.size() >= 3) {
				LOG.debug("generteTest----" + "总答题次数已满3次！！");
				setAttr("tpsMsg", "对不起，该课程只能答题三次。您已答题三次，不能再参加考试。");
				renderWithPath("/f/tips.html");
				return;
			}
		} else {// 省运会、亚运会
			// 查询当天已经考试了几次
			List<T11ExamStat> T11ExamStat_num = T11ExamStatService.service.SelectByUserIdAndTime(usrid,
					which_competition);
			if (T11ExamStat_num.size() >= 3) {
				LOG.debug("generteTest----" + "今日答题次数已满！！");
				setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
				renderWithPath("/f/tips.html");
				return;
			}
		}

		// 查询当天已经考试了几次
		// List<T11ExamStat> T11ExamStat_num =
		// T11ExamStatService.service.SelectByUserIdAndTime(usrid);
		// if(T11ExamStat_num.size()>=4)
		// {
		// LOG.debug("generteTest----"+"今日答题次数已满！！");
		// setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
		// renderWithPath("/f/tips.html");
		// } else {

		// 选择题
		String sql = "select * from t9_tstlib t where t.prblm_tp ='01' and t.type = '" + type + "' and t.category = '"
				+ category + "' order by t.prblmid";
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
		sql = "select * from t9_tstlib t where t.prblm_tp ='02' and t.type = '" + type + "' and t.category = '"
				+ category + "' order by t.prblmid";
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
			t10ExamGrd.setType(type);
			t10ExamGrd.setCategory(category);
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
		setAttr("type", type);
		setAttr("category", category);
		// setAttr("pre_action", "/jf/puresport/t7Crcl/video3_select_5");// 必修视频3选择
		renderWithPath("/f/accession/dotest_tokyo_3.html");

		// }

	}

	/**
	 * 描述：根据考试id查询试卷
	 * 
	 * @author zhuchaobin 2019-11-2
	 */
	public void queryTestPaper() {
		String usrid = getPara("usrid");
		String examid = getPara("examid");
		String userFlag = getPara("userFlag");//1:管理员 2：普通用户
		// 考试名称，科目
		String t11sql = "select * from t11_exam_stat t where t.examid ='" + examid + "' and t.usrid='" + usrid + "'";
		T11ExamStat t11 = T11ExamStat.dao.findFirst(t11sql);
		if (null != t11) {
			setAttr("exam_name", t11.getExam_nm());
			setAttr("category", t11.getCategory());
			DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			setAttr("tms", t11.getTms());
			setAttr("exam_grd", t11.getExam_grd());
		}

		// 查询用户信息
		T1usrBsc t1 = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=?", usrid);// 根据用户名查询数据库中的用户
		if (t1 != null) {
			setAttr("usrNm", t1.getNm());
			setAttr("crdtNo", t1.getCrdt_no());
		}

		System.out.println("usrid=" + usrid);
		System.out.println("examid=" + examid);

		String sql = "select * from t10_exam_grd t where t.usrid = '" + usrid + "' and t.examid ='" + examid
				+ "' order by t.prblmno asc";
		List<T10ExamGrd> t10List = T10ExamGrd.dao.find(sql);
		List<ExamEntity> testPaperList01 = new ArrayList<ExamEntity>();
		List<ExamEntity> testPaperList02 = new ArrayList<ExamEntity>();
		if ((null == t10List) || (0 < t10List.size())) {
			LOG.debug("查询到试卷，包含考题：" + t10List.size() + "道.");
			for (T10ExamGrd t10 : t10List) {
				ExamEntity exam = new ExamEntity();
				exam.setUsr_aswr(t10.getUsr_aswr());
				exam.setPrblmno(Integer.parseInt(t10.getPrblmno()));
				exam.setPrblmid(Integer.parseInt(t10.getPrblmid()));
				exam.setUsrid(Integer.parseInt(t10.getUsrid()));
				exam.setExam_grd(Integer.parseInt(t10.getExam_grd()));				
				// 查题目
				T9Tstlib t9 = T9Tstlib.dao.findById(Long.parseLong(t10.getPrblmid() + ""));
				if (null != t9) {
					exam.setTtl(t9.getTtl());
					if("1".equals(userFlag)) {
						exam.setPrblm_aswr("【正确答案：" + t9.getPrblm_aswr() + "】");
						if ("02".equals(t9.getPrblm_tp())) {
							if(t9.getPrblm_aswr().equals("A"))
								exam.setPrblm_aswr("	【正确答案：正确】");
							else if(t9.getPrblm_aswr().equals("B"))
								exam.setPrblm_aswr("	【正确答案：错误】");
						}
					}
				}
/*				if (Integer.parseInt(t10.getExam_grd() + "") > 0)
					exam.setRltDesc("<code class=\"text-success bg-success\">答案正确</code>");
				else
					exam.setRltDesc("<code class=\"text-danger bg-danger\">答案错误</code>");*/
				
				if (t9.getPrblm_aswr().equals(t10.getUsr_aswr())) {
					exam.setRltDesc("<code class=\"text-success bg-success\">答案正确</code>");
					exam.setPrblm_aswr("");
				} else
					exam.setRltDesc("<code class=\"text-danger bg-danger\">答案错误</code>");

				exam.setOpt((String) t9.getOpt());
				String option = exam.getOpt();
				String[] optionList = option.split("\\|");
				// 4个选项
				if (4 == optionList.length) {
					exam.setOptA(optionList[0]);
					exam.setOptB(optionList[1]);
					exam.setOptC(optionList[2]);
					exam.setOptD(optionList[3]);
				} else if (2 == optionList.length) {// 2个选项
					exam.setOptA("正确");
					exam.setOptB("错误");
				}
				// 设置已选选项
				if (t10.getUsr_aswr().toString().contains("A"))
					exam.setOptASelct("checked");
				if (t10.getUsr_aswr().toString().contains("B"))
					exam.setOptBSelct("checked");
				if (t10.getUsr_aswr().toString().contains("C"))
					exam.setOptCSelct("checked");
				if (t10.getUsr_aswr().toString().contains("D"))
					exam.setOptDSelct("checked");

				if ("01".equals(t9.getPrblm_tp()))
					testPaperList01.add(exam);
				else if ("02".equals(t9.getPrblm_tp())) {
					testPaperList02.add(exam);
				}
			}
			setAttr("testPaperList01", testPaperList01);
			setAttr("testPaperList02", testPaperList02);
			renderWithPath("/f/accession/queryTestPaper.html");
		} else {
			LOG.error("查询不到对应考试记录，请确认！");
			renderWithPath("/f/accession/queryTestPaper.html");
		}
	}
	
	void saveCreditInf(T17CreditInf t17) {
//		String sql = "select * from t17_credit_inf t where t.usrid = '" + t17.getUsrid() + "' and t.flag ='" + t17.getFlag()
//				+ "'";
		String sql = "select * from t17_credit_inf t where t.usrid = '" + t17.getUsrid() + "'";
		T17CreditInf t17Rlt = T17CreditInf.dao.findFirst(sql);
		if(null != t17Rlt) {
			t17Rlt.setFile_path(t17.getFile_path());
			t17Rlt.setCredit_no(t17.getCredit_no());
			t17Rlt.setTms(new Timestamp(System.currentTimeMillis()));
			t17Rlt.update();
		} else {
			t17.saveGenIntId();
		}
	}
	
	
	public void invitationCodeVerify() {
		// 处理结果
		ResultEntity res = null;
		String useridStr = getSession().getAttribute("usrid") + "";
		String invitationCode = getPara("invitationCode");
		String flag = getPara("flag"); // 1:判断是否已经验证过   2：提交邀请码从新验证
		String sql = "select * from t14_invitation_code t where t.type = '05'";
		T14InvitationCode t14 = T14InvitationCode.dao.findFirst(sql);
		if(null != t14) {
			if("1".equals(flag)) {
					if(StringUtils.isNoneBlank(t14.getInvited_user_list()+"")) {
						String[] invitedUserList = ((t14.getInvited_user_list())+"").split(",");
						if(null != invitedUserList) {
							for(String usrId : invitedUserList) {
								if(usrId.equals(useridStr)) {
									//  已验证
									res = new ResultEntity("0000", "已验证过邀请码用户.", "", "", "");
									renderJson(res);
									return;
								}
							}
						}
					}
				// 未验证
				res = new ResultEntity("0001", "请填写邀请码进行验证.", "", "", "");
				renderJson(res);
				return;
			} else if("2".equals(flag)) {
				if(t14.getInvitation_code().equals(invitationCode)) {
					res = new ResultEntity("0002", "邀请码验证通过.", "", "", "");
					renderJson(res);
					// 保存已验证用户列表
					String updateSql = "update t14_invitation_code t set t.invited_user_list=CONCAT(t.invited_user_list, ',"+ useridStr +"') where t.type='05'";
					ConfMain.db().update(updateSql);
					return;
				} else {
					res = new ResultEntity("0003", "邀请码验证失败.", "", "", "");
					renderJson(res);
					return;
				}
			}
		} else {
			// 查询邀请码失败
			res = new ResultEntity("0004", "查询邀请码失败.", "", "", "");
			renderJson(res);
			return;
		}
	}

}
