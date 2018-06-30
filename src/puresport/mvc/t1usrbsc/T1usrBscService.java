package puresport.mvc.t1usrbsc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.ComUtil;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.tuple.Tuple2;
import csuduc.platform.util.tuple.TupleUtil;
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumRoleType;
import puresport.constant.EnumStatus;
import puresport.constant.EnumTypeLevel;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ValidateComm;
import puresport.mvc.t6mgrahr.T6MgrSession;

public class T1usrBscService extends BaseService {
	private final static String tableName = "t1_usr_bsc";
	private final static String tableKey = "crdt_no";

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscService.class);

	public static final T1usrBscService service = Enhancer.enhance(T1usrBscService.class);

	public T1usrBsc SelectById(Integer id) {

		T1usrBsc mdl = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=? limit 1 ", id);
		return mdl;
	}

	public boolean isExist(T1usrBsc mdl) {
		Record sporter = ConfMain.db().findById(tableName, T1usrBsc.column_crdt_no,
				(String) mdl.get(T1usrBsc.column_crdt_no));
		if (null == sporter || !mdl.getLong(T1usrBsc.column_usrid).equals(sporter.getLong(T1usrBsc.column_usrid))) {
			return false;
		}
		return true;
	}
	public Tuple2<Boolean, String> delete(T6MgrSession mgrSession, ParamComm paramComm){
		Integer usrId = paramComm.getId().intValue();
		try {
			// 查询待删除信息
			T1usrBsc sporter = SelectById(usrId);
			if (Objects.isNull(sporter)) {
				log.error("delete sporter not exist ,param:" +paramComm);
				return TupleUtil.tuple(false, "该用户不存在");
			}
			// 比较省市是否可删
			if (ValidateComm.inv_deleteProvince_sporter(mgrSession, sporter)) {
				log.error(String.format("delete sporter inv_deleteProvince param:%s  session:%s " , paramComm, mgrSession));
				return TupleUtil.tuple(false, "您没有权限"); 
			}
			sporter.update();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e);
			return TupleUtil.tuple(false, "系统有误，请联系管理人员");
		}
		return TupleUtil.tuple(false, "删除成功");
	}
	public static String getSearchWhere(T6MgrSession mgrSession, ParamComm paramMdl, List<Object> listArgs) {
		StringBuilder whereStr = new StringBuilder();
			if (StringUtil.notEmpty(paramMdl.getName1())) {
				whereStr.append(" and nm like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName1()));
			}
			if (StringUtil.notEmpty(paramMdl.getName2())) {
				whereStr.append(" and crdt_no like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName2()));
			}
			return whereStr.toString();
	}
	public List<T1usrBsc> selectByPage(T6MgrSession mgrSession, ParamComm paramMdl) {
		final String roleStr = mgrSession.selectRoleStr_UserBasic();
		List<Object> listArgs = new ArrayList<>();
		final String searchStr = getSearchWhere(mgrSession, paramMdl, listArgs);
		Long countTotal = ConfMain.db()
				.queryLong(String.format("select count(1) from %s where %s %s", tableName, roleStr, searchStr), listArgs.toArray());
		paramMdl.setTotal(countTotal);
		List<T1usrBsc> resList = new ArrayList<>();
		if (countTotal > 0) {
			listArgs.add(paramMdl.getPageIndex());
			listArgs.add(paramMdl.getPageSize());
			resList = T1usrBsc.dao.find(String.format(
					"select usrid,usr_tp, nm,crdt_tp, crdt_no,department,post, gnd,brth_dt,spt_prj, typelevel, province, city,institute, mblph_no, email,levelprovince,levelcity,levelinstitute  from %s where %s %s  limit ?,?",
					tableName, roleStr, searchStr), listArgs.toArray());
			resList.forEach(e->{
				// 单独处理级别显示
				StringBuilder levelAll = new StringBuilder();
	
				if ((EnumStatus.LevelShow.getIdStr().equals((String)e.getTypelevel()))
						||(EnumStatus.LevelShow.getIdStr().equals((String)e.getLevelinstitute()))) {
					levelAll.append( EnumTypeLevel.Country.getName()).append(" ");
				}
				if(EnumStatus.LevelShow.getIdStr().equals((String)e.getLevelprovince()))
				{
					levelAll.append( EnumTypeLevel.Province.getName()).append(" ");
				}
				if(EnumStatus.LevelShow.getIdStr().equals((String)e.getLevelcity()))
				{
					levelAll.append( EnumTypeLevel.City.getName()).append(" ");
				}
				e.setTypelevel(levelAll.toString());
			});
		
		}
		return resList;
	}

	/**
	 * 成绩统计
	 * 
	 * @param paramMdl
	 * @return
	 */
	private static String sql_score = "select u.*, s.exam_nm as exam_nm, s.exam_grd as exam_grd, (CASE WHEN s.exam_grd >= 80 THEN '及格'  WHEN s.exam_grd is null THEN '未考试'  ELSE '不及格' END) as passed "
			+ " from t1_usr_bsc u  left join ("
			+ " select usrid, exam_nm, max(exam_grd) as exam_grd from t11_exam_stat where exam_st='1' group by usrid, exam_nm "
			+ " ) as s on u.usrid = s.usrid  where 1=1 ";

	public List<Record> selectScoreByPage(T6MgrSession mgrSession, ParamComm paramMdl) {

		List<Object> listArgs = new ArrayList<>();
		String whereSql = getProvinceWhere(mgrSession, paramMdl, listArgs);
		Object[] listObjs = listArgs.toArray();
		List<Record> userScoreRecords = ConfMain.db().find(sql_score + whereSql, listObjs);
		return userScoreRecords;
	}

	private final static String defSelect = "全部";

	private final static String getStringLikeLeft(String s) {
		return s + "%";
	}

	public static String getProvinceWhere(T6MgrSession mgrSession, ParamComm paramMdl, List<Object> listArgs) {
		StringBuilder whereStr = new StringBuilder();
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			whereStr.append(" and levelinstitute >0 or typelevel > '0' ");
			if (StringUtil.notEmptyOrDefault(paramMdl.getName1(), defSelect)) {
				whereStr.append(" and province like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName1()));
			}
			if (StringUtil.notEmptyOrDefault(paramMdl.getName2(), defSelect)) {
				whereStr.append(" and city like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName2()));
			}
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			// 省级 只可见属于该省的
			whereStr.append(" and province like ?  and levelprovince>0 ");
			listArgs.add(getStringLikeLeft(mgrSession.getProvince()));

			if (StringUtil.notEmptyOrDefault(paramMdl.getName2(), defSelect)) {
				whereStr.append(" and city like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName2()));
			}
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			whereStr.append(" and province like ? ");
			listArgs.add(getStringLikeLeft(mgrSession.getProvince()));
			whereStr.append(" and city like ? and levelcity>0 ");
			listArgs.add(getStringLikeLeft(mgrSession.getCity()));

		} else if(mgrSession.getTypeleve().equals(EnumTypeLevel.CenterInstitute.getName())) {
			whereStr.append(String.format(" and levelinstitute >0 and  institute='%s' ", mgrSession.getInstitute()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName3(), defSelect)) {
			whereStr.append(" and institute like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName3()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName4(), defSelect)) {
			whereStr.append(" and spt_prj like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName4()));
		}
		// 分页必须加
		whereStr.append(" limit ?,?");
		listArgs.add(paramMdl.getPageIndex());
		listArgs.add(paramMdl.getPageSize());
		return whereStr.toString();
	}

	public ResUserScore getUserScore(T1usrBsc user) {
		ResUserScore userScore = new ResUserScore();
		userScore.setUsr_tp(user.getUsr_tp());
		userScore.setUsr_nm(user.getUsr_nm());
		userScore.setNm(user.getNm());
		userScore.setCrdt_tp(user.getCrdt_tp());
		userScore.setCrdt_no(user.getCrdt_no());
		userScore.setSpt_prj(user.getSpt_prj());
		userScore.setGnd(user.getGnd());
		userScore.setBrth_dt(user.getBrth_dt());
		userScore.setMblph_no(user.getMblph_no());
		userScore.setEmail(user.getEmail());
		userScore.setProvince(user.getProvince());
		userScore.setCity(user.getCity());
		userScore.setInstitute(user.getInstitute());
		userScore.setCourse("田径100米");
		userScore.setScore(90);
		userScore.setPassed("合格");
		return userScore;
	}

	private static String sql_prj = "select * from prjGroupStatis where 1=1 ";

	/**
	 * 统计-项目合格率
	 * 
	 * @param paramMdl
	 * @return
	 */
	public List<Record> selectPassedPercent(T6MgrSession mgrSession, ParamComm paramMdl) {
		List<Object> listArgs = new ArrayList<>();
		String whereSql = getProvinceWhere(mgrSession, paramMdl, listArgs);
		Object[] listObjs = listArgs.toArray();
		List<Record> prjStatisticsRes = ConfMain.db().find(sql_prj + whereSql, listObjs);

		if (CollectionUtils.isEmpty(prjStatisticsRes))
			return new ArrayList<>();

		prjStatisticsRes.stream().forEach(e -> {
			Long cntTotal = e.getLong("cnt_total");
			Long cntAnswered = e.getLong("cnt_answered");
			Long cntPassed = e.getLong("cnt_passed");
			if (ComUtil.notNullAndZero(cntTotal) && ComUtil.notNullAndZero(cntAnswered)
					&& ComUtil.notNullAndZero(cntPassed)) {
				e.set("answered", String.format("%d%%(%d/%d)", cntAnswered * 100 / cntTotal, cntAnswered, cntTotal));
				e.set("passed", String.format("%d%%(%d/%d)", cntPassed * 100 / cntAnswered, cntPassed, cntAnswered));
			} else if (ComUtil.notNullAndZero(cntTotal) && ComUtil.notNullAndZero(cntAnswered)) {
				e.set("answered", String.format("%d%%(%d/%d)", cntAnswered * 100 / cntTotal, cntAnswered, cntTotal));
				e.set("passed", String.format("0%%(0/%d)", cntAnswered));
			} else if (ComUtil.notNullAndZero(cntTotal)) {
				e.set("answered", String.format("0%%(0/%d)", cntTotal));
				e.set("passed", "0%%(0/0)");
			} else {
				e.set("answered", "0%%(0/0)");
				e.set("passed", "0%%(0/0)");
			}
		});
		return prjStatisticsRes;
	}

	public ResPrjStatis getPrjStatisMdl(Record record) {
		ResPrjStatis prjStatis = new ResPrjStatis();
		prjStatis.setSpt_prj(record.getStr("spt_prj"));
		prjStatis.setProvince(record.getStr("province"));
		prjStatis.setCity(record.getStr("city"));
		prjStatis.setInstitute(record.getStr("institute"));
		prjStatis.setAnswered("100%(30/30)");
		prjStatis.setPassed("80%(40/50)");
		return prjStatis;
	}

	private static String sql_problem = "select * from problemStatis where 1=1 ";

	public static String getQuestionWhere(ParamComm paramMdl, List<Object> listArgs) {
		StringBuilder whereStr = new StringBuilder();
		if (StringUtil.notEmptyOrDefault(paramMdl.getName1(), defSelect)) {
			whereStr.append(" and prblm_tp like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName1()));
		}
		// 分页必须加
		whereStr.append(" limit ?,?");
		listArgs.add(paramMdl.getPageIndex());
		listArgs.add(paramMdl.getPageSize());
		return whereStr.toString();
	}

	/**
	 * 统计-试题错误率
	 * 
	 * @param paramMdl
	 * @return
	 */
	public List<Record> selectExamQuestion(ParamComm paramMdl) {

		List<Object> listArgs = new ArrayList<>();
		String whereSql = getQuestionWhere(paramMdl, listArgs);
		Object[] listObjs = listArgs.toArray();
		List<Record> problemStatisRes = ConfMain.db().find(sql_problem + whereSql, listObjs);

		if (CollectionUtils.isEmpty(problemStatisRes))
			return new ArrayList<>();

		problemStatisRes.stream().forEach(e -> {
			Long cntTotal = e.getLong("cntall");
			Long cntWrong = e.getLong("cntwrong");
			if (ComUtil.notNullAndZero(cntTotal) && ComUtil.notNullAndZero(cntWrong)) {
				e.set("errorPercent", String.format("%d%%(%d/%d)", cntWrong * 100 / cntTotal, cntWrong, cntTotal));
			} else if (ComUtil.notNullAndZero(cntTotal)) {
				e.set("errorPercent", String.format("0%%(0/%d)", cntTotal));
			} else {
				e.set("errorPercent", "--(0/0)");
			}
			if (e.getStr("prblm_tp").equals("02")) {
				// 判断题
				if (e.getStr("prblm_aswr").equals("A")) {
					e.set("prblm_aswr", "正确");
				} else {
					e.set("prblm_aswr", "错误");
				}
			} else if (e.getStr("prblm_tp").equals("01")) {
				// 选择题
			}
		});
		return problemStatisRes;
	}

	public ResExamQuestion getExamQuestionMdl(Record record) {
		ResExamQuestion item = new ResExamQuestion();
		item.setType(record.getStr("prblm_tp"));
		item.setTitle(record.getStr("ttl"));
		item.setContent(record.getStr("opt"));
		item.setAnswer(record.getStr("prblm_aswr"));
		item.setScore(record.getInt("scor"));
		item.setErrorPercent("10%(10/100)");
		return item;
	}

	/**
	 * 将excel数据导入数据库
	 * 
	 * @param excelRows
	 * @param outFailedRows
	 * @return
	 */
	public Tuple2<Boolean, String> insertFromExcel(T6MgrSession mgrSession, List<MdlExcelRow> excelRows) {
		if (CollectionUtils.isEmpty(excelRows) || Objects.isNull(mgrSession)) {
			log.error("excelRows or  mgrSession is null");
			return TupleUtil.tuple(false, "excel文件没有有效数据,请您检查");
		}
		// 记录失败的
		final ComOutMdl<Boolean> resBool = new ComOutMdl<>();
		resBool.set(true);
		final ComOutMdl<Integer> rowCnt = new ComOutMdl<>();
		rowCnt.set(0);
		StringBuilder resStr = new StringBuilder();
		excelRows.forEach(e -> {
			rowCnt.set(rowCnt.get() + 1);
			Tuple2<Boolean, String> r = insertRowToDb(mgrSession, e);
			if (!r.first) {
				resBool.set(false);
				;
				resStr.append(String.format("第%d行%s \n", rowCnt.get(), r.second));
			}
		});
		return TupleUtil.tuple(resBool.get(), resStr.toString());
	}

	private Tuple2<Boolean, String> insertRowToDb(T6MgrSession mgrSession, MdlExcelRow excelRow) {
		boolean res = false;
		String resTips = "";
		try {
			// 校验输入
			if (StringUtil.invalidateLength(excelRow.getByIndex(0), 2, 64)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				// 因为可能有空行，当姓名没有的时候，直接默认未空行
				return TupleUtil.tuple(true, "");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(1), 1, 8)
					|| ValidateComm.inv_column_crdt_tp(excelRow.getByIndex(1))) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, "证件类型不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(2), 8, 20)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, "证件号不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(3), 1, 4)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, "性别不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(4), 2, 10)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, "出生日期不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(5), 2, 18)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, "手机号不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(6), 2, 128)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, "邮箱不符合要求");
			}
			// 根据手机号匹配，没有插入、已有更新
			String crdt_number = excelRow.getByIndex(2);// 身份证号

			Record dbRow;

			// 查询该运动员是否已经导入过
			Record sporter = ConfMain.db().findById(tableName, T1usrBsc.column_crdt_no, crdt_number);
			if (Objects.isNull(sporter)) {
				// 不存在 则插入
				dbRow = new Record()
						.set(T1usrBsc.column_usr_nm, excelRow.getByIndex(0))// 用户账户名：手机号
						.set(T1usrBsc.column_nm, excelRow.getByIndex(0))
						.set(T1usrBsc.column_crdt_tp, excelRow.getByIndex(1)).set(T1usrBsc.column_crdt_no, crdt_number)
						.set(T1usrBsc.column_gnd, excelRow.getByIndex(3))
						.set(T1usrBsc.column_brth_dt, excelRow.getByIndex(4))
						.set(T1usrBsc.column_pswd,
								DESUtil.encrypt(crdt_number.substring(crdt_number.length() - 6), ConstantInitMy.SPKEY))// 密码默认身份证后6位
						.set(T1usrBsc.column_mblph_no, excelRow.getByIndex(5))
						.set(T1usrBsc.column_email, excelRow.getByIndex(6))
						.set(T1usrBsc.column_cty_prov_city_mgrid, mgrSession.getUsrid())

						// .set(T1usrBsc.column_typelevel,
						// mgrSession.getTypeleve())
						.set(T1usrBsc.column_province, mgrSession.ggProvince())
						.set(T1usrBsc.column_city, mgrSession.ggCity());
				
				resolveLevelWithSession_Insert(dbRow, mgrSession);
				res = ConfMain.db().save(tableName, tableKey, dbRow);
				resTips = " 导入成功";
			} else {
				// 已存在 则只更新级别标志
				dbRow = new Record().set(T1usrBsc.column_usrid, sporter.get(T1usrBsc.column_usrid));
				resolveLevelWithSession_Update(dbRow, mgrSession);
				res = ConfMain.db().update(tableName, T1usrBsc.column_usrid, dbRow);
				resTips = " 运动员已存在，如需修改信息请进行编辑";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return TupleUtil.tuple(false, "有失败情况，请您检查数据后重试");

		}
		return TupleUtil.tuple(res, "");
	}
	
	/**
	 * level的处理逻辑
	 * 0：不可见 删除
	 * 1：可见
	 * 2：具有该级别
	 * @param record
	 * @param mgrSession
	 */
	private void resolveLevelWithSession_Insert(Record record,  T6MgrSession mgrSession){
		String typeLevel = mgrSession.getTypeleve();
		if (typeLevel.equals(EnumTypeLevel.Country.getName())) {
			record.set(T1usrBsc.column_typelevel, EnumStatus.LevelShow.getIdStr());
			
		} else if (typeLevel.equals(EnumTypeLevel.Province.getName())) {
			record.set(T1usrBsc.column_typelevel, EnumStatus.LevelView.getIdStr())
			.set(T1usrBsc.column_levelprovince, EnumStatus.LevelShow.getId())
			.set(T1usrBsc.column_province, mgrSession.ggProvince());
			
		} else if (typeLevel.equals(EnumTypeLevel.City.getName())) {
			record.set(T1usrBsc.column_typelevel, EnumStatus.LevelView.getIdStr())
			.set(T1usrBsc.column_levelprovince, EnumStatus.LevelView.getId())
			.set(T1usrBsc.column_levelcity, EnumStatus.LevelShow.getId())
			.set(T1usrBsc.column_province, mgrSession.ggProvince())
			.set(T1usrBsc.column_city, mgrSession.ggCity());
		} else if(typeLevel.equals(EnumTypeLevel.CenterInstitute.getName()))
		{
			//record.set(T1usrBsc.column_typelevel, "1")
			record.set(T1usrBsc.column_typelevel, EnumStatus.LevelView.getIdStr())
			.set(T1usrBsc.column_levelinstitute, EnumStatus.LevelShow.getId())
			.set(T1usrBsc.column_institute, mgrSession.getInstitute());
		}
		else {
			record.set(T1usrBsc.column_levelinstitute, EnumStatus.LevelUnknown.getId());
			record.set(T1usrBsc.column_remark, mgrSession);
		}
	}
	private void resolveLevelWithSession_Update(Record record,  T6MgrSession mgrSession){
		String typeLevel = mgrSession.getTypeleve();
		if (typeLevel.equals(EnumTypeLevel.Country.getName())) {
			record.set(T1usrBsc.column_typelevel, EnumStatus.LevelShow.getIdStr());
			
		} else if (typeLevel.equals(EnumTypeLevel.Province.getName())) {
			record.set(T1usrBsc.column_levelprovince, EnumStatus.LevelShow.getId())
			.set(T1usrBsc.column_province, mgrSession.ggProvince());
			
		} else if (typeLevel.equals(EnumTypeLevel.City.getName())) {
			record.set(T1usrBsc.column_levelcity, EnumStatus.LevelShow.getId())
			.set(T1usrBsc.column_province, mgrSession.ggProvince())
			.set(T1usrBsc.column_city, mgrSession.ggCity());
		} else if(typeLevel.equals(EnumTypeLevel.CenterInstitute.getName()))
		{
			record.set(T1usrBsc.column_levelinstitute, EnumStatus.LevelShow.getId())
			.set(T1usrBsc.column_institute, mgrSession.getInstitute());
		}
		else {
			record.set(T1usrBsc.column_levelinstitute, EnumStatus.LevelUnknown.getId());
			record.set(T1usrBsc.column_remark, mgrSession);
		}
	}
}
