package puresport.mvc.r4assc;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class R4AsscService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R4AsscService.class);
	
	public static final R4AsscService service = Enhancer.enhance(R4AsscService.class);
	
	public R4Assc SelectById(Integer id){
		
		R4Assc mdl = R4Assc.dao.findFirst("select * from r4Assc where id=?", id);
		return mdl;
	}
}
