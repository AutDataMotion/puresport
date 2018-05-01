package puresport.mvc.area;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import puresport.constant.ConstantInitMy;
import puresport.mvc.comm.ParamComm;

public class AreaService extends BaseService {

	private final static String tableName = "area";
	private final static String tableKey = "id";
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AreaService.class);
	
	public static final AreaService service = Enhancer.enhance(AreaService.class);
	
	public Area SelectById(Integer id){
		
		Area mdl = Area.dao.findFirst("select * from area where id=?", id);
		return mdl;
	}
	
	public List<Area> getProvince(){
		return  Area.dao.find(String.format("select * from %s where %s ", tableName, "parent_id=?"), 0);
	}
	
	public List<Area> getCityByProvince(ParamComm province){
		return  Area.dao.find(String.format("select * from %s where %s ", tableName, "parent_id=?"), province.getId());
	}
	
	public List<Record> getInstitute(ParamComm province){
		return Db.use(ConstantInitMy.db_dataSource_main)
				.find(String.format("select institute from %s group by institute ", "t1_usr_bsc"));
	}
}
