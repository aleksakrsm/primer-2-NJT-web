package it.engineering.spring.mvc.ds.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import it.engineering.spring.mvc.ds.formatter.CityDtoFormatter;
import it.engineering.spring.mvc.ds.service.CityService;

@Configuration
@ComponentScan(basePackages = { "it.engineering.spring.mvc.ds.controller" })
@EnableWebMvc
//@Import(value = SwaggerConfig.class)
public class MvcConfig implements WebMvcConfigurer {
	private CityService cityService;

	@Autowired
	public MvcConfig(CityService cityService) {
		this.cityService = cityService;
	}

	@Bean
	public ViewResolver tilesViewResolver() {
		System.out.println("=========================== tilesViewResolver =====================");
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		// gde se nalazi definicaja pogleda koji se vracaju korisniku
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/tiles/tiles.xml" });
		return tilesConfigurer;
	}

	@Bean
	public ViewResolver jspViewResolver() {
		System.out.println("=========================== jspViewResolver =====================");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		return resolver;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		System.out.println("================ public void addFormatters(FormatterRegistry registry) =================");
		registry.addFormatter(new CityDtoFormatter(cityService));
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("forward:/authentication"); // login
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }
	

//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
//	}
	
}
