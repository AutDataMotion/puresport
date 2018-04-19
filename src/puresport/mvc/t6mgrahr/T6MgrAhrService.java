package puresport.mvc.t6mgrahr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import csuduc.platform.util.ComOutMdl;
import puresport.applicat.MdlExcelRow;

public class T6MgrAhrService extends BaseService {

	private final static String tableName = "t6MgrAhr";
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrService.class);

	public static final T6MgrAhrService service = Enhancer.enhance(T6MgrAhrService.class);

	public T6MgrAhr SelectById(Integer id) {

		T6MgrAhr mdl = T6MgrAhr.dao.findFirst("select * from t6MgrAhr where id=?", id);
		return mdl;
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
		List<MdlExcelRow> failedRows = excelRows.stream().filter(e -> insertAdminToDb(e)).collect(Collectors.toList());
		
		if (CollectionUtils.isEmpty(failedRows)) {
			return true;
		}
		outFailedMdl.set(failedRows);
		return false;
	}

	private boolean insertAdminToDb(MdlExcelRow excelRow) {
		// 根据手机号匹配，没有插入、已有更新
		System.out.println(excelRow);
		Record admin = new Record().set(T6MgrAhr.column_nm, excelRow.getByIndex(0))
				.set(T6MgrAhr.column_crdt_tp, excelRow.getByIndex(1))
				.set(T6MgrAhr.column_crdt_no, excelRow.getByIndex(2)).set(T6MgrAhr.column_gnd, excelRow.getByIndex(3))
				.set(T6MgrAhr.column_brth_dt, excelRow.getByIndex(4))
				.set(T6MgrAhr.column_wrk_unit, excelRow.getByIndex(5)).set(T6MgrAhr.column_post, excelRow.getByIndex(6))
//				.set(T6MgrAhr.column_typeleve, excelRow.getByIndex(7)).set("province", excelRow.getByIndex(8))
				.set("city", excelRow.getByIndex(9)).set("institute", excelRow.getByIndex(10))
				.set(T6MgrAhr.column_mblph_no, excelRow.getByIndex(11))
				.set(T6MgrAhr.column_email, excelRow.getByIndex(12));
		Db.save(tableName, admin);
		return true;
	}
}
