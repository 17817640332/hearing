package com.jie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**","/words/**")
                .addResourceLocations(("file:"+"/usr/local/java1/upload/"))
        .addResourceLocations(("file:"+"/usr/local/java1/words/"));
    }
   //System.getProperty("user.dir")+"/words/"

}
