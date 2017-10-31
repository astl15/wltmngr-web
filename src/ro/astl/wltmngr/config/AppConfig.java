package ro.astl.wltmngr.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ro.ast.userservice.UserInstanceService;
import ro.ast.userservice.UserInstanceService_Service;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="ro.astl.wltmngr")
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean(name="UserInstanceService")
	public JaxWsPortProxyFactoryBean getProxy() throws MalformedURLException {
		JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
		proxy.setServiceInterface(UserInstanceService.class);
		proxy.setWsdlDocumentUrl(new URL("http://localhost:8181/UserInstance/UserInstanceWS?wsdl"));
		proxy.setNamespaceUri("http://ws.userservice.astl.ro/");
		//proxy.setEndpointAddress("http://ws.userservice.astl.ro/");
		proxy.setServiceName("UserInstanceService");
		proxy.setPortName("UserInstanceImplPort");
		return proxy;
	}
	
	/*@Bean
	public UserInstanceService getService() throws MalformedURLException {
		return (UserInstanceService)getProxy().;
	}*/
	
	
	
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
}
