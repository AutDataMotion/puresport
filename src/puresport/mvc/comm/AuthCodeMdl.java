/**
 * PureSport
 * create by zw at 2018年4月28日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;

import csuduc.platform.util.lyf.EmailUtils;

/**
 * @author zw
 *
 */
public class AuthCodeMdl implements Serializable{
	public static final String keyPhoneCode = "keyPhoneCode";
	public static final String keyEmailCode = "keyEmailCode";
	
	private String code;
	private long timeBeg;
	
	public AuthCodeMdl(){}

	public static AuthCodeMdl createOne() {
		
		AuthCodeMdl mdl = new AuthCodeMdl();
		mdl.code = EmailUtils.getRadCode();
		System.out.println("code:"+mdl.code);
		mdl.timeBeg = System.currentTimeMillis();
		
		return mdl;
	}
	
	public boolean hasTimeOut(int seconds) {
		
		long cur = System.currentTimeMillis();
		long milliLen = seconds*1000 ;
		
		return (cur - timeBeg) > milliLen;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getTimeBeg() {
		return timeBeg;
	}

	public void setTimeBeg(Long timeBeg) {
		this.timeBeg = timeBeg;
	}
	
}
