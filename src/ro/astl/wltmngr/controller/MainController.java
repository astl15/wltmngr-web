package ro.astl.wltmngr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);

	@RequestMapping("/")
	public String home(){
		logger.debug("/ Request Mapped");
		return "home";
	}
}
