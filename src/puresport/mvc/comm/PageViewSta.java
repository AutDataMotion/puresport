package puresport.mvc.comm;

import com.jfinal.plugin.activerecord.Db;

import puresport.config.ConfMain;
import puresport.mvc.t3statl.T3Statl;
import puresport.mvc.t3statl.T3StatlService;

public class PageViewSta {
	private final static String tableName = "t3_stat";
	private final static String tableKey = "id";
	public  static boolean StaLoginPeopleCountByDay()
	{
		try {
			T3Statl mdl = T3StatlService.service.SelectByDate();
			if(mdl!=null)
			{
				int count = Integer.valueOf(mdl.getLogin_cnt());
				
				int res = Db.update("update puresport.t3_stat set login_cnt=?",count+1);
			}
			else {
				T3Statl newRecord =new T3Statl().set(T3Statl.column_login_cnt, 1);
				newRecord.saveGenIntId();
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
