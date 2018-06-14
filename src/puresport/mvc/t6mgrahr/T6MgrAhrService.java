package puresport.mvc.t6mgrahr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.tuple.Tuple2;
import csuduc.platform.util.tuple.TupleUtil;
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumRoleType;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ValidateComm;

public class T6MgrAhrService extends BaseService {

	private final static String tableName = "t6_mgr_ahr";
	private final static String tableKey = "crdt_no";
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrService.class);

	public static final T6MgrAhrService service = Enhancer.enhance(T6MgrAhrService.class);

	public T6MgrAhr SelectById(Integer id) {

		T6MgrAhr mdl = T6MgrAhr.dao.findFirst("select * from t6MgrAhr where id=?", id);
		return mdl;
	}

	public boolean isExist(T6MgrAhr mdl) {
		Record user = ConfMain.db().findById(tableName, T6MgrAhr.column_crdt_no,
				(String) mdl.get(T6MgrAhr.column_crdt_no));
		if (null == user) {
			return false;
		}
		mdl.set(T6MgrAhr.column_usrid, user.get(T6MgrAhr.column_usrid));
		return true;
	}

	public List<T6MgrAhr> selectByPage(T6MgrSession mgrSession, ParamComm paramMdl) {
		final String roleStr = mgrSession.selectRoleStr();
		Long countTotal = ConfMain.db()
				.queryLong(String.format("select count(1) from %s where %s ", tableName, roleStr));
		paramMdl.setTotal(countTotal);
		List<T6MgrAhr> resList = new ArrayList<>();
		if (countTotal > 0) {
			resList = T6MgrAhr.dao.find(String.format(
					"select usrid,nm,crdt_tp, crdt_no, gnd,brth_dt,wrk_unit, post,typeleve, province, city,institute, mblph_no, email  from %s where %s  limit ?,?",
					tableName, roleStr), paramMdl.getPageIndex(), paramMdl.getPageSize());
		}
		return resList;
	}

	/**
	 * 将excel数据导入数据库
	 * 
	 * @param excelRows
	 * @param outFailedRows
	 * @return
	 */
	public Tuple2<Boolean, String> insertAdmin(T6MgrSession mgrSession, List<MdlExcelRow> excelRows) {
		if (CollectionUtils.isEmpty(excelRows)) {
			log.error("excelRows is null");
			return TupleUtil.tuple(false, "excel文件没有有效数据,请您检查");
		}
		// 记录失败的
		final ComOutMdl<Boolean> resBool = new ComOutMdl<>();
		resBool.set(true);
		final ComOutMdl<Integer> rowCnt = new ComOutMdl<>();
		rowCnt.set(0);
		StringBuilder resStr = new StringBuilder();
		excelRows.forEach(e -> {
			rowCnt.set(rowCnt.get()+1);
			Tuple2<Boolean, String> r= insertAdminToDb(mgrSession, e);
			if (!r.first) {
				resBool.set(false);;
				resStr.append(String.format("第%d行%s \\n", rowCnt.get(), r.second));
			}
			});
		return TupleUtil.tuple(resBool.get(), resStr.toString()); 
	}

	private Tuple2<Boolean, String> insertAdminToDb(T6MgrSession mgrSession,MdlExcelRow excelRow) {
		boolean res = false;
		try {
		// 校验输入
		if (StringUtil.invalidateLength(excelRow.getByIndex(0), 2, 64)) {
			// 因为可能有空行，当姓名没有的时候，直接默认未空行
			log.error("insertAdminToDb没有姓名，认为是空行:" + excelRow);
			return TupleUtil.tuple(true, "");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(1), 1, 8)
				|| ValidateComm.inv_column_crdt_tp(excelRow.getByIndex(1))) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "证件类型不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(2), 2, 20)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "证件号不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(3), 1, 4)
				|| ValidateComm.inv_column_gnd(excelRow.getByIndex(3))) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "性别不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(4), 2, 12)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "出生日期不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(5), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "工作单位不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(6), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "职务不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(7), 2, 64)
				|| ValidateComm.inv_column_typeleve(mgrSession, excelRow.getByIndex(7))) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "管理员级别不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(8), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "省/直辖市不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(9), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "市/区不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(10), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "手机号不符合要求");
		}
		if (StringUtil.invalidateLength(excelRow.getByIndex(11), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return TupleUtil.tuple(false, "邮箱不符合要求");
		}
		// 根据手机号匹配，没有插入、已有更新
		String crdt_number = StringUtil.replaceExcelBlank(excelRow.getByIndex(2));
		
		Record admin;
		
			admin = new Record().set(T6MgrAhr.column_usr_tp, EnumRoleType.Admin.getName())
					.set(T6MgrAhr.column_usr_nm, excelRow.getByIndex(0))
					.set(T6MgrAhr.column_nm, excelRow.getByIndex(0))
					.set(T6MgrAhr.column_crdt_tp, excelRow.getByIndex(1))
					.set(T6MgrAhr.column_crdt_no, crdt_number)
					.set(T6MgrAhr.column_gnd, excelRow.getByIndex(3))
					.set(T6MgrAhr.column_brth_dt, excelRow.getByIndex(4))
					.set(T6MgrAhr.column_wrk_unit, excelRow.getByIndex(5))
					.set(T6MgrAhr.column_post, excelRow.getByIndex(6))
					.set(T6MgrAhr.column_typeleve, excelRow.getByIndex(7))
					.set(T6MgrAhr.column_province, excelRow.getByIndex(8))
					.set(T6MgrAhr.column_city, excelRow.getByIndex(9))
//					.set(T6MgrAhr.column_institute, excelRow.getByIndex(10))
					.set(T6MgrAhr.column_pswd, DESUtil.encrypt(crdt_number.substring(crdt_number.length() - 6), ConstantInitMy.SPKEY))
					.set(T6MgrAhr.column_mblph_no, excelRow.getByIndex(10))
					.set(T6MgrAhr.column_email, excelRow.getByIndex(11));
			
			res = ConfMain.db().saveOtherwiseUpdate(tableName, tableKey, admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return TupleUtil.tuple(false, "有失败情况，请您检查数据后重试");
		}
		return  TupleUtil.tuple(res, "");
	}
}
