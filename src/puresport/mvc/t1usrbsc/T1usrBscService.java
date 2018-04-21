package puresport.mvc.t1usrbsc;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import csuduc.platform.util.ComOutMdl;
import puresport.applicat.MdlExcelRow;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumRoleType;
import puresport.mvc.t6mgrahr.ParamComm;
import puresport.mvc.t6mgrahr.T6MgrAhr;

public class T1usrBscService extends BaseService {
	private final static String tableName = "t1_usr_bsc";
	private final static String tableKey = "usr_nm";

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscService.class);

	public static final T1usrBscService service = Enhancer.enhance(T1usrBscService.class);

	public T1usrBsc SelectById(Integer id) {

		T1usrBsc mdl = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where id=?", id);
		return mdl;
	}

	public List<T1usrBsc> selectByPage(ParamComm paramMdl) {
		return T1usrBsc.dao.find(String.format("select * from %s where %s  limit ?,?", tableName, "1=1"),
				paramMdl.getPageIndex(), paramMdl.getPageSize());
	}
	
	public List<ResUserScore> selectScoreByPage(ParamComm paramMdl) {
		List<T1usrBsc> userInfos =  T1usrBsc.dao.find(String.format("select * from %s where %s  limit ?,?", tableName, "1=1"),
				paramMdl.getPageIndex(), paramMdl.getPageSize());
		
		return userInfos.stream().map(e->getUserScore(e)).collect(Collectors.toList());
	}

	public  ResUserScore getUserScore(T1usrBsc user){
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
	/**
	 * 将excel数据导入数据库
	 * 
	 * @param excelRows
	 * @param outFailedRows
	 * @return
	 */
	public boolean insertFromExcel(List<MdlExcelRow> excelRows, final ComOutMdl<List<MdlExcelRow>> outFailedMdl) {
		if (CollectionUtils.isEmpty(excelRows)) {
			log.error("excelRows is null");
			return false;
		}
		// 根据手机号匹配，没有插入、已有更新
		// 记录失败的
		List<MdlExcelRow> failedRows = excelRows.stream().filter(e -> !insertRowToDb(e)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(failedRows)) {
			return true;
		}
		outFailedMdl.set(failedRows);
		return false;
	}

	private boolean insertRowToDb(MdlExcelRow excelRow) {
		// 根据手机号匹配，没有插入、已有更新
		String crdt_number = excelRow.getByIndex(2);// 身份证号
		if (crdt_number.length() < 18) {
			return false;
		}
		Record dbRow = new Record().set(T1usrBsc.column_usr_tp, EnumRoleType.Sporter.getName())
				.set(T1usrBsc.column_usr_nm, excelRow.getByIndex(5))// 用户账户名：手机号
				.set(T1usrBsc.column_nm, excelRow.getByIndex(0))
				.set(T1usrBsc.column_crdt_tp, excelRow.getByIndex(1))
				.set(T1usrBsc.column_crdt_no, crdt_number)
				.set(T1usrBsc.column_gnd, excelRow.getByIndex(3))
				.set(T1usrBsc.column_brth_dt, excelRow.getByIndex(4))
				.set(T1usrBsc.column_pswd, crdt_number.substring(crdt_number.length() - 6))// 密码默认身份证后6位
				.set(T1usrBsc.column_mblph_no, excelRow.getByIndex(5))
				.set(T1usrBsc.column_email, excelRow.getByIndex(6));
		return Db.use(ConstantInitMy.db_dataSource_main).saveOtherwiseUpdate(tableName, tableKey, dbRow);
	}
}
