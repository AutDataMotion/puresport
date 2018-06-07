/**
 * PureSport
 * create by zw at 2018年6月2日
 * version: v1.0
 */
package puresport.mvc.t6mgrahr;

import java.io.Serializable;

import puresport.constant.EnumTypeLevel;

/**
 * @author zw
 *
 */
public class T6MgrSession implements Serializable{

	private static final long serialVersionUID = 2305013944330477412L;

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
		usrid = Long.valueOf((String)mdl.getUsrid()) ;
		typeleve = mdl.getTypeleve();
		province = mdl.getProvince();
		city = mdl.getCity();
		institute = mdl.getInstitute();
	}
	
	public String selectRoleStr(){
		if (typeleve.equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			return " 1=1 ";
		} else if (typeleve.equals(EnumTypeLevel.Province.getName())) {
			// 省级 只可见属于该省的
			return String.format(" province='%s' ", province);
		} else if (typeleve.equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			return String.format(" city='%s' and province='%s' ", city , province);
		}
		// 未知的都不可见
		return " 1=2 ";
	}
	
	public String ggProvince(){
		if (typeleve.equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			return " -- ";
		}
	    return province;
	}
	
	public String ggCity(){
		if (typeleve.equals(EnumTypeLevel.Country.getName())
				||typeleve.equals(EnumTypeLevel.Province.getName()) ) {
			// 国家级 全部可见
			return " -- ";
		} 
	
	    return city;
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
