/**
 * <p>title:routePlugins.java<／p>
 * <p>Description: <／p>
 * @date:2016年1月28日下午2:15:23
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package puresport.config;

import com.jfinal.config.Routes;

/**
 * 创建时间：2016年1月28日 下午2:15:23
 * 项目名称：DUCPlatFromWeb
 * 文件类型：RoutePlugins.java
 * 类说明：
 *
 *  
 *修改日志：
 * Date			Author		Version		Description
 *---------------------------------------------------
 *2016年1月28日		Zhongweng	1.0			1.0Version
 */

import puresport.mvc.pages.pagesController;
import puresport.mvc.r4assc.R4AsscController;
import puresport.mvc.t10examgrd.T10ExamGrdController;
import puresport.mvc.t1usrbsc.T1usrBscController;
import puresport.mvc.t2adiv.T2AdivController;
import puresport.mvc.t3statl.T3StatlController;
import puresport.mvc.t5crclstdy.T5CrclStdyController;
import puresport.mvc.t6mgrahr.T6MgrAhrController;
import puresport.mvc.t7crcl.T7CrclController;
import puresport.mvc.t8exam.T8ExamController;
import puresport.mvc.t9tstlib.T9TstlibController;


/**
 * <p>Title: RoutePlugins<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年1月28日
 */
public class RoutePlugins extends Routes {
	@Override
	public void config() {
		add("/jf/puresport/pagesController", pagesController.class);
		add("/jf/puresport/t1_usr_bsc", T1usrBscController.class);
		add("/jf/puresport/t2_adiv", T2AdivController.class);
		add("/jf/puresport/t3_stat", T3StatlController.class);
		add("/jf/puresport/t4_assc", R4AsscController.class);
		add("/jf/puresport/t5_crcl_stdy", T5CrclStdyController.class);
		add("/jf/puresport/t6_mgr_ahr", T6MgrAhrController.class);
		add("/jf/puresport/t7_crcl", T7CrclController.class);
		add("/jf/puresport/t8_exam", T8ExamController.class);
		add("/jf/puresport/t9_tstlib", T9TstlibController.class);
		add("/jf/puresport/t10_exam_grd", T10ExamGrdController.class);
	}
}
