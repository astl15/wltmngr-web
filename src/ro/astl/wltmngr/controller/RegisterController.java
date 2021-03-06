package ro.astl.wltmngr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.astl.userservice.ApplicationContext;
import ro.astl.userservice.UserInstanceIn;
import ro.astl.userservice.UserInstanceOut;
import ro.astl.userservice.UserInstanceService;
import ro.astl.wltmngr.model.RegisteredUserDTO;

@Controller 
public class RegisterController {
	
	 @Autowired
	 private MessageSource messageSource;
	 
	 @Autowired
	 private UserInstanceService userInstanceService;
	 
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
		 UserInstanceOut output = null;
		 UserInstanceIn request = new UserInstanceIn();
		 ApplicationContext context = new ApplicationContext();
		 String plainPassword = registeredUser.getPassword();
		 String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt()); 
		 context.setApplicationName("WLTMNGR");
		 request.setConxtext(context);
		 request.setUsername(registeredUser.getUsername());
		 request.setPassword(hashedPassword);
		 request.setRole(2);
		 if(plainPassword.equals(registeredUser.getRepass())) {
			 output = userInstanceService.registerUser(request);
		 }
		 if(output != null && "SUCCESS".equals(output.getResponseCode())) {
			 model.addAttribute("registrationSuccessful", true);
		 }
		 return "redirect:/register";
	 }
}
