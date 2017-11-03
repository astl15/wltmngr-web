package ro.astl.wltmngr.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getHome(Principal principal, ModelMap model){
		logger.debug("/ Request Mapped");
		model.addAttribute("user", principal.getName());
		return "home";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(){
		return "register";
	}
}
