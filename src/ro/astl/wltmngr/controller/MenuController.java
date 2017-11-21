package ro.astl.wltmngr.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@Controller("menuController")
@Scope("session")
public class MenuController implements ViewPreparer {

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		attributeContext.putAttribute("currentLocale", new Attribute(currentLocale),true);
	}	
}
