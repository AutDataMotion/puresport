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
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumRoleType;
import puresport.mvc.comm.ParamComm;

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
	public boolean insertAdmin(List<MdlExcelRow> excelRows, final ComOutMdl<List<MdlExcelRow>> outFailedMdl) {
		if (CollectionUtils.isEmpty(excelRows)) {
			log.error("excelRows is null");
			return false;
		}
		// 根据手机号匹配，没有插入、已有更新
		// 记录失败的
		List<MdlExcelRow> failedRows = excelRows.stream().filter(e -> !insertAdminToDb(e)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(failedRows)) {
			return true;
		}
		outFailedMdl.set(failedRows);
		return false;
	}

	private boolean insertAdminToDb(MdlExcelRow excelRow) {
		// 校验输入
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(0)), 2, 64)) {
			// 因为可能有空行，当姓名没有的时候，直接默认未空行
			log.error("insertAdminToDb没有姓名，认为是空行:" + excelRow);
			return true;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(1)), 1, 8)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(2)), 2, 20)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(3)), 1, 4)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(4)), 2, 64)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(5)), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(6)), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(7)), 2, 64)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(8)), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(9)), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(10)), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		if (StringUtil.invalidateLength(StringUtil.replaceExcelBlank(excelRow.getByIndex(11)), 2, 128)) {
			log.error("insertAdminToDb数据校验失败:" + excelRow);
			return false;
		}
		// 根据手机号匹配，没有插入、已有更新
		String crdt_number = StringUtil.replaceExcelBlank(excelRow.getByIndex(2));
		if (crdt_number.length() < 18) {
			return false;
		}
		Record admin;
		try {
			admin = new Record().set(T6MgrAhr.column_usr_tp, EnumRoleType.Admin.getName())
					.set(T6MgrAhr.column_usr_nm, StringUtil.replaceExcelBlank(excelRow.getByIndex(0)))
					.set(T6MgrAhr.column_nm, StringUtil.replaceExcelBlank(excelRow.getByIndex(0)))
					.set(T6MgrAhr.column_crdt_tp, StringUtil.replaceExcelBlank(excelRow.getByIndex(1)))
					.set(T6MgrAhr.column_crdt_no, crdt_number)
					.set(T6MgrAhr.column_gnd, StringUtil.replaceExcelBlank(excelRow.getByIndex(3)))
					.set(T6MgrAhr.column_brth_dt, excelRow.getByIndex(4))
					.set(T6MgrAhr.column_wrk_unit, StringUtil.replaceExcelBlank(excelRow.getByIndex(5)))
					.set(T6MgrAhr.column_post, StringUtil.replaceExcelBlank(excelRow.getByIndex(6)))
					.set(T6MgrAhr.column_typeleve, StringUtil.replaceExcelBlank(excelRow.getByIndex(7)))
					.set(T6MgrAhr.column_province, StringUtil.replaceExcelBlank(excelRow.getByIndex(8)))
					.set(T6MgrAhr.column_city, StringUtil.replaceExcelBlank(excelRow.getByIndex(9)))
//					.set(T6MgrAhr.column_institute, excelRow.getByIndex(10))
					.set(T6MgrAhr.column_pswd, DESUtil.encrypt(crdt_number.substring(crdt_number.length() - 6), ConstantInitMy.SPKEY))
					.set(T6MgrAhr.column_mblph_no, StringUtil.replaceExcelBlank(excelRow.getByIndex(10)))
					.set(T6MgrAhr.column_email, StringUtil.replaceExcelBlank(excelRow.getByIndex(11)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return false;
		}
		return  ConfMain.db().saveOtherwiseUpdate(tableName, tableKey, admin);
	}
}
