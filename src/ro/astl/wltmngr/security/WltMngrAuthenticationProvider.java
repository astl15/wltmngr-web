package ro.astl.wltmngr.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class WltMngrAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = null; 
		String password = null;
		if(auth != null && auth.getName()!=null){
			username = auth.getName().trim();
		}
		if(auth !=null && auth.getCredentials()!=null){
			password = auth.getCredentials().toString();
		}
		
		if(username==null || "".equals(username)){
			throw new BadCredentialsException("Bad user");
		}
		if(password==null || "".equals(password)){
			throw new BadCredentialsException("Bad password");
		}
		return new UsernamePasswordAuthenticationToken(username,password);
		
		
		
	}

	@Override
	public boolean supports(Class<?> auth) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth);	
	}

}
