package cl.crojas.blog.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * Configure ContentNegotiationManager.
	 * 
	 * La Anotación @Override indica que este método es sobreescrito de la clase que
	 * hereda o interfaz que implementa
	 * 
	 * @param configurer Objeto ContentNegotiationConfigurer que especifica el Tipo
	 *                   de Protocolo para Intercambio de Mensajes
	 * @author Christian Rojas N.
	 * 
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}

	/**
	 * Static Resource Config.
	 * 
	 * La Anotación @Override indica que este método es sobreescrito de la clase que
	 * hereda o interfaz que implementa
	 * 
	 * @param registry Objeto ResourceHandler que gestiona cuáles Recursos serán
	 *                 visibles y accesibles en la Interfaz Web
	 * @author Christian Rojas N.
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}

	/**
	 * MessageSource.
	 * 
	 * La Anotación @Bean indica que este método será instanciado como un Bean al
	 * Iniciar la Aplicación
	 * 
	 * @return Objeto MessageSource que representa el Bean de Negocio para la
	 *         Gestión de Mensajes Properties
	 * @author Christian Rojas N.
	 * 
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/mutual-messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * ConfigureDefaultServletHandling.
	 * 
	 * La Anotación @Override indica que este método es sobreescrito de la clase que
	 * hereda o interfaz que implementa
	 * 
	 * @param configurer Objeto DefaultServletHandlerConfigurer que permite
	 *                   redireccionar todos los Request al Servlet por Defecto.
	 * 
	 * @author Christian Rojas N.
	 * 
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
