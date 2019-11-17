package puresport.mvc.t15creditinf;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T15CreditInfValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15CreditInfValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T15CreditInfController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T15CreditInfController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T15CreditInf.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T15CreditInfController.pthc+"save")){
			controller.render(T15CreditInfController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T15CreditInfController.pthc+"update")){
			controller.render(T15CreditInfController.pthv+"xxx.html");
		
		}
	}
	
}
