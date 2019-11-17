package puresport.mvc.t15creditinf;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T15CreditInfService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15CreditInfService.class);
	
	public static final T15CreditInfService service = Enhancer.enhance(T15CreditInfService.class);
	
	public T15CreditInf SelectById(Integer id){
		
		T15CreditInf mdl = T15CreditInf.dao.findFirst("select * from t15CreditInf where id=?", id);
		return mdl;
	}
}
