package ro.astl.wltmngr.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ro.astl.wltmngr.model.CategoryDTO;

@Controller
public class MainController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);
	private static final String PAYMENTSWS_URL = "http://localhost:8181/PaymentsWS/";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getHome(Principal principal, ModelMap model){
		logger.debug("/ Request Mapped");
		Gson gson = new Gson();
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		RestTemplate paymentsService = new RestTemplate();
		String jsonResponse = paymentsService
				.getForObject(PAYMENTSWS_URL + 
						"paymentsWS/v1/categories/getCategories" , String.class);
		categories = gson.fromJson(jsonResponse, List.class);
		model.addAttribute("categories",categories);
		model.addAttribute("user", principal.getName());
		return "home";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(){
		return "register";
	}
}
