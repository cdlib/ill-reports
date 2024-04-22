package org.cdlib.ill.report;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
      logGet(httpRequest);
      chain.doFilter(httpRequest, response);
    } else {
      logPost(httpRequest);
      if (isValid(httpRequest)) {
        chain.doFilter(httpRequest, response);
      }
    }
  }
  
  private void logGet(HttpServletRequest request) {
    logger.info("GET request" + " from " + request.getRemoteAddr());
  }

  private void logPost(HttpServletRequest httpRequest) {
    logger.info("POST request from " + httpRequest.getRemoteAddr());
    logger.info("size: " + String.valueOf(httpRequest.getContentLength()));
    Map<String, String[]> params = httpRequest.getParameterMap();
    for (String key : params.keySet()) {
      String[] vals = params.get(key);
      if (vals == null) {
        logger.info(key + "=null");
      } else if (vals.length == 0) {
        logger.info(key + "=");
      } else {
        logger.info(key + "=" + vals[0]);
      }
    }
  }

  private boolean isValid(HttpServletRequest httpRequest) {
    if (httpRequest.getContentLength() > 56) {
      return false;
    }
    if (httpRequest.getContentLength() < 55) {
      return false;
    }

    Map<String, String[]> params = httpRequest.getParameterMap();
    if (params == null) {
      return false;
    }
    if (params.size() != 4) {
      return false;
    }
    if (!isValidCommand(httpRequest.getParameter("command"))) {
      return false;
    }
    if (!isValidCampus(httpRequest.getParameter("campus"))) {
      return false;
    }
    if (!isValidDate(httpRequest.getParameter("to"))) {
      return false;
    }
    if (!isValidDate(httpRequest.getParameter("from"))) {
      return false;
    }
    return true;
  }

  private boolean isValidCommand(String command) {
    if (command == null) {
      return false;
    }
    return command.equals("Search");
  }

  private boolean isValidCampus(String campus) {
    if (campus == null) {
      return false;
    }
    return campus.matches("all|OELA|NRLF|SRLF|UCB|UCD|UCI|UCLA|UCM|UCR|UCSB|UCSC|UCSD|UCSF");
  }

  private boolean isValidDate(String date) {
    if (date == null || date.trim().isEmpty()) {
      return false;
    }
    try {
      LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

}
