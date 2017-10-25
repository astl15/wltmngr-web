package ro.astl.wltmngr.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class WltMngrWebAuthenticationDetails extends WebAuthenticationDetails{
	
	private static final long serialVersionUID = 5241859654969047196L;
	private final boolean isDemo;

	public WltMngrWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.isDemo = Boolean.valueOf(request.getParameter("demo"));
	}

	public boolean isDemo() {
		return isDemo;
	}

}
