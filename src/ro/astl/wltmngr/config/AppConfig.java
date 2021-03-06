package ro.astl.wltmngr.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.SimpleSpringPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import ro.astl.userservice.UserInstanceService;
import ro.astl.userservice.UserInstanceService_Service;

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
		proxy.setWsdlDocumentUrl(new URL("http://localhost:8081/UserInstance/UserInstanceWS?wsdl"));
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
		tilesConfigurer.setPreparerFactoryClass(SimpleSpringPreparerFactory.class);
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
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("resources/i18n/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver(){
	SessionLocaleResolver  resolver = new SessionLocaleResolver();
	   resolver.setDefaultLocale(new Locale("ro-RO"));
	   return resolver;
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
	LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	registry.addInterceptor(interceptor);
    }
}
