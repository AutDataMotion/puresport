package puresport.mvc.t6mgrahr;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T6MgrAhrService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrService.class);
	
	public static final T6MgrAhrService service = Enhancer.enhance(T6MgrAhrService.class);
	
	public T6MgrAhr SelectById(Integer id){
		
		T6MgrAhr mdl = T6MgrAhr.dao.findFirst("select * from t6MgrAhr where id=?", id);
		return mdl;
	}
}
