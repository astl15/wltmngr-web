package ro.astl.wltmngr.config;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
	public WltMngrUsernamePasswordAuthenticationFilter getWltMngrFilter() throws Exception{
		WltMngrUsernamePasswordAuthenticationFilter filter = new WltMngrUsernamePasswordAuthenticationFilter();
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/login?error");
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
            //auth.inMemoryAuthentication()
                //.withUser("user").password("password").roles("USER");
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
        	.csrf()
        		.disable()
        	.authorizeRequests()
        		.antMatchers("/register").permitAll()
        		.anyRequest().authenticated()
        	.and()
        	.formLogin()
        		.loginPage("/login")
        		.permitAll()	
        	.and()
        	.addFilter(getWltMngrFilter())
        	.httpBasic();
    }
}
