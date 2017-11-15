package ro.astl.wltmngr.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@Controller("menuController")
@Scope("session")
public class MenuController implements ViewPreparer {

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {
		attributeContext.putAttribute("menu", new Attribute("Value added by preparer"));
		
	}
	
}
