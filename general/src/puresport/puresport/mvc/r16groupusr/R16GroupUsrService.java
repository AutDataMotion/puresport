package puresport.mvc.r16groupusr;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class R16GroupUsrService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R16GroupUsrService.class);
	
	public static final R16GroupUsrService service = Enhancer.enhance(R16GroupUsrService.class);
	
	public R16GroupUsr SelectById(Integer id){
		
		R16GroupUsr mdl = R16GroupUsr.dao.findFirst("select * from r16GroupUsr where id=?", id);
		return mdl;
	}
}
