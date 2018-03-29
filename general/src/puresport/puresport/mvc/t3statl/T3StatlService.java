package puresport.mvc.t3statl;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T3StatlService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T3StatlService.class);
	
	public static final T3StatlService service = Enhancer.enhance(T3StatlService.class);
	
	public T3Statl SelectById(Integer id){
		
		T3Statl mdl = T3Statl.dao.findFirst("select * from t3Statl where id=?", id);
		return mdl;
	}
}
