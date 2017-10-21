package ro.astl.wltmngr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.ast.userservice.*;
import ro.astl.wltmngr.model.User;
@Controller
public class LoginController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(LoginController.class);
	private UserInstanceService userInstanceService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView getLogin(){
		logger.debug("GET login Mapped");
		return new ModelAndView("login","user",new User());
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView postLogin(@ModelAttribute("user")User user, 
			BindingResult result, 
			ModelMap model) {
		logger.debug("POST login Mapped");
		UserInstanceService_Service service = new UserInstanceService_Service();
		userInstanceService = service.getUserInstanceImplPort();
		
		/*Map request*/
		ApplicationContext context = new ApplicationContext();
		context.setApplicationName("WLTMNGR");
		UserInstanceIn request = new UserInstanceIn();
		request.setConxtext(context);
		if(user.isDemo()){
			request.setUsername("Andrei");
			request.setPassword("test");
		}else{
			request.setUsername(user.getUsername());
			request.setPassword(user.getPassword());
		}
		
		/*Call UserInstanceWS*/
		UserInstanceOut response = userInstanceService.getUserbyUsername(request);
		
		/*Use response*/
		if("SUCCESS".equals(response.getResponseCode())){
			return new ModelAndView("placeholder","loggedUser",user);
		}else{
			return new ModelAndView("error","rejectedUser",user);
		}
	}
}
