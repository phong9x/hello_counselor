package org.trams.hello.web.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
public class LoggerInterceptor extends HandlerInterceptorAdapter {
    
    protected Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("============================================================================");
            log.debug(" Request URI:  [" + request.getMethod() + "] " + request.getRequestURI());
            
            @SuppressWarnings("unchecked")
			Enumeration<String> headerNames = request.getHeaderNames();

            if (headerNames != null) {
            	log.debug(" Request Header:  ");
                while (headerNames.hasMoreElements()) {
                	String headerName = headerNames.nextElement();
                	String value = request.getHeader(headerName);
                	log.debug("  " + headerName + ": " + value);
                }
            }
            
            @SuppressWarnings("unchecked")
            Enumeration<String> paramNames = request.getParameterNames();
            
            if (paramNames != null) {
            	log.debug(" Request body:  ");
                while (paramNames.hasMoreElements()) {
                	String paramName = paramNames.nextElement();
                	String value = request.getParameter(paramName);
                	log.debug("  [ " + paramName + ": " + value + " ]");
                }
            }
            log.debug("============================================================================");
        }
        return super.preHandle(request, response, handler);
    }
}
 

