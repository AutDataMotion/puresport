/**
 * ThaiRiceRS
 * create by zw at 2018年3月17日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;
import java.util.Map;

import csuduc.platform.util.ReflectionUtils;

/**
 * @author zw
 *
 */
public class ParamComm implements Serializable{
	private Long id;
	private String name1;
	private String name2;
	private String name3;
	
	private Integer draw;
	private Integer pageIndex;
	private Integer pageSize;
	private Long total;
	
	ParamComm(){}

	
	
	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}



	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name1
	 */
	public String getName1() {
		return name1;
	}

	/**
	 * @param name1 the name1 to set
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * @param name2 the name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}

	/**
	 * @param name3 the name3 to set
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}

	/**
	 * @return the pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the draw
	 */
	public Integer getDraw() {
		return draw;
	}

	/**
	 * @param draw the draw to set
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	
}
