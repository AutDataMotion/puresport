package puresport.mvc.t11examstat;

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
//@Table(tableName = "t11_exam_stat")
public class T11ExamStat extends BaseModel<T11ExamStat> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T11ExamStat.class);
	
	public static final T11ExamStat dao = new T11ExamStat();
	
	public static final String  tableName = "t11_exam_stat";
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：用户id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_usrid = "usrid";
	
	/**
	 * 字段描述：考试id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_examid = "examid";
	
	/**
	 * 字段描述：考试成绩 
	 * 字段类型：int  长度：null
	 */
	public static final String column_exam_grd = "exam_grd";
	
	/**
	 * 字段描述：考试状态 
	 * 字段类型：char  长度：null
	 */
	public static final String column_exam_st = "exam_st";
	
	/**
	 * 字段描述：考试渠道 
	 * 字段类型：char  长度：null
	 */
	public static final String column_exam_channel = "exam_channel";
	
	/**
	 * 字段描述：考试次数 
	 * 字段类型：int  长度：null
	 */
	public static final String column_exam_num = "exam_num";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：考试名称
	 * 字段类型：String  长度：null
	 */
	public static final String column_exam_nm = "exam_nm";
	
	
	/**
	 * sqlId : puresport.t11ExamStat.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t11ExamStat.splitPageFrom";

	private Long id;
	private Integer usrid;
	private Integer examid;
	private Integer exam_grd;
	private String exam_st;
	private String exam_channel;
	private Integer exam_num;
	private Timestamp tms;
	private String exam_nm;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setUsrid(Integer usrid){
		set(column_usrid, usrid);
	}
	public <T> T getUsrid() {
		return get(column_usrid);
	}
	public void setExamid(Integer examid){
		set(column_examid, examid);
	}
	public <T> T getExamid() {
		return get(column_examid);
	}
	public void setExam_grd(Integer exam_grd){
		set(column_exam_grd, exam_grd);
	}
	public <T> T getExam_grd() {
		return get(column_exam_grd);
	}
	public void setExam_st(String exam_st){
		set(column_exam_st, exam_st);
	}
	public <T> T getExam_st() {
		return get(column_exam_st);
	}
	public void setExam_channel(String exam_channel){
		set(column_exam_channel, exam_channel);
	}
	public <T> T getExam_channel() {
		return get(column_exam_channel);
	}
	public void setExam_num(Integer exam_num){
		set(column_exam_num, exam_num);
	}
	public <T> T getExam_num() {
		return get(column_exam_num);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	public void setExam_nm(String exam_nm){
		set(column_exam_num, exam_nm);
	}
	public <T> T getExam_nm() {
		return get(column_exam_nm);
	}
	
}
