package puresport.mvc.area;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class AreaService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AreaService.class);
	
	public static final AreaService service = Enhancer.enhance(AreaService.class);
	
	public Area SelectById(Integer id){
		
		Area mdl = Area.dao.findFirst("select * from area where id=?", id);
		return mdl;
	}
}
