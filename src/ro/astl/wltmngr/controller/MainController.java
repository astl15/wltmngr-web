package ro.astl.wltmngr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getHome(){
		logger.debug("/ Request Mapped");
		return "redirect:login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(){
		return "register";
	}
}
