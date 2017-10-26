package ro.astl.wltmngr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ro.astl.wltmngr.security.WltMngrAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WltMngrWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	WltMngrAuthenticationProvider provider;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.addFilterBefore(new WltMngrUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        	.authorizeRequests()
        	.anyRequest().authenticated()
        	.and()
        	.formLogin()
          	.loginPage("/login")
          	.permitAll();
    }
}
