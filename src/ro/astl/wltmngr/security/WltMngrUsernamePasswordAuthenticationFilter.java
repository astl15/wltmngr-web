package ro.astl.wltmngr.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ro.astl.userservice.ApplicationContext;
import ro.astl.userservice.UserInstanceIn;
import ro.astl.userservice.UserInstanceOut;
import ro.astl.userservice.UserInstanceService;

public class WltMngrUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	public static final String DEMO_USERNAME = "demo";
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String isDemo = obtainDemoMode(request);
		
		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		
		if (isDemo == null) {
			isDemo = "";
		}
		
		UsernamePasswordAuthenticationToken authentication = null;
		if("on".equals(isDemo)){
			username = DEMO_USERNAME;
		}
		authentication = new UsernamePasswordAuthenticationToken(username, password);

		setDetails(request, authentication);
		return this.getAuthenticationManager().authenticate(authentication);
		//return super.attemptAuthentication(request, response);
	}
	 
	 
	public String obtainDemoMode(HttpServletRequest request){
		return request.getParameter("demo");
	}

}
