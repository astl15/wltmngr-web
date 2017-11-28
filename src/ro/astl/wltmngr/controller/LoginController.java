package ro.astl.wltmngr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.astl.wltmngr.model.UserDTO;
@Controller
public class LoginController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(LoginController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView getLogin(@RequestParam(value = "error", required = false) String error){
		logger.debug("GET login Mapped");
		ModelAndView model = new ModelAndView();
		if(error != null){
			model.addObject("error", Boolean.TRUE);
		}
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView postLogin(@RequestParam(value = "error", required = false) String error){
		ModelAndView model = new ModelAndView();
		if(error != null){
			model.addObject("error", Boolean.TRUE);
		}
		model.setViewName("login");
		return model;
	}
	
}
