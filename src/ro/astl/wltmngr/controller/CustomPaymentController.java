package ro.astl.wltmngr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomPaymentController {
	@RequestMapping(value="/payments/custom", method=RequestMethod.GET)
	public ModelAndView getCustomPaymentController() {
		ModelAndView model = new ModelAndView();
		model.setViewName("customPayment");
		return model;
	}
}
