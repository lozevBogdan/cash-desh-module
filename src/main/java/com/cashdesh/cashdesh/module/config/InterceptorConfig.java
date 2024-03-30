package com.cashdesh.cashdesh.module.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cashdesh.cashdesh.module.web.interceptor.ApiKeyInterceptor;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private ApiKeyInterceptor apiKeyInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiKeyInterceptor);
	}

}
