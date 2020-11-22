/**
 * 
 */
package com.example.Learnsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author rajivranjan
 *
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/mylogin").setViewName("mylogin");;
	}
	
}
