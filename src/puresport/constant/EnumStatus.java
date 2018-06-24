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
public enum EnumStatus {
	Failed(0, "0","操作失败,请检查数据格式后重试！"),
	Success(1, "1","操作成功"),
	LevelUnknown(-1, "-1", "未知"),
	LevelDeleted(0, "0", "已删除，不可见"),
	LevelView(1, "1", "可见"),
	LevelShow(2, "2", "具有该级别"),
	;
	private int id;
	private String idStr;
	private String name;
	
	private EnumStatus(int aid, String aidStr,  String aname){
		id = aid;
		idStr = aidStr;
		name = aname;
	}

	public String getIdText(){
		return String.valueOf(id);
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

	/**
	 * @return the idStr
	 */
	public String getIdStr() {
		return idStr;
	}

	/**
	 * @param idStr the idStr to set
	 */
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	} 
	
}
