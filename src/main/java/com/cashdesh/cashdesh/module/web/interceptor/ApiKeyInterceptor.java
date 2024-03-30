package com.cashdesh.cashdesh.module.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor  {

	@Value("${fib.api.key}")
    private String API_KEY;

	@Value("${fib.api.headerName}")
    private String HEADER_NAME;
	
	  @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        String apiKey = request.getHeader(HEADER_NAME);
	        if (apiKey == null || !apiKey.equals(API_KEY)) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
	            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, String> errorMap = new HashMap<>();
	            errorMap.put("error", "Invalid API key");
	            String errorJson = objectMapper.writeValueAsString(errorMap);
	            response.getWriter().write(errorJson);
	            return false;
	        }
	        return true;
	    }
	
}
