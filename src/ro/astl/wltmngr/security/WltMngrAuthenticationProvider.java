package ro.astl.wltmngr.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import ro.ast.userservice.ApplicationContext;
import ro.ast.userservice.UserInstanceIn;
import ro.ast.userservice.UserInstanceOut;
import ro.ast.userservice.UserInstanceService;

@Component
public class WltMngrAuthenticationProvider implements AuthenticationProvider {
	
	@Resource
	UserInstanceService userInstanceService;

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
			throw new BadCredentialsException("Bad format user");
		}
		if(password==null || "".equals(password)){
			throw new BadCredentialsException("Bad format password");
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
		
		/*Map response*/
		String responseCode = userInstanceOut.getResponseCode();
		String usernameDB = userInstanceOut.getUsername();
		String passwordDB = userInstanceOut.getPassword();
		
		if("NODATA".equals(responseCode)){
			throw new BadCredentialsException("Username not found");
		}else if("SUCCESS".equals(responseCode)){
			if(!passwordDB.equals(password)) {
				throw new BadCredentialsException("Incorrect password");
			}
		}
		
		return new UsernamePasswordAuthenticationToken(username,password);
		
		
		
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);	
	}

}
