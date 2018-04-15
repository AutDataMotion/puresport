package puresport.mvc.t6mgrahr;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseModel;

/**
 * @description：
 * 
 * @author ZW
 */
@SuppressWarnings("unused")
// @Table(tableName = "t6_mgr_ahr")
public class T6MgrAhr extends BaseModel<T6MgrAhr> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T6MgrAhr.class);

	public static final T6MgrAhr dao = new T6MgrAhr();

	/**
	 * 字段描述：id 字段类型：bigint 长度：null
	 */
	public static final String column_usrid = "usrid";

	/**
	 * 字段描述：用户类型 字段类型：char 长度：2
	 */
	public static final String column_usr_tp = "usr_tp";

	/**
	 * 字段描述：用户名 字段类型：varchar 长度：32
	 */
	public static final String column_usr_nm = "usr_nm";

	/**
	 * 字段描述：姓名 字段类型：varchar 长度：32
	 */
	public static final String column_nm = "nm";

	/**
	 * 字段描述：证件类型 字段类型：char 长度：2
	 */
	public static final String column_crdt_tp = "crdt_tp";

	/**
	 * 字段描述：证件号 字段类型：varchar 长度：512
	 */
	public static final String column_crdt_no = "crdt_no";

	/**
	 * 字段描述：性别 字段类型：char 长度：1
	 */
	public static final String column_gnd = "gnd";

	/**
	 * 字段描述：工作单位 字段类型：varchar 长度：512
	 */
	public static final String column_wrk_unit = "wrk_unit";

	/**
	 * 字段描述：密码 字段类型：varchar 长度：512
	 */
	public static final String column_pswd = "pswd";

	/**
	 * 字段描述：职务 字段类型：varchar 长度：512
	 */
	public static final String column_post = "post";

	/**
	 * 字段描述：出生日期 字段类型：char 长度：8
	 */
	public static final String column_brth_dt = "brth_dt";

	/**
	 * 字段描述：行政区划代码 字段类型：char 长度：6
	 */
	public static final String column_adiv_cd = "adiv_cd";

	/**
	 * 字段描述：协会id 字段类型：char 长度：6
	 */
	public static final String column_asscid = "asscid";

	/**
	 * 字段描述：手机号 字段类型：varchar 长度：256
	 */
	public static final String column_mblph_no = "mblph_no";

	/**
	 * 字段描述：维护时间 字段类型：timestamp 长度：null
	 */
	public static final String column_tms = "tms";

	/**
	 * 字段描述：国家省市管理员id 字段类型：int 长度：null
	 */
	public static final String column_cty_prov_city_mgrid = "cty_prov_city_mgrid";

	/**
	 * 字段描述：协会管理员id 字段类型：int 长度：null
	 */
	public static final String column_assc_mgrid = "assc_mgrid";

	/**
	 * 字段描述：邮箱 字段类型：varchar 长度：512
	 */
	public static final String column_email = "email";

	/**
	 * 字段描述：备注 字段类型：varchar 长度：2048
	 */
	public static final String column_rmrk = "rmrk";

	public static final String column_typeleve = "typeleve";

	/**
	 * sqlId : puresport.t6MgrAhr.splitPageFrom 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t6MgrAhr.splitPageFrom";

	private Long usrid;
	private String usr_tp;
	private String usr_nm;
	private String nm;
	private String crdt_tp;
	private String crdt_no;
	private String gnd;
	private String wrk_unit;
	private String pswd;
	private String post;
	private String brth_dt;
	private String adiv_cd;
	private String asscid;
	private String mblph_no;
	private Timestamp tms;
	private Integer cty_prov_city_mgrid;
	private Integer assc_mgrid;
	private String email;
	private String rmrk;
	private String typeleve;

	public void setUsrid(Long usrid) {
		set(column_usrid, usrid);
	}

	public <T> T getUsrid() {
		return get(column_usrid);
	}

	public void setUsr_tp(String usr_tp) {
		set(column_usr_tp, usr_tp);
	}

	public <T> T getUsr_tp() {
		return get(column_usr_tp);
	}

	public void setUsr_nm(String usr_nm) {
		set(column_usr_nm, usr_nm);
	}

	public <T> T getUsr_nm() {
		return get(column_usr_nm);
	}

	public void setNm(String nm) {
		set(column_nm, nm);
	}

	public <T> T getNm() {
		return get(column_nm);
	}

	public void setCrdt_tp(String crdt_tp) {
		set(column_crdt_tp, crdt_tp);
	}

	public <T> T getCrdt_tp() {
		return get(column_crdt_tp);
	}

	public void setCrdt_no(String crdt_no) {
		set(column_crdt_no, crdt_no);
	}

	public <T> T getCrdt_no() {
		return get(column_crdt_no);
	}

	public void setGnd(String gnd) {
		set(column_gnd, gnd);
	}

	public <T> T getGnd() {
		return get(column_gnd);
	}

	public void setWrk_unit(String wrk_unit) {
		set(column_wrk_unit, wrk_unit);
	}

	public <T> T getWrk_unit() {
		return get(column_wrk_unit);
	}

	public void setPswd(String pswd) {
		set(column_pswd, pswd);
	}

	public <T> T getPswd() {
		return get(column_pswd);
	}

	public void setPost(String post) {
		set(column_post, post);
	}

	public <T> T getPost() {
		return get(column_post);
	}

	public void setBrth_dt(String brth_dt) {
		set(column_brth_dt, brth_dt);
	}

	public <T> T getBrth_dt() {
		return get(column_brth_dt);
	}

	public void setAdiv_cd(String adiv_cd) {
		set(column_adiv_cd, adiv_cd);
	}

	public <T> T getAdiv_cd() {
		return get(column_adiv_cd);
	}

	public void setAsscid(String asscid) {
		set(column_asscid, asscid);
	}

	public <T> T getAsscid() {
		return get(column_asscid);
	}

	public void setMblph_no(String mblph_no) {
		set(column_mblph_no, mblph_no);
	}

	public <T> T getMblph_no() {
		return get(column_mblph_no);
	}

	public void setTms(Timestamp tms) {
		set(column_tms, tms);
	}

	public <T> T getTms() {
		return get(column_tms);
	}

	public void setCty_prov_city_mgrid(Integer cty_prov_city_mgrid) {
		set(column_cty_prov_city_mgrid, cty_prov_city_mgrid);
	}

	public <T> T getCty_prov_city_mgrid() {
		return get(column_cty_prov_city_mgrid);
	}

	public void setAssc_mgrid(Integer assc_mgrid) {
		set(column_assc_mgrid, assc_mgrid);
	}

	public <T> T getAssc_mgrid() {
		return get(column_assc_mgrid);
	}

	public void setEmail(String email) {
		set(column_email, email);
	}

	public <T> T getEmail() {
		return get(column_email);
	}

	public void setRmrk(String rmrk) {
		set(column_rmrk, rmrk);
	}

	public <T> T getRmrk() {
		return get(column_rmrk);
	}

	public void setTypelevel(String typeleve) {
		set(column_typeleve, typeleve);
	}

	public <T> T getTypelevel() {
		return get(column_typeleve);
	}

}
