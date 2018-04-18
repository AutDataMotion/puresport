package puresport.mvc.pages;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class loginInterceptorlyf implements Interceptor{
	public static final String pthc = "/jf/puresport/pagesController/";
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		HttpSession session = inv.getController().getSession();
		if(session==null)
		{
			inv.getController().redirect(pthc+"login");
		}
		else {
			Long userID = (Long) session.getAttribute("usrid");
			//String userID = (String)session.getAttribute("usrid");
			if(userID!=null)
			{
				inv.invoke();
			}
			else {
				inv.getController().redirect(pthc+"login");
			}
		}
	}

}
