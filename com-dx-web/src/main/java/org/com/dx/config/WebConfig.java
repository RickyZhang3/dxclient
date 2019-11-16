package org.com.dx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	/**
     * 跨域支持
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//    	super.addCorsMappings(registry);
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT","OPTIONS");
//    }
}
