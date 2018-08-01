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
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import puresport.constant.EnumCompetition;
import puresport.entity.ExamEntity;
import puresport.entity.ResultEntity;
import puresport.mvc.t10examgrd.T10ExamGrd;
import puresport.mvc.t11examstat.T11ExamStat;
import puresport.mvc.t11examstat.T11ExamStatService;
import puresport.mvc.t12highestscore.T12HighestScore;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t1usrbsc.T1usrBscService;
import puresport.mvc.t5crclstdy.T5CrclStdy;
import puresport.mvc.t5crclstdy.T5CrclStdyController;
//import puresport.entity.ExamEntity;
import puresport.mvc.t9tstlib.T9Tstlib;
import puresport.mvc.t13tststat.T13TstStat;
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
		//判断是省运会、亚运会、青奥会
		String which_competition = getPara("which_competition");
		if(StringUtils.isBlank(which_competition)) {			
			renderWithPath("/f/zhunru_index.html");
			return;
		}
		if(which_competition.equals(EnumCompetition.ShengYunHui.getIndex_str()))
		{
			getSession().setAttribute("which_competition", EnumCompetition.ShengYunHui.getCompetitionName());
		}else if(which_competition.equals(EnumCompetition.YaYunHui.getIndex_str()))
		{
			getSession().setAttribute("which_competition", EnumCompetition.YaYunHui.getCompetitionName());
		}else if(which_competition.equals(EnumCompetition.QingAoHui.getIndex_str()))
		{
			getSession().setAttribute("which_competition", EnumCompetition.QingAoHui.getCompetitionName());
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
	 * 描述：查询证书
	 * 
	 * @author zhuchaobin 2018-06-03
	 * @throws URISyntaxException
	 */
	@Clear
	public void queryCetifate() {
		String flag = getPara("flag");
		// 考试不及格的情况
		if("2".equals(flag)) {			
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
			renderWithPath("/f/accession/certificate.html");
			return;
		} else {
			setAttr("pageHead", "反兴奋剂教育准入合格证书-" + t1.getNm());
/*			// 取身份证号码第1位+ 最后1位
			String crdt_no_endStr = "";
			if (!StringUtils.isBlank(crdt_no)) {
				crdt_no_endStr = crdt_no.substring(0, 1)
						+ crdt_no.substring(crdt_no.length() - 2, crdt_no.length() - 1);
			}*/
			certificatePath = "/images_zcb/certificates/" + "反兴奋剂教育准入合格证书_" + t1.getNm() + "_"
					+ usrid + ".jpg";
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
		if(isCertificated) {
			setAttr("shareDisplay", "inline");
			setAttr("rankDisplay", "inline");
			setAttr("disappointDisplay", "inline");
		} else if(isExamed) {
			setAttr("rankDisplay", "inline");
			setAttr("disappointDisplay", "inline");
		} else {
			setAttr("startDisplay", "inline");
		}
		setAttr("mingci", mingci);
		setAttr("totalExamer", t11List.size());
		double percent = (t11List.size() + 1 - mingci) * 1.0 / (t11List.size());
		// 最后一名特殊处理
		if(t11List.size() == mingci) {
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
	 * 描述：根据课程id，获取课程信息
	 * 
	 * @author zhuchaobin 2018-06-08
	 * @throws 
	 */
	public T7Crcl getCrclInfo(String crclid) {
		T7Crcl t7 = null;
		if(StringUtils.isNotBlank(crclid)) {
			t7 = T7CrclService.service.SelectById(Integer.parseInt(crclid));
		} else {
			LOG.error("课程编号为空，查询课程信息失败！");
		}
		if(null == t7) {
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
			if(i < heroList.size()) {
				t11 = heroList.get(i);
			} else {
				;
			}
			t11.setRankImg(rankImg);
			LOG.debug("t11.getUsrid()" + t11.getUsrid());
			if(null != useridStr) {
				if(useridStr.equals((t11.getUsrid()+""))) {
					t11.setRank("#FF0202");
				}
			}
			
//			// 默认city没有的话，默认值是“--”，特殊处理
//			if(t11.getCity().toString().contains("-")) {
//				t11.setCity(null);
//			}
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
/*	@Clear*/
	public void video_play() {
	/*	Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));*/
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
			/*setAttr("crcl_brf", getCrclInfo(crclid).getCrcl_brf());// 课程简介
*/			
/*			// 必修课程1
			String sql = "select * from t7_crcl t where t.crclid='" + getPara("crclid") + "'";
			List<T7Crcl> t7List = T7Crcl.dao.find(sql);
			if ((t7List != null) && (t7List.size() > 0)) { // 去视频1
				setAttr("crcl_nm", "必修课程一：" + getCrclInfo(crclid).getCrcl_nm());// 课程名称
				setAttr("crcl_brf", t7List.get(0).getCrcl_brf());// 课程简介
			}*/
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
//		setAttr("stdy_st_hidden", getPara("stdy_st_hidden"));// 本课程学习状态
		setAttr("crclid", crclid);// 课程id
/*		if (!("1".equals(crcl_attr))) {
			setAttr("crcl_brf", getPara("crcl_brf"));// 课程简介
		}*/
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
	
	/**
	 * 描述：新浪视频测试
	 * 
	 * @author zhuchaobin 2018-06-07
	 */
	@Clear
	public void videoTest() {
		renderWithPath("/f/accession/playertest.html");
/*		renderWithPath("/f/accession/video_play.html");*/
	}

	/**
	 * 描述：从30道选择题中随机取10道，从30道判断题中随机取10道构成试卷。并保存到成绩记录表中。
	 * 
	 * @author zhuchaobin 2018-05-21
	 */
	@Before(FunctionInterceptor.class)  
	public void generteTest() {		
		//判断赛事类型是否为空
		String which_competition = (String) getSession().getAttribute("which_competition");	
		if(StringUtils.isBlank(which_competition)) {
			LOG.error("session中获取赛事名称获取为空");
			renderWithPath("/f/zhunru_index.html");
			return;
		}  else {
			setAttr("which_competition", which_competition);
		}
/*		if (!isCanTest())
			renderWithPath("/f/accession/dotest.html");*/
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		//查询当天已经考试了几次
		List<T11ExamStat> T11ExamStat_num = T11ExamStatService.service.SelectByUserIdAndTime(usrid);
		if(T11ExamStat_num.size()>=4)
		{
			LOG.debug("generteTest----"+"今日答题次数已满！！");
			setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
			renderWithPath("/f/tips.html");
		} else {
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
		}
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
		
		// // 处理结果
		ResultEntity res = null;
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		
		
		//查询当天已经考试了几次
		List<T11ExamStat> T11ExamStat_num = T11ExamStatService.service.SelectByUserIdAndTime(usrid);
		if(T11ExamStat_num.size()>=4)
		{
			LOG.debug("submitExam----"+"今日答题次数已满！！");
			setAttr("tpsMsg", "对不起，每日最多只能答题三次。您今天已答题三次，请明日再答。");
			renderWithPath("/f/tips.html");
		}
		else {
			
		String examid = getPara("examid");
		String[] ds = getParaValues("dataSet");
		// 承诺人姓名
		String commimentNm = getPara("commimentNm");
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
					Db.update("update puresport.t13_tst_stat t set t.right_num = t.right_num + 1 where prblmid =" +  prblmId);
				} else {
					t10Data.setExam_grd(0);
					t10Data.setResult("错误");
					// 更新答对次数
					
					Db.update("update puresport.t13_tst_stat t set t.wrong_num = t.wrong_num + 1 where prblmid =" +  prblmId);
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
		// 从session中获取赛事名称
		String which_competition = (String) getSession().getAttribute("which_competition");	
		if(StringUtils.isBlank(which_competition)) {
			LOG.error("获取赛事名称失败！");
			which_competition = "";
		} else {
			LOG.debug("赛事名称：" + which_competition);
		}	
		t11.setExam_nm(which_competition);
		t11.saveGenIntId();
		// 插入或者更新成绩统计表最后一次成绩
		String sql = "select * from t11_exam_stat t where t.usrid = '" + t10.getUsrid() + "' and t.exam_st = '9'";
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
			t11Rlt.saveGenIntId();
		} else {
/*			t11.setExam_st("9");// 考试状态，9表示最终成绩
			t11.setId(Long.parseLong(t11Rlt.getId()));*/
//			t11Rlt.setUsrid(usrid);// 用户id
			t11Rlt.setExamid(Integer.parseInt(examid));// 考试id
			t11Rlt.setExam_grd(totalScore);// 考试成绩
			t11Rlt.setExam_st("1");// 考试状态
			t11Rlt.setExam_channel("01");// 考试渠道,01:互联网站
			t11Rlt.setExam_num(Integer.parseInt(examid));// 考试次数
			t11Rlt.setTms(new Timestamp(System.currentTimeMillis()));// 维护时间
			t11Rlt.setExam_nm(which_competition);
			t11Rlt.setExam_st("9");// 考试状态，9表示最终成绩
			t11Rlt.update();
		}
		// 更新最高成绩
		// 插入或者更新成绩统计表最后一次成绩
		String sql_highest_score = "select * from t12_highest_score t where t.usrid = '" + t10.getUsrid() + "' and t.exam_nm = '" + which_competition + "'";
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
			t12.saveGenIntId();
		} else {
/*			t11.setExam_st("9");// 考试状态，9表示最终成绩
			t11.setId(Long.parseLong(t11Rlt.getId()));*/
//			t11Rlt.setUsrid(usrid);// 用户id
			// 如果本次成绩高于已有最高成绩，则更新最高成绩
			if(totalScore > Integer.parseInt(t12.getExam_grd())) {
				// 转入操作
				Db.update("update puresport.t12_highest_score t set t.exam_grd = ? , t.tms = ? where usrid = ? and t.exam_nm = ?",
						totalScore, new Timestamp(System.currentTimeMillis()),t10.getUsrid(), which_competition );
			}
		}
		
		LOG.debug("totalScore=" + totalScore);
		// 判断是否可以取得合格证书
		if(totalScore >= 80) {
		} else {
			res = new ResultEntity("0003", "考试成绩不合格.", totalScore.toString(), "", t1.getUsrid()+"");
			// setAttr("certificatePath", certificatePath);
			// renderWithPath("/f/accession/certificate.html");
			renderJson(res);
			return;
		}
		// ResultEntity res = new ResultEntity("0000", "恭喜您！您已完成测试，您的成绩为：" + toltalScore
		// + "分！");
		String certificatePath = "";
		if (t1 != null) {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			String srcImg = webContentPath + "\\images_zcb\\certificateTemp.jpg";
//			// 取身份证号码第1位+ 最后1位
//			String crdt_no = t1.getCrdt_no().toString();
//			String crdt_no_endStr = "";
//			if (!StringUtils.isBlank(crdt_no)) {
//				crdt_no_endStr = crdt_no.substring(0, 1)
//						+ crdt_no.substring(crdt_no.length() - 2, crdt_no.length() - 1);
//			}
			certificatePath = "\\images_zcb\\certificates\\" + "反兴奋剂教育准入合格证书_" + t1.getNm() + "_"
					+ t1.getUsrid() + ".jpg";
			String dscImg = webContentPath + certificatePath;
			LOG.info("srcImg=" + srcImg);
			LOG.info("dscImg=" + dscImg);
			LOG.info("certificatePath=" + certificatePath);
			waterMark(totalScore.toString(), srcImg, dscImg, 212, 616);
			waterMark(t1.getNm(), dscImg, dscImg, 212, 671);
			waterMark(dataTime, dscImg, dscImg, 212, 731);
			LOG.info(totalScore.toString() + t1.getNm() + dataTime);
		} else {
			LOG.error("查不到用户信息！");
		}
		// 合格证书加水印
//		String hostAddress = "";
//		try {
//			InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
//			hostAddress = address.getHostAddress();// 192.168.0.121
//
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		// res = new ResultEntity("0000", "考试成绩提交成功.", certificatePath, hostAddress,
		// t1.getCrdt_no());
		res = new ResultEntity("0000", "考试成绩提交成功.", "", "", t1.getUsrid() +"");
		// setAttr("certificatePath", certificatePath);
		// renderWithPath("/f/accession/certificate.html");
		renderJson(res);
		}
	}

	/**
	 * 描述：加水印，在合格证书上打印考试结果信息
	 * 
	 * @author zhuchaobin 2018-06-01
	 */
	public static void waterMark(String waterMsg, String inputImg, String outImg, Integer x, Integer y) {
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
			Font f = new Font("黑体", Font.PLAIN, 28);
			Color mycolor = Color.darkGray;// new Color(0, 0, 255);
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

}
