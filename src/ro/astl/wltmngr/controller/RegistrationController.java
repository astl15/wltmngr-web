package ro.astl.wltmngr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin() {
		return "placeholder";
	}
	
}
