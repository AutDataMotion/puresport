/**
 * PureSport
 * create by zw at 2018年4月21日
 * version: v1.0
 */
package puresport.mvc.t1usrbsc;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import csuduc.platform.util.ComUtil;
import csuduc.platform.util.StringUtil;
import puresport.mvc.comm.AuthCodeMdl;

/**
 * @author zw
 *
 */
public class T1userBscDTO implements Serializable{
	
	private Long usrid;
	private String usr_tp;
	private String usr_nm;
	private String nm;
	private String crdt_tp;
	private String spt_prj;
	private String crdt_no;
	private String gnd;
	private String brth_dt;
	private String mblph_no;
	private String email;
	private String province;
	private String city;
	private String institute;
    private Integer emailValCode;
    private Integer mblphValCode;
    
    private String passwd;
    private String rePasswd;
	
    private List<String> tipList;
    
	

	public T1userBscDTO(){}

	public boolean validate(AuthCodeMdl authCodeMdlPhone, AuthCodeMdl authCodeMdlEmail) {
		
		if (ComUtil.haveEmpty(nm, crdt_no, gnd, brth_dt, province, city, mblph_no, email, passwd)) {
			tipList.add("have empty");
			return false;
		}
		if (null == emailValCode && null == mblphValCode) {
			tipList.add("valCode empty");
			return false;
		}
		// 邮件是否校验
		if (emailValCode!=null) {
			if (authCodeMdlEmail.hasTimeOut(59*10)) {
				tipList.add("邮箱校验码已过期");
				return false;
			} else if(!emailValCode.equals(Integer.valueOf(authCodeMdlEmail.getCode()))){
				tipList.add("邮箱校验码不正确，请重新获取验证");
				return false;
			}
		}
		
		// 手机是否校验
		if (mblphValCode!=null) {
			if (authCodeMdlPhone.hasTimeOut(59*10)) {
				tipList.add("手机校验码已过期");
				return false;
			} else if(!mblphValCode.equals(Integer.valueOf(authCodeMdlPhone.getCode()))){
				tipList.add("手机校验码不正确，请重新获取验证");
				return false;
			}
		}
		
		return true;
	}
	
	private void addTip(String msg) {
		if (tipList == null) {
			tipList = new LinkedList<String>();
		}
		tipList.add(msg);
	}
	/**
	 * @return the usrid
	 */
	public Long getUsrid() {
		return usrid;
	}

	/**
	 * @param usrid the usrid to set
	 */
	public void setUsrid(Long usrid) {
		this.usrid = usrid;
	}

	/**
	 * @return the usr_tp
	 */
	public String getUsr_tp() {
		return usr_tp;
	}

	/**
	 * @param usr_tp the usr_tp to set
	 */
	public void setUsr_tp(String usr_tp) {
		this.usr_tp = usr_tp;
	}

	/**
	 * @return the usr_nm
	 */
	public String getUsr_nm() {
		return usr_nm;
	}

	/**
	 * @param usr_nm the usr_nm to set
	 */
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}

	/**
	 * @return the nm
	 */
	public String getNm() {
		return nm;
	}

	/**
	 * @param nm the nm to set
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}

	/**
	 * @return the crdt_tp
	 */
	public String getCrdt_tp() {
		return crdt_tp;
	}

	/**
	 * @param crdt_tp the crdt_tp to set
	 */
	public void setCrdt_tp(String crdt_tp) {
		this.crdt_tp = crdt_tp;
	}

	/**
	 * @return the spt_prj
	 */
	public String getSpt_prj() {
		return spt_prj;
	}

	/**
	 * @param spt_prj the spt_prj to set
	 */
	public void setSpt_prj(String spt_prj) {
		this.spt_prj = spt_prj;
	}

	/**
	 * @return the crdt_no
	 */
	public String getCrdt_no() {
		return crdt_no;
	}

	/**
	 * @param crdt_no the crdt_no to set
	 */
	public void setCrdt_no(String crdt_no) {
		this.crdt_no = crdt_no;
	}

	/**
	 * @return the gnd
	 */
	public String getGnd() {
		return gnd;
	}

	/**
	 * @param gnd the gnd to set
	 */
	public void setGnd(String gnd) {
		this.gnd = gnd;
	}

	/**
	 * @return the brth_dt
	 */
	public String getBrth_dt() {
		return brth_dt;
	}

	/**
	 * @param brth_dt the brth_dt to set
	 */
	public void setBrth_dt(String brth_dt) {
		this.brth_dt = brth_dt;
	}

	/**
	 * @return the mblph_no
	 */
	public String getMblph_no() {
		return mblph_no;
	}

	/**
	 * @param mblph_no the mblph_no to set
	 */
	public void setMblph_no(String mblph_no) {
		this.mblph_no = mblph_no;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the institute
	 */
	public String getInstitute() {
		return institute;
	}

	/**
	 * @param institute the institute to set
	 */
	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public Integer getEmailValCode() {
		return emailValCode;
	}

	public void setEmailValCode(Integer emailValCode) {
		this.emailValCode = emailValCode;
	}

	public Integer getMblphValCode() {
		return mblphValCode;
	}

	public void setMblphValCode(Integer mblphValCode) {
		this.mblphValCode = mblphValCode;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRePasswd() {
		return rePasswd;
	}

	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}
	
	public List<String> getTipList() {
		return tipList;
	}

	public void setTipList(List<String> tipList) {
		this.tipList = tipList;
	}
}