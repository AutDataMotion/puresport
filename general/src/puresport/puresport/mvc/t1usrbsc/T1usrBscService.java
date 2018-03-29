package puresport.mvc.t1usrbsc;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T1usrBscService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscService.class);
	
	public static final T1usrBscService service = Enhancer.enhance(T1usrBscService.class);
	
	public T1usrBsc SelectById(Integer id){
		
		T1usrBsc mdl = T1usrBsc.dao.findFirst("select * from t1usrBsc where id=?", id);
		return mdl;
	}
}
