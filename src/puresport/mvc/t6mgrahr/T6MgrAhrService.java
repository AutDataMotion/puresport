package puresport.mvc.t6mgrahr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import csuduc.platform.util.ComOutMdl;
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumRoleType;
import puresport.mvc.comm.ParamComm;

public class T6MgrAhrService extends BaseService {

	private final static String tableName = "t6_mgr_ahr";
	private final static String tableKey = "usr_nm";
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrService.class);

	public static final T6MgrAhrService service = Enhancer.enhance(T6MgrAhrService.class);

	public T6MgrAhr SelectById(Integer id) {

		T6MgrAhr mdl = T6MgrAhr.dao.findFirst("select * from t6MgrAhr where id=?", id);
		return mdl;
	}
	
	public boolean isExist(T6MgrAhr mdl){
		Record user = ConfMain.db().findById(tableName, T6MgrAhr.column_mblph_no, (String)mdl.get(T6MgrAhr.column_mblph_no));
		if (null == user) {
			return false;
		}
		mdl.set(T6MgrAhr.column_usrid, user.get(T6MgrAhr.column_usrid));
		return true ;
	 }

	public List<T6MgrAhr> selectByPage(ParamComm paramMdl){
		Long countTotal = ConfMain.db().queryLong(String.format("select count(1) from %s ", tableName));
		paramMdl.setTotal(countTotal);
		List<T6MgrAhr> resList =new ArrayList<>();
		if (countTotal > 0) {
			resList  =  T6MgrAhr.dao.find(String.format("select usrid,nm,crdt_tp, crdt_no, gnd,brth_dt,wrk_unit, post,typeleve, province, city,institute, mblph_no, email  from %s where %s  limit ?,?", tableName, "1=1"),
					paramMdl.getPageIndex(), paramMdl.getPageSize());
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
		// 根据手机号匹配，没有插入、已有更新
		System.out.println(excelRow);
		String crdt_number  = excelRow.getByIndex(2);
		if (crdt_number.length()<18) {
			return false;
		}
		Record admin = new Record()
				.set(T6MgrAhr.column_usr_tp, EnumRoleType.Admin.getName())
				.set(T6MgrAhr.column_usr_nm, excelRow.getByIndex(11))
				.set(T6MgrAhr.column_nm, excelRow.getByIndex(0))
				.set(T6MgrAhr.column_crdt_tp, excelRow.getByIndex(1))
				.set(T6MgrAhr.column_crdt_no, crdt_number)
				.set(T6MgrAhr.column_gnd, excelRow.getByIndex(3))
				.set(T6MgrAhr.column_brth_dt, excelRow.getByIndex(4))
				.set(T6MgrAhr.column_wrk_unit, excelRow.getByIndex(5))
				.set(T6MgrAhr.column_post, excelRow.getByIndex(6))
//				.set(T6MgrAhr.column_typeleve, excelRow.getByIndex(7))
//				.set(T6MgrAhr.column_province, excelRow.getByIndex(8))
//				.set(T6MgrAhr.column_city, excelRow.getByIndex(9))
//				.set(T6MgrAhr.column_institute, excelRow.getByIndex(10))
				.set(T6MgrAhr.column_pswd,  crdt_number.substring(crdt_number.length()-6))
				.set(T6MgrAhr.column_mblph_no, excelRow.getByIndex(11))
				.set(T6MgrAhr.column_email, excelRow.getByIndex(12));
		return Db.use(ConstantInitMy.db_dataSource_main).saveOtherwiseUpdate(tableName, tableKey,admin);
	}
}
