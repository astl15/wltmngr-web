package ro.astl.wltmngr.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class WltMngrUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	        final String isDemo = request.getParameter("isDemo");
	        request.getSession().setAttribute("isDemo", isDemo);

	        return super.attemptAuthentication(request, response); 
	    } 

}
