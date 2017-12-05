package ro.astl.wltmngr.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
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
import ro.astl.wltmngr.model.CategoryAmount;
import ro.astl.wltmngr.model.CategoryDTO;
import ro.astl.wltmngr.model.Payment;
import ro.astl.wltmngr.model.PaymentDTO;
import ro.astl.wltmngr.utils.WltMngrEnv;

@Controller
public class MainController {
	
	private static final Logger logger = (Logger) LogManager.getLogger(MainController.class);
	private static Properties envProperties = WltMngrEnv.getProperties();
	private static final String PAYMENTSWS_URL = envProperties.getProperty("payments.url");
	
	@Autowired
	private RestTemplate paymentsService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getHome(Principal principal){
		logger.debug("/ Request Mapped");
		
		LocalDate currentDate = LocalDate.now();
		
		Locale currentLocale = LocaleContextHolder.getLocale();
		
		String uriCategories = PAYMENTSWS_URL + "categories/getCategories";
		String uriPayments = PAYMENTSWS_URL + "payments/last/" + principal.getName();
		String uriPaymentsByMonth = PAYMENTSWS_URL + "payments/" + 
				currentDate.getYear() + "/" + 
				currentDate.getMonthValue() +"/" + 
				principal.getName();
		String uriAmountsPerCategory = PAYMENTSWS_URL + "amounts/categories/" +
				currentDate.getYear() + "/" + 
				currentDate.getMonthValue() +"/" + 
				principal.getName();
		String uriAmountsPerDate = PAYMENTSWS_URL + "amounts/date/" +
				currentDate.getYear() + "/" + 
				currentDate.getMonthValue() +"/" + 
				principal.getName();
		String uriMonthlySummary = PAYMENTSWS_URL + "amounts/month/" +
				currentDate.getYear() + "/" + 
				currentDate.getMonthValue() +"/" + 
				principal.getName();
		
		Gson gson = new Gson();
		List<Category> categories = new ArrayList<Category>();
		List<Payment> payments = new ArrayList<Payment>();
		List<Payment> paymentsThisMonth = new ArrayList<Payment>();
		List<CategoryAmount> amountsPerCategory = new ArrayList<CategoryAmount>();
		List<Payment> amountsPerDate = new ArrayList<Payment>();
		Map<String, Integer> monthlySummary = new HashMap<String, Integer>();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		
		String jsonCategories = paymentsService
				.getForObject(uriCategories , String.class);
		categories = gson.fromJson(jsonCategories, List.class);
		
		String jsonLastPayments = paymentsService
				.getForObject(uriPayments, String.class);
		payments = gson.fromJson(jsonLastPayments, List.class);
		
		String jsonPaymentsThisMonth = paymentsService
				.getForObject(uriPaymentsByMonth, String.class);
		paymentsThisMonth = gson.fromJson(jsonPaymentsThisMonth, List.class);
		
		String jsonAmountsPerCategory = paymentsService
				.getForObject(uriAmountsPerCategory,  String.class);
		amountsPerCategory = gson.fromJson(jsonAmountsPerCategory, List.class);
		
		String jsonAmountsPerDate = paymentsService
				.getForObject(uriAmountsPerDate, String.class);
		amountsPerDate = gson.fromJson(jsonAmountsPerDate, List.class);
		
		String jsonMonthlySummary = paymentsService
				.getForObject(uriMonthlySummary, String.class);
		monthlySummary = gson.fromJson(jsonMonthlySummary, Map.class);
		
		
		model.addObject("currentLocale", currentLocale);
		model.addObject("categories",categories);
		model.addObject("user", principal.getName());
		model.addObject("payment", new PaymentDTO());
		model.addObject("lastPaymentsByPrincipal", payments);
		model.addObject("paymentsThisMonth", paymentsThisMonth);
		model.addObject("amountsPerCategory", amountsPerCategory);
		model.addObject("amountsPerDate", amountsPerDate);
		model.addObject("monthlySum",monthlySummary.get("monthlySum"));
		model.addObject("monthlyAvg",monthlySummary.get("monthlyAvg"));
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
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(jsonRequest,headers);
		String jsonResponse = paymentsService.postForObject(PAYMENTSWS_URL +
				"payments/add", request, String.class);
		return "redirect:/";    	  
	}
}
