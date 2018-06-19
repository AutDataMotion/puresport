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
	Country(91, "国家级"), Province(81, "省级"), City(71, "市级"),;
	private int id;
	private String name;

	private EnumTypeLevel(int aid, String aname) {
		id = aid;
		name = aname;
	}

	public static int getLevelId(String levelName) {

		for (EnumTypeLevel e : EnumTypeLevel.values()) {
			if (levelName.equals(e.getName())) {
				return e.getId();
			}
		}
		throw new IllegalArgumentException("无效级别"+levelName);
	}

	public static boolean higher(String a, String b) {
		int ia = getLevelId(a);
		int ib = getLevelId(b);
		if (ia >  ib) {
			return true;
		}
		return false;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
