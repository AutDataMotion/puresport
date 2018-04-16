package puresport.mvc.pages;

import org.apache.log4j.Logger;

import com.jfinal.aop.Clear;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;


public class pagesController extends BaseController {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(pagesController.class);

	public static final String pthc = "/jf/puresport/pagesController/";
	public static final String pthv = "/f/";
	@Clear
	public void index() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		//getSession().setAttribute("userid", "100");
//		setSessionAttr("userID","10");
		renderWithPath(pthv+"index.html");
	}
	@Clear
	public void study() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"study.html");
	}
	@Clear
	public void dotest() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"dotest.html");
	}
	@Clear
	public void about() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"about.html");
	}
	@Clear
	public void login() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"login.html");
	}
	@Clear
	public void selfcenter() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"selfcenter.html");
	}
	@Clear
	public void admin() {
		//paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10pdt_report.sqlId_splitPage_from);
		//renderWithPath(pthv+"list.html");
		renderWithPath(pthv+"admin.html");
	}
	@Override
	protected void setViewPath() {
		// TODO Auto-generated method stub
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
