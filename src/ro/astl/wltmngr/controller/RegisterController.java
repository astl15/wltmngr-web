package ro.astl.wltmngr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.astl.wltmngr.model.RegisteredUserDTO;

@Controller 
public class RegisterController {
	
	 @Autowired
	 private MessageSource messageSource;
	 
	 @RequestMapping(value="/register", method=RequestMethod.GET)
	 public ModelAndView getRegister(){
		ModelAndView model = new ModelAndView();
		model.addObject("register", new RegisteredUserDTO());
		model.setViewName("register");
		return model;
	 }
	 
	 @RequestMapping(value="/register", method=RequestMethod.POST)
	 public String postRegister(@ModelAttribute("register")RegisteredUserDTO registeredUser,
			 BindingResult result, ModelMap model) {
		 return "redirect:/register";
	 }
}
