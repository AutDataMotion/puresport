package puresport.mvc.area;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.mvc.comm.ParamComm;

public class AreaService extends BaseService {

	private final static String tableName = "dt_area";
	private final static String tableKey = "id";
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AreaService.class);
	
	public static final AreaService service = Enhancer.enhance(AreaService.class);
	
	public Area SelectById(Integer id){
		
		Area mdl = Area.dao.findFirst("select * from area where id=?", id);
		return mdl;
	}
	
	public List<Area> getProvince(){
		return  Area.dao.find(String.format("select id, name from %s where %s ", tableName, "parent_id=?"), 0);
	}
	
	public List<Area> getCityByProvince(Integer provinceId){
		return  Area.dao.find(String.format("select id, name from %s where %s ", tableName, "parent_id=?"), provinceId);
	}
	
	public List<Record> getInstitute(){
		return ConfMain.db().find(String.format("select institute from t1_usr_bsc group by institute "));
	}
	
	public List<Record> getProject(){
		return ConfMain.db().find(String.format("select spt_prj from t1_usr_bsc group by spt_prj "));
	}

	public List<Record> getQuestionType(){
		return ConfMain.db().find(String.format("select prblm_tp from t9_tstlib group by prblm_tp "));
	}
}
