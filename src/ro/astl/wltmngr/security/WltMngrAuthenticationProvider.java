package ro.astl.wltmngr.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class WltMngrAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName().trim();
		String password = auth.getCredentials().toString();
		Object authDetails = auth.getDetails();
		if(authDetails instanceof WltMngrWebAuthenticationDetails){
			WltMngrWebAuthenticationDetails details = (WltMngrWebAuthenticationDetails) authDetails;
			System.out.println("username:" + username + " password:" + password + " details:" + details.isDemo());
			return new UsernamePasswordAuthenticationToken(username,password);
		}
		
		
		throw new BadCredentialsException("Bad user");
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);	
	}

}
