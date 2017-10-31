package ro.astl.wltmngr.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ro.ast.userservice.ApplicationContext;
import ro.ast.userservice.UserInstanceIn;
import ro.ast.userservice.UserInstanceOut;
import ro.ast.userservice.UserInstanceService;

public class WltMngrUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Resource
	UserInstanceService userInstanceService;
	
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
		
		/*Map request*/
		ApplicationContext appContext = new ApplicationContext();
		appContext.setApplicationName("WLTMNGR");
		UserInstanceIn userInstanceRequest = new UserInstanceIn();
		userInstanceRequest.setConxtext(appContext);
		userInstanceRequest.setUsername(username);
		
		/*Call UserInstance WS*/
		UserInstanceOut userInstanceOut = new UserInstanceOut();
		if(userInstanceService!=null) {
			 userInstanceOut = userInstanceService.getUserbyUsername(userInstanceRequest);
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
