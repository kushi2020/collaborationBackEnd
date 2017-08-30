package com.col.sol.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc

 
@ComponentScan(basePackages="com.col.sol")
public class WebConfig extends WebMvcConfigurerAdapter {
	/*@Bean
	public InternalResourceViewResolver getViewResolver()
	{ 
		System.out.println("View Resolver:");
     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); 
		 		viewResolver.setPrefix("/WEB-INF/"); 
		 		viewResolver.setSuffix(".jsp"); 
		 		return viewResolver; 
	 }*/
	@Bean
	public ViewResolver viewResolver() {
		System.out.println("Starting of the metnod viewResolver");
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		//InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".html");
		System.out.println("Ending of the metnod viewResolver");

		return viewResolver;
	}
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}



}
