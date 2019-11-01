package puresport.mvc.t1usrbsc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.ComUtil;
import csuduc.platform.util.RegexUtils;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.tuple.Tuple2;
import csuduc.platform.util.tuple.Tuple3;
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
			if (StringUtil.notEmpty(paramMdl.getName3())) {
				whereStr.append(" and province like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName3()));
			}
			if (StringUtil.notEmpty(paramMdl.getName4())) {
				whereStr.append(" and city like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName4()));
			}
			if (StringUtil.notEmpty(paramMdl.getName5())) {
				whereStr.append(" and usr_tp like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName5()));
			}
			if (StringUtil.notEmpty(paramMdl.getName6())) {
				whereStr.append(" and spt_prj like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName6()));
			}
			if (StringUtil.notEmpty(paramMdl.getName7())) {
				if("国家级".equals(paramMdl.getName7()))
				{
					whereStr.append(" and (levelinstitute = 2 or typelevel = '2') ");
				}
				if("省级".equals(paramMdl.getName7()))
				{
					whereStr.append(" and levelprovince = 2 ");
//					listArgs.add(getStringLikeLeft(paramMdl.getName7()));
				}
				if("市级".equals(paramMdl.getName7()))
				{
					whereStr.append(" and levelcity = 2 ");
				}
//				whereStr.append(" and typelevel like ? ");
//				listArgs.add(getStringLikeLeft(paramMdl.getName7()));
			}
			if (StringUtil.notEmpty(paramMdl.getName8())) {
				whereStr.append(" and gnd like ? ");
				listArgs.add(getStringLikeLeft(paramMdl.getName8()));
			}
			return whereStr.toString();
	}
	public List<T1usrBsc> selectByPage(T6MgrSession mgrSession, ParamComm paramMdl) {
		final String roleStr = mgrSession.selectRoleStr_UserBasic();
		List<Object> listArgs = new ArrayList<>();
		final String searchStr = getSearchWhere(mgrSession, paramMdl, listArgs);
		Long countTotal = ConfMain.db()
				.queryLong(String.format("select count(1) from %s where %s %s", tableName, roleStr, searchStr), listArgs.toArray());
		log.debug("selectByPage----"+countTotal+","+paramMdl.getDraw());
		
		paramMdl.setTotal(countTotal);
		List<T1usrBsc> resList = new ArrayList<>();
		if (countTotal > 0) {
			if(paramMdl.getExportall().equals("1"))//全量导出
			{
				listArgs.add(0);
				listArgs.add(countTotal);
			}
			else {
				listArgs.add(paramMdl.getPageIndex());
				listArgs.add(paramMdl.getPageSize());
			}
			
//			String test = String.format(
//					"select usrid,usr_tp, nm,crdt_tp, crdt_no,department,post, gnd,brth_dt,spt_prj, typelevel, province, city,institute, mblph_no, email,levelprovince,levelcity,levelinstitute  from %s where %s %s  limit ?,?",
//					tableName, roleStr, searchStr);
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
//	private static String sql_score = "select u.*, s.exam_nm as exam_nm, s.exam_grd as exam_grd, (CASE WHEN s.exam_grd >= 80 THEN '及格'  WHEN s.exam_grd is null THEN '未考试'  ELSE '不及格' END) as passed "
//			+ " from t1_usr_bsc u  left join ("
//			+ " select usrid, exam_nm, max(exam_grd) as exam_grd from t11_exam_stat where exam_st='1' group by usrid, exam_nm "
//			+ " ) as s on u.usrid = s.usrid  where 1=1 %s  limit ?,?";
	
//	private static String sql_score_total = "select count(1) "
//			+ " from t1_usr_bsc u  left join ("
//			+ " select usrid, exam_nm, max(exam_grd) as exam_grd from t11_exam_stat where exam_st='1' group by usrid, exam_nm "
//			+ " ) as s on u.usrid = s.usrid  where 1=1  %s ";

	private static String sql_score = "select u.*, s.exam_nm as exam_nm, s.exam_grd as exam_grd, (CASE WHEN s.exam_grd >= 80 THEN '及格'  WHEN s.exam_grd is null THEN '未考试'  ELSE '不及格' END) as passed "
			+ " from t1_usr_bsc u  left join ("
			+ " select usrid, exam_nm, exam_grd from t12_highest_score"
			+ " ) as s on u.usrid = s.usrid  where 1=1 %s  limit ?,?";
	
	private static String sql_score_total = "select count(1) "
	+ " from t1_usr_bsc u  left join ("
	+ " select usrid, exam_nm, exam_grd from t12_highest_score"
	+ " ) as s on u.usrid = s.usrid  where 1=1  %s ";
			
	public List<Record> selectScoreByPage(T6MgrSession mgrSession, ParamComm paramMdl) {
		
		List<Object> listArgs = new ArrayList<>();
		String whereSql = getSearchWhereSta(mgrSession, paramMdl, listArgs, true);
		Long countTotal = ConfMain.db().queryLong(String.format(sql_score_total, whereSql), listArgs.toArray());
		paramMdl.setTotal(countTotal);
		List<Record> userScoreRecords = null;
		if (countTotal > 0) {
			listArgs.add(paramMdl.getPageIndex());
			listArgs.add(paramMdl.getPageSize());
			userScoreRecords = ConfMain.db().find(String.format(sql_score, whereSql) , listArgs.toArray());
		} else {
			userScoreRecords = new ArrayList<>();
		}
		
		return userScoreRecords;
	}

	private final static String defSelect = "全部";

	private final static String getStringLikeLeft(String s) {
		return s + "%";
	}

	public static String getSearchWhereSta(T6MgrSession mgrSession, ParamComm paramMdl, List<Object> listArgs, boolean isAddRoleWhere) {
		StringBuilder whereStr = new StringBuilder();
		if (StringUtil.notEmptyOrDefault(paramMdl.getName1(), defSelect)) {
			whereStr.append(" and province like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName1()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName2(), defSelect)) {
			whereStr.append(" and city like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName2()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName3(), defSelect)) {
			whereStr.append(" and institute like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName3()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName4(), defSelect)) {
			whereStr.append(" and nm like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName4()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName5(), defSelect)) {
			whereStr.append(" and crdt_no like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName5()));
		}
		if (StringUtil.notEmptyOrLikeDefault(paramMdl.getName6(), defSelect)) {
			whereStr.append(" and usr_tp like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName6()));
		}
		if (StringUtil.notEmptyOrDefault(paramMdl.getName7(), defSelect)) {
			whereStr.append(" and spt_prj like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName7()));
		}
		if (StringUtil.notEmptyOrLikeDefault(paramMdl.getName8(), defSelect)) {
			whereStr.append(" and exam_nm like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName8()));
		}
		/*if (StringUtil.notEmptyOrDefault(paramMdl.getName9(), defSelect)) {
			whereStr.append(" and exam_grd > ? ");
			listArgs.add(paramMdl.getName9());
		}*/
		if (StringUtil.notEmptyOrLikeDefault(paramMdl.getName10(), defSelect)) {
			if (paramMdl.getName10().equals("合格")) {
				whereStr.append(" and exam_grd >= 80 ");
			}else if (paramMdl.getName10().equals("不合格")){
				whereStr.append(" and exam_grd < 80 and exam_grd>=0 ");
			} else {
				whereStr.append(" and exam_grd is  null ");
			}
		}
		if (StringUtil.notEmptyOrLikeDefault(paramMdl.getName11(), defSelect)) {
			whereStr.append(" and gnd like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName11()));
		}
		
		if (isAddRoleWhere) {
			whereStr.append(" and ")
			.append(mgrSession.selectRoleStr_UserBasic());
		}
		return whereStr.toString();
	}

	private static String sql_prj = "select * from prjGroupStatis where 1=1 %s limit ?,?";
	private static String sql_prj_total = "select count(1) from prjGroupStatis where 1=1 %s ";

	/**
	 * 统计-项目合格率
	 * 
	 * @param paramMdl
	 * @return
	 */
	public List<Record> selectPassedPercent(T6MgrSession mgrSession, ParamComm paramMdl) {
		List<Object> listArgs = new ArrayList<>();
		String whereSql = getSearchWhereSta(mgrSession, paramMdl, listArgs, false);
		Long countTotal = ConfMain.db().queryLong(String.format(sql_prj_total, whereSql), listArgs.toArray());
		paramMdl.setTotal(countTotal);
		List<Record> records = null;
		if (countTotal <= 0) {
			return new ArrayList<>();
		} else {
			listArgs.add(paramMdl.getPageIndex());
			listArgs.add(paramMdl.getPageSize());
			records = ConfMain.db().find(String.format(sql_prj, whereSql) , listArgs.toArray());
		} 
		
		if (CollectionUtils.isEmpty(records)) return new ArrayList<>();

		records.stream().forEach(e -> {
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
		return records;
	}

	private static String sql_problem = "select * from problemStatis where 1=1 %s limit ?,? ";
	private static String sql_problem_total = "select count(1) from problemStatis where 1=1 %s ";

	public static String getQuestionWhere(ParamComm paramMdl, List<Object> listArgs) {
		StringBuilder whereStr = new StringBuilder();
		if (StringUtil.notEmptyOrDefault(paramMdl.getName1(), defSelect)) {
			whereStr.append(" and prblm_tp like ? ");
			listArgs.add(getStringLikeLeft(paramMdl.getName1()));
		}
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
		Long countTotal = ConfMain.db().queryLong(String.format(sql_problem_total, whereSql), listArgs.toArray());
		paramMdl.setTotal(countTotal);
		List<Record> records = null;
		if (countTotal <= 0) {
			return new ArrayList<>();
		} else {
			listArgs.add(paramMdl.getPageIndex());
			listArgs.add(paramMdl.getPageSize());
			records = ConfMain.db().find(String.format(sql_problem, whereSql) , listArgs.toArray());
		} 
		
		if (CollectionUtils.isEmpty(records)) return new ArrayList<>();

		records.stream().forEach(e -> {
			Long cntTotal = e.getBigInteger("cntall").longValue();
			Long cntWrong = e.getBigInteger("cntwrong").longValue();
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
		return records;
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
			Tuple3<Boolean, Integer, String> r = insertRowToDb(mgrSession, e);
			if (!r.first) {
				resBool.set(false);
				resStr.append(String.format("第%d行第%d列 %s \n", rowCnt.get(), r.second, r.third));
			}
		});
		return TupleUtil.tuple(resBool.get(), resStr.toString());
	}

	private Tuple3<Boolean,Integer, String> insertRowToDb(T6MgrSession mgrSession, MdlExcelRow excelRow) {
		boolean res = false;
		String resTips = "";
		try {
			// 校验输入
			if (StringUtil.invalidateLength(excelRow.getByIndex(0), 2, 64)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				// 因为可能有空行，当姓名没有的时候，直接默认未空行
				return TupleUtil.tuple(true, 0, "");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(1), 1, 8)
					|| ValidateComm.inv_column_crdt_tp(excelRow.getByIndex(1))) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, 2, "证件类型不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(2), 8, 20)
					|| !RegexUtils.checkDigitAlpha(excelRow.getByIndex(2))) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false,3,  "证件号不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(3), 1, 4)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, 4, "性别不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(4), 2, 10)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, 5, "出生日期不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(5), 2, 18)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, 6, "手机号不符合要求");
			}
			if (StringUtil.invalidateLength(excelRow.getByIndex(6), 2, 128)) {
				log.error("insertRowToDb数据校验失败:" + excelRow);
				return TupleUtil.tuple(false, 7,  "邮箱不符合要求");
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
			return TupleUtil.tuple(false, 0, "有失败情况，请您检查数据后重试");

		}
		return TupleUtil.tuple(res, 0, "");
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
	
	public boolean addUserBsc(T1userBscDTO dto) {
		
		if (Objects.isNull(dto)) {
			return false;
		}
		Record dbRow;
		try {
			dbRow = new Record()
					.set(T1usrBsc.column_usr_tp, EnumRoleType.Sporter.getName())
					.set(T1usrBsc.column_usr_nm, dto.getMblph_no())
					.set(T1usrBsc.column_nm, dto.getNm())
					.set(T1usrBsc.column_crdt_tp, "身份证")
					.set(T1usrBsc.column_crdt_no, dto.getCrdt_no())
					.set(T1usrBsc.column_gnd, dto.getGnd()) // 性别
					.set(T1usrBsc.column_brth_dt, dto.getBrth_dt())
					.set(T1usrBsc.column_pswd,
							DESUtil.encrypt(dto.getPasswd(), ConstantInitMy.SPKEY))// 密码默认身份证后6位
					.set(T1usrBsc.column_mblph_no, dto.getMblph_no())
					.set(T1usrBsc.column_email, dto.getEmail())
					//.set(T1usrBsc.column_cty_prov_city_mgrid, mgrSession.getUsrid())
					// .set(T1usrBsc.column_typelevel,
					// mgrSession.getTypeleve())
					.set(T1usrBsc.column_province, dto.getProvince())
					.set(T1usrBsc.column_city, dto.getCity());
			
			if ( dto.getEmailValCode()!= null) {
				dbRow.set(T1usrBsc.column_email_val, 1);
			}
			if ( dto.getMblphValCode()!= null) {
				dbRow.set(T1usrBsc.column_mblph_val, 1);
			}
			
			return ConfMain.db().save(tableName, tableKey, dbRow);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return false;
		}
	}
}
