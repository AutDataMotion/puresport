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

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.ComUtil;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.CommDES;
import csuduc.platform.util.encrypt.DESUtil;
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumRoleType;
import puresport.constant.EnumTypeLevel;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.t6mgrahr.T6MgrSession;

public class T1usrBscService extends BaseService {
	private final static String tableName = "t1_usr_bsc";
	private final static String tableKey = "crdt_no";

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscService.class);

	public static final T1usrBscService service = Enhancer.enhance(T1usrBscService.class);

	public T1usrBsc SelectById(Integer id) {

		T1usrBsc mdl = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where id=?", id);
		return mdl;
	}

	public boolean isExist(T1usrBsc mdl) {
		Record sporter = ConfMain.db().findById(tableName, T1usrBsc.column_mblph_no,
				(String) mdl.get(T1usrBsc.column_mblph_no));
		if (null == sporter) {
			return false;
		}
		mdl.set(T1usrBsc.column_usrid, sporter.get(T1usrBsc.column_usrid));
		return true;
	}

	public List<T1usrBsc> selectByPage(T6MgrSession mgrSession, ParamComm paramMdl) {
		final String roleStr = mgrSession.selectRoleStr();
		Long countTotal = ConfMain.db()
				.queryLong(String.format("select count(1) from %s where %s ", tableName, roleStr));
		paramMdl.setTotal(countTotal);
		List<T1usrBsc> resList = new ArrayList<>();
		if (countTotal > 0) {
			resList = T1usrBsc.dao.find(String.format(
					"select usrid,nm,crdt_tp, crdt_no, gnd,brth_dt,spt_prj, typelevel, province, city,institute, mblph_no, email  from %s where %s  limit ?,?",
					tableName, roleStr), paramMdl.getPageIndex(), paramMdl.getPageSize());
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
			+ " from t1_usr_bsc u  left join t11_exam_stat s on u.usrid = s.usrid  where 1=1 ";

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
			whereStr.append(" and province like ? ");
			listArgs.add(getStringLikeLeft(mgrSession.getProvince()));

			if (StringUtil.notEmptyOrDefault(paramMdl.getName2(), defSelect)) {
				whereStr.append(" and city like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName2()));
			}
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			whereStr.append(" and province like ? ");
			listArgs.add(getStringLikeLeft(mgrSession.getProvince()));
			whereStr.append(" and city like ? ");
			listArgs.add(getStringLikeLeft(mgrSession.getCity()));

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
	public boolean insertFromExcel(T6MgrSession mgrSession, List<MdlExcelRow> excelRows,
			final ComOutMdl<List<MdlExcelRow>> outFailedMdl) {
		if (CollectionUtils.isEmpty(excelRows) || Objects.isNull(mgrSession)) {
			log.error("excelRows or  mgrSession is null");
			return false;
		}
		// 根据手机号匹配，没有插入、已有更新
		// 记录失败的
		List<MdlExcelRow> failedRows = excelRows.stream().filter(e -> !insertRowToDb(mgrSession, e))
				.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(failedRows)) {
			return true;
		}
		outFailedMdl.set(failedRows);
		return false;
	}

	private boolean insertRowToDb(T6MgrSession mgrSession, MdlExcelRow excelRow) {
		// 校验输入
		if (StringUtil.invalidateLength(excelRow.getByIndex(0), 2, 64)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			// 因为可能有空行，当姓名没有的时候，直接默认未空行
			return true;
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(1), 1, 8)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(2), 2, 20)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(3), 1, 4)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(4), 2, 16)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(5), 2, 16)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(6), 2, 128)) {
			log.error("insertRowToDb数据校验失败:" + excelRow);
			return false;
		}
		// 根据手机号匹配，没有插入、已有更新
		String crdt_number = excelRow.getByIndex(2);// 身份证号
		if (crdt_number.length() < 18) {
			return false;
		}
		Record dbRow;
		try {
			dbRow = new Record().set(T1usrBsc.column_usr_tp, EnumRoleType.Sporter.getName())
					.set(T1usrBsc.column_usr_nm, excelRow.getByIndex(5))// 用户账户名：手机号
					.set(T1usrBsc.column_nm, excelRow.getByIndex(0)).set(T1usrBsc.column_crdt_tp, excelRow.getByIndex(1))
					.set(T1usrBsc.column_crdt_no, crdt_number).set(T1usrBsc.column_gnd, excelRow.getByIndex(3))
					.set(T1usrBsc.column_brth_dt, excelRow.getByIndex(4))
					.set(T1usrBsc.column_pswd,
							DESUtil.encrypt(crdt_number.substring(crdt_number.length() - 6), ConstantInitMy.SPKEY))// 密码默认身份证后6位
					.set(T1usrBsc.column_mblph_no, excelRow.getByIndex(5))
					.set(T1usrBsc.column_email, excelRow.getByIndex(6))

					.set(T1usrBsc.column_cty_prov_city_mgrid, mgrSession.getUsrid())
					.set(T1usrBsc.column_typelevel, mgrSession.getTypeleve())
					.set(T1usrBsc.column_province, mgrSession.ggProvince())
					.set(T1usrBsc.column_city, mgrSession.ggCity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return false;

		}
		return ConfMain.db().saveOtherwiseUpdate(tableName, tableKey, dbRow);
	}
}
