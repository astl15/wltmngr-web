package ro.astl.wltmngr.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import ro.ast.userservice.UserInstanceService;
import ro.ast.userservice.UserInstanceService_Service;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="ro.astl.wltmngr")
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Bean(name="PaymentsService")
	public RestTemplate getRestTemplate() {
		RestTemplate paymentsWS= new RestTemplate();
		return paymentsWS;
	}
	
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
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setOrder(1);
		return resolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/layout/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	 
	
	@Bean
	public TilesViewResolver getTilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
}
