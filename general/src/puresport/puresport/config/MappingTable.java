/**
 * <p>title:MappingTable.java<／p>
 * <p>Description: <／p>
 * @date:2016年3月12日上午10:11:42
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package puresport.config;

import org.apache.log4j.Logger;
import puresport.mvc.t15_group.T15Group;
import puresport.mvc.r16_group_usr.R16GroupUsr;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
/**
 * 创建时间：2016年3月12日 上午10:11:42
 * 项目名称：
 * 文件类型：MappingTable.java
 * 类说明：
 *
 *  
 *修改日志：
 * Date			Author		Version		Description
 *---------------------------------------------------
 *2016年3月12日		Zhongweng	1.0			1.0Version
 */

/**
 * <p>Title: MappingTable<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年3月12日
 */
public class MappingTable {

	private static Logger log = Logger.getLogger(MappingTable.class);
	public static void mapping(ActiveRecordPlugin arp){
		log.info("puresport MappingTable 表手工注册-----begin");
		arp.addMapping("t15_group", "id", T15Group.class);
		arp.addMapping("r16_group_usr", "id", R16GroupUsr.class);
		log.info("puresport MappingTable 表手工注册-----end");
		
	}
}
