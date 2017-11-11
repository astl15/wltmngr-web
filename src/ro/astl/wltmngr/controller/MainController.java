package ro.astl.wltmngr.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ro.astl.wltmngr.adapter.LocalDateAdapter;
import ro.astl.wltmngr.model.CategoryDTO;
import ro.astl.wltmngr.model.PaymentDTO;

@Controller
public class MainController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);
	private static final String PAYMENTSWS_URL = "http://localhost:8181/PaymentsWS/";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getHome(Principal principal){
		logger.debug("/ Request Mapped");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		Gson gson = new Gson();
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		RestTemplate paymentsService = new RestTemplate();
		String jsonResponse = paymentsService
				.getForObject(PAYMENTSWS_URL + 
						"paymentsWS/v1/categories/getCategories" , String.class);
		categories = gson.fromJson(jsonResponse, List.class);
		//model.addAttribute("categories",categories);
		//model.addAttribute("user", principal.getName());
		model.addObject("categories",categories);
		model.addObject("user", principal.getName());
		model.addObject("payment", new PaymentDTO());
		return model;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String processFastPayment( @ModelAttribute("payment")PaymentDTO payment, 
		      BindingResult result, ModelMap model, Principal principal){
		
		String jsonRequest;
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
		/*Because we process a fast payment, we are sure that payment date is always
		 * the current date
		 */
		payment.setDate(LocalDate.now());
		payment.setAuthor(principal.getName());
		jsonRequest = gson.toJson(payment);
		return "register";    	  
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(){
		return "register";
	}
}
