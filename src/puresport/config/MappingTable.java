/**
 * <p>title:MappingTable.java<／p>
 * <p>Description: <／p>
 * @date:2016年3月12日上午10:11:42
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package puresport.config;

import org.apache.log4j.Logger;

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

import puresport.mvc.area.Area;
import puresport.mvc.t10examgrd.T10ExamGrd;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t2adiv.T2Adiv;
import puresport.mvc.t3statl.T3Statl;
import puresport.mvc.t4assc.T4Assc;
import puresport.mvc.t5crclstdy.T5CrclStdy;
import puresport.mvc.t6mgrahr.T6MgrAhr;
import puresport.mvc.t7crcl.T7Crcl;
import puresport.mvc.t8exam.T8Exam;
import puresport.mvc.t9tstlib.T9Tstlib;

/**
 * <p>
 * Title: MappingTable<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年3月12日
 */
public class MappingTable {

	private static Logger log = Logger.getLogger(MappingTable.class);

	public static void mapping(ActiveRecordPlugin arp) {
		log.info("puresport MappingTable 表手工注册-----begin");
		arp.addMapping("dt_area", "id", Area.class);
		arp.addMapping("t1_usr_bsc", "id", T1usrBsc.class);
		arp.addMapping("t2_adiv", "id", T2Adiv.class);
		arp.addMapping("t3_stat", "id", T3Statl.class);
		arp.addMapping("t4_assc", "id", T4Assc.class);
		arp.addMapping("t5_crcl_stdy", "id", T5CrclStdy.class);
		arp.addMapping("t6_mgr_ahr", "id", T6MgrAhr.class);
		arp.addMapping("t7_crcl", "id", T7Crcl.class);
		arp.addMapping("t8_exam", "id", T8Exam.class);
		arp.addMapping("t9_tstlib", "id", T9Tstlib.class);
		arp.addMapping("t10_exam_grd", "id", T10ExamGrd.class);
		log.info("puresport MappingTable 表手工注册-----end");

	}
}
