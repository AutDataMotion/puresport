package puresport.mvc.t3statl;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t3_stat")
public class T3Statl extends BaseModel<T3Statl> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T3Statl.class);
	
	public static final T3Statl dao = new T3Statl();
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：已登陆次数 
	 * 字段类型：int  长度：null
	 */
	public static final String column_login_cnt = "login_cnt";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.t3Statl.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t3Statl.splitPageFrom";

	private Long id;
	private Integer login_cnt;
	private Timestamp tms;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setLogin_cnt(Integer login_cnt){
		set(column_login_cnt, login_cnt);
	}
	public <T> T getLogin_cnt() {
		return get(column_login_cnt);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
