package ro.astl.wltmngr.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import ro.astl.wltmngr.model.Category;
import ro.astl.wltmngr.model.CustomPaymentDTO;
import ro.astl.wltmngr.model.PaymentDTO;
import ro.astl.wltmngr.utils.WltMngrEnv;

@Controller
public class CustomPaymentController {
	
	private static Properties envProperties = WltMngrEnv.getProperties();
	private static final String PAYMENTSWS_URL = envProperties.getProperty("payments.url");
	
	@Autowired
	private RestTemplate paymentsService;
	
	@RequestMapping(value="/payments/custom", method=RequestMethod.GET)
	public ModelAndView getCustomPayment() {
		List<Category> categories = new ArrayList<Category>();
		Gson gson = new Gson();
		Locale currentLocale = LocaleContextHolder.getLocale();
		String uriCategories = PAYMENTSWS_URL + "categories/getCategories";
		String jsonCategories = paymentsService
				.getForObject(uriCategories , String.class);
		categories = gson.fromJson(jsonCategories, List.class);
		ModelAndView model = new ModelAndView();
		model.addObject("categories",categories);
		model.addObject("customPayment", new CustomPaymentDTO());
		model.addObject("currentLocale", currentLocale);
		model.setViewName("customPayment");
		return model;
	}
	
	@RequestMapping(value="/payments/custom", method=RequestMethod.POST)
	public ModelAndView processFastPayment( @ModelAttribute("payment")CustomPaymentDTO payment, 
		      BindingResult result, Principal principal){
		
		String jsonRequest;
		ModelAndView model = new ModelAndView("redirect:/");
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setDate(LocalDate.parse(payment.getDate()));
		paymentDTO.setAuthor(principal.getName());
		paymentDTO.setAmount(payment.getAmount());
		paymentDTO.setCategory(payment.getCategory());
		paymentDTO.setDescription(payment.getDescription());
		jsonRequest = gson.toJson(paymentDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(jsonRequest,headers);
		String jsonResponse = paymentsService.postForObject(PAYMENTSWS_URL +
				"payments/add", request, String.class);
		return model;    	  
	}
}

