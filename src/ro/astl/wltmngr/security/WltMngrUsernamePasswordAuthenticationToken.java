package ro.astl.wltmngr.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class WltMngrUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3599475700338978822L;
	
	private boolean isDemo;
	
	public WltMngrUsernamePasswordAuthenticationToken(Object principal, Object credentials, boolean isDemo) {
		super(principal, credentials);
		this.isDemo = isDemo;
	}

}
