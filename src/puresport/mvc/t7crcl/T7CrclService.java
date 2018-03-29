package puresport.mvc.t7crcl;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T7CrclService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7CrclService.class);
	
	public static final T7CrclService service = Enhancer.enhance(T7CrclService.class);
	
	public T7Crcl SelectById(Integer id){
		
		T7Crcl mdl = T7Crcl.dao.findFirst("select * from t7Crcl where id=?", id);
		return mdl;
	}
}
