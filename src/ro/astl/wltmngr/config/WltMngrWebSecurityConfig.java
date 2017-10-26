package ro.astl.wltmngr.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ro.astl.wltmngr.security.WltMngrAuthenticationProvider;
import ro.astl.wltmngr.security.WltMngrUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WltMngrWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	WltMngrAuthenticationProvider provider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
		authenticationProviderList.add(provider);
		AuthenticationManager authenticationManager = new ProviderManager(
				authenticationProviderList);
		return authenticationManager;
	}
	
	@Bean
	public WltMngrUsernamePasswordAuthenticationFilter getWltMngrFilter() throws Exception{
		WltMngrUsernamePasswordAuthenticationFilter filter = new WltMngrUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		//filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler());
		return filter;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring().antMatchers("/resources/**")
			.and()
			.debug(true);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        	.anyRequest().authenticated()
        	.and()
        	.formLogin()
          	.loginPage("/login")
          	.permitAll()
          	.and()
          	.addFilter(getWltMngrFilter());
        
    }
}
