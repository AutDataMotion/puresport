package puresport.mvc.t7crcl;

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
//@Table(tableName = "t7_crcl")
public class T7Crcl extends BaseModel<T7Crcl> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T7Crcl.class);
	
	public static final T7Crcl dao = new T7Crcl();
	
	/**
	 * 字段描述：课程id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_crclid = "crclid";
	
	/**
	 * 字段描述：课程简介 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_crcl_brf = "crcl_brf";
	
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：int  长度：null
	 */
	public static final String column_adiv = "adiv";
	
	/**
	 * 字段描述：课程名称 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_crcl_nm = "crcl_nm";
	
	/**
	 * 字段描述：课程文件路径 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_crcl_file_rte = "crcl_file_rte";
	
	/**
	 * 字段描述：课程属性（是否必修） 
	 * 字段类型：char  长度：1
	 */
	public static final String column_crcl_attr = "crcl_attr";
	
	/**
	 * 字段描述：学分 
	 * 字段类型：int  长度：null
	 */
	public static final String column_ty_grd = "ty_grd";
	
	/**
	 * 字段描述：课程类别 
	 * 字段类型：char  长度：2
	 */
	public static final String column_crcl_cgy = "crcl_cgy";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：缩略图文件路径 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_thumbnail_rte = "thumbnail_rte";
	
	/**
	 * 字段描述：学习状态 
	 * 字段类型：char  长度：1
	 */
	public static final String column_stdy_st = "stdy_st";
	
	/**
	 * 字段描述：赛事类型
	 * 字段类型：String 
	 * "":通用赛事; "05"东京奥运会
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：科目
	 * 字段类型：String 
	 * "01":科目1;"02":科目2;"03":科目3;"04":科目4;"05":科目5;"06":科目6;
	 */
	public static final String column_category = "category";
	
	
	/**
	 * sqlId : puresport.t7Crcl.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t7Crcl.splitPageFrom";

	private Long crclid;
	private String crcl_brf;
	private Integer adiv;
	private String crcl_nm;
	private String crcl_file_rte;
	private String crcl_attr;
	private Integer ty_grd;
	private String crcl_cgy;
	private Timestamp tms;
	private String thumbnail_rte;
	private String stdy_st;
	private String type;
	private String category;

	public <T> T  getType() {
		return get(column_type);
	}
	public void setType(String type) {
		set(column_type, type);
	}
	public <T> T  getCategory() {
		return get(column_category);
	}
	public void setCategory(String category) {
		set(column_category, category);
	}

	public void setStdy_st(String stdy_st){
		set(column_stdy_st, stdy_st);
	}
	public <T> T getStdy_st() {
		return get(column_stdy_st);
	}
	public <T> T getThumbnail_rte() {
		return get(column_thumbnail_rte);
	}
	public void setThumbnail_rte(String thumbnail_rte) {
		set(column_thumbnail_rte, thumbnail_rte);
	}

	public void setCrclid(Long crclid){
		set(column_crclid, crclid);
	}
	public <T> T getCrclid() {
		return get(column_crclid);
	}
	public void setCrcl_brf(String crcl_brf){
		set(column_crcl_brf, crcl_brf);
	}
	public <T> T getCrcl_brf() {
		return get(column_crcl_brf);
	}
	public void setAdiv(Integer adiv){
		set(column_adiv, adiv);
	}
	public <T> T getAdiv() {
		return get(column_adiv);
	}
	public void setCrcl_nm(String crcl_nm){
		set(column_crcl_nm, crcl_nm);
	}
	public <T> T getCrcl_nm() {
		return get(column_crcl_nm);
	}
	public void setCrcl_file_rte(String crcl_file_rte){
		set(column_crcl_file_rte, crcl_file_rte);
	}
	public <T> T getCrcl_file_rte() {
		return get(column_crcl_file_rte);
	}
	public void setCrcl_attr(String crcl_attr){
		set(column_crcl_attr, crcl_attr);
	}
	public <T> T getCrcl_attr() {
		return get(column_crcl_attr);
	}
	public void setTy_grd(Integer ty_grd){
		set(column_ty_grd, ty_grd);
	}
	public <T> T getTy_grd() {
		return get(column_ty_grd);
	}
	public void setCrcl_cgy(String crcl_cgy){
		set(column_crcl_cgy, crcl_cgy);
	}
	public <T> T getCrcl_cgy() {
		return get(column_crcl_cgy);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
