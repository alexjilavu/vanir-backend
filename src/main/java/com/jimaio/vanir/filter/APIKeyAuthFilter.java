//package com.jimaio.vanir.filter;
//
//import java.io.IOException;
//import java.util.Enumeration;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.jimaio.vanir.service.UserService;
//
//@Component
//@Order(1)
//public class APIKeyAuthFilter implements Filter {
//
//	@Autowired
//	UserService userService;
//
//	@Override
//	public void doFilter(ServletRequest request, javax.servlet.ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		if (httpRequest.getRequestURI().contains("login") || httpRequest.getRequestURI().contains("register"))
//			chain.doFilter(httpRequest, response);
//		else {
//			Enumeration<String> headerNames = httpRequest.getHeaderNames();
//			while(headerNames.hasMoreElements()) {
//				String key = headerNames.nextElement();
//				String value = httpRequest.getHeader(key);
//			}
//			
//			chain.doFilter(httpRequest, response);
//		}
//	}
// 
//
//}
