package org.cdlib.ill.report;

import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidatorFilter implements Filter {
  
  private static Logger logger = LoggerFactory.getLogger(ValidatorFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    HttpServletRequest httpRequest = (HttpServletRequest)request;
    logger.info(String.valueOf(httpRequest.getContentLength()));
    Map<String, String[]> params = httpRequest.getParameterMap();
    for (String s : params.keySet()) {
      logger.info(s);
    }
    if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
      chain.doFilter(httpRequest, response);
    } else {
      if (isValid(httpRequest)) {
        chain.doFilter(httpRequest, response);
      }
    }
    
  }
  
  private boolean isValid(HttpServletRequest httpRequest) {
    return true;
  }

}
