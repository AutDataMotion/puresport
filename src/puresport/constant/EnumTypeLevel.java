/**
 * PureSport
 * create by zw at 2018年4月19日
 * version: v1.0
 */
package puresport.constant;

/**
 * @author zw
 *
 */
public enum EnumTypeLevel {
	Country(11, "国家级"),
	Province(21, "省级"),
	City(31, "市级"),
	;
	private int id;
	private String name;
	
	private EnumTypeLevel(int aid, String aname){
		id = aid;
		name = aname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	} 
	
	
}
