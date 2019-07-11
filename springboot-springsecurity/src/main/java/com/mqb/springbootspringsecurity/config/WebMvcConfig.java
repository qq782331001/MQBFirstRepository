package com.mqb.springbootspringsecurity.config;


import com.mqb.springbootspringsecurity.utils.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
//这里最开始继承的是WebMvcConfigurerAdapter，但该类已经过时了，但它实现的是WebMvcConfigurer类，
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(
				new DateConverter());
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newCachedThreadPool();
	}
}
