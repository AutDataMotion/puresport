package puresport.mvc.r4assc;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class R4AsscValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R4AsscValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(R4AsscController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(R4AsscController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(R4Assc.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(R4AsscController.pthc+"save")){
			controller.render(R4AsscController.pthv+"xxx.html");
		
		} else if (actionKey.equals(R4AsscController.pthc+"update")){
			controller.render(R4AsscController.pthv+"xxx.html");
		
		}
	}
	
}
