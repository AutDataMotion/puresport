package puresport.mvc.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class loginInterceptorlyf implements Interceptor{
	public static final String pthc = "/jf/puresport/pagesController/";
	@Override
	public void intercept(Invocation inv) {
		HttpServletRequest request = inv.getController().getRequest();
		String RequestURL =request.getRequestURL().toString();
		// TODO Auto-generated method stub
		HttpSession session = inv.getController().getSession();
		
		session.setAttribute("RequestURL", RequestURL);
		
//		if(session==null)
//		{
//			
//			inv.getController().redirect(pthc+"login");
//		}
//		else {
//			String userID_str = (String)session.getAttribute("usrid");
			//String userID = (String)session.getAttribute("usrid");
			if(session.getAttribute("usrid")!=null)
			{
				inv.invoke();
			}
			else {
				inv.getController().redirect(pthc+"login");
			}
//		}
	}

}
