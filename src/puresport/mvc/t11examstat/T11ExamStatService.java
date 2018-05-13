package puresport.mvc.t11examstat;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T11ExamStatService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11ExamStatService.class);
	
	public static final T11ExamStatService service = Enhancer.enhance(T11ExamStatService.class);
	
	public T11ExamStat SelectById(Integer id){
		
		T11ExamStat mdl = T11ExamStat.dao.findFirst("select * from t11ExamStat where id=?", id);
		return mdl;
	}
}
