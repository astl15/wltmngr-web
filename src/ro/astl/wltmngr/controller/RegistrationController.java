package ro.astl.wltmngr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.ast.userservice.*;
@Controller
public class RegistrationController {
	
	UserInstanceService userInstanceService;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin() {
		
		UserInstanceService_Service service = new UserInstanceService_Service();
		userInstanceService = service.getUserInstanceImplPort();
		
		/*Map request*/
		ApplicationContext context = new ApplicationContext();
		context.setApplicationName("WLTMNGR");
		UserInstanceIn request = new UserInstanceIn();
		request.setConxtext(context);
		request.setUsername("Andrei");
		
		/*Call UserInstanceWS*/
		UserInstanceOut response = userInstanceService.getUserbyUsername(request);
		
		
		return "placeholder";
	}
	
}
