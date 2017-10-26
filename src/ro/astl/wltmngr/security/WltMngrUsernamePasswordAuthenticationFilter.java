package ro.astl.wltmngr.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class WltMngrUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
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
		
		WltMngrUsernamePasswordAuthenticationToken authentication = null;
		if("true".equals(isDemo)){
			authentication = new WltMngrUsernamePasswordAuthenticationToken(username, password, true);
		}else{
			authentication = new WltMngrUsernamePasswordAuthenticationToken(username, password, false);
		}

		setDetails(request, authentication);
		return this.getAuthenticationManager().authenticate(authentication);
		//return super.attemptAuthentication(request, response);
	}
	 
	 
	public String obtainDemoMode(HttpServletRequest request){
		return request.getParameter("demo");
	}

}
