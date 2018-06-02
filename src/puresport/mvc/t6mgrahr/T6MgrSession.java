/**
 * PureSport
 * create by zw at 2018年6月2日
 * version: v1.0
 */
package puresport.mvc.t6mgrahr;

import java.io.Serializable;

/**
 * @author zw
 *
 */
public class T6MgrSession implements Serializable{
	
	public final static String KeyName = "_T6MgrSession_";
	
	private Long usrid;
	private String typeleve;
	private String province;
	private String city;
	private String institute;
	
	public T6MgrSession(){}

	public T6MgrSession(Long ausrid, String atypeleve, String aprovince, String acity, String ainstitute){
		usrid = ausrid;
		typeleve = atypeleve;
		province = aprovince;
		city = acity;
		institute = ainstitute;
	}
	
	public T6MgrSession(T6MgrAhr mdl){
		usrid = mdl.getUsrid();
		typeleve = mdl.getTypeleve();
		province = mdl.getProvince();
		city = mdl.getCity();
		institute = mdl.getInstitute();
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
	 * @return the typeleve
	 */
	public String getTypeleve() {
		return typeleve;
	}

	/**
	 * @param typeleve the typeleve to set
	 */
	public void setTypeleve(String typeleve) {
		this.typeleve = typeleve;
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
	
}
