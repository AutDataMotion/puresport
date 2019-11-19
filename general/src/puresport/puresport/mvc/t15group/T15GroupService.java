package puresport.mvc.t15group;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T15GroupService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15GroupService.class);
	
	public static final T15GroupService service = Enhancer.enhance(T15GroupService.class);
	
	public T15Group SelectById(Integer id){
		
		T15Group mdl = T15Group.dao.findFirst("select * from t15Group where id=?", id);
		return mdl;
	}
}
