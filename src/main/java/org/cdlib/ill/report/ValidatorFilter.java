package org.cdlib.ill.report;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * Evaluates incoming requests and checks whether they meet criteria to be legitimate requests. Drop
 * those that do not meet criteria.
 * 
 * Logs info about incoming requests, particularly HTTP POST requests.
 * 
 * Deny all subsequent requests from IPs that have made a disallowed POST.
 * 
 */
@Component
@Order(1)
public class ValidatorFilter implements Filter {

  private static Logger logger = LoggerFactory.getLogger(ValidatorFilter.class);
  private List<String> denyList = new ArrayList<>();

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (denyList.contains(httpRequest.getRemoteAddr())) {
      logger.debug("Request" + " from " + request.getRemoteAddr() + " rejected by denyList.");
      return;
    }
    if (httpRequest.getMethod().equalsIgnoreCase("GET")) {
      logger.debug("GET request from " + request.getRemoteAddr());
      chain.doFilter(httpRequest, response);
    } else {
      logPost(httpRequest);
      if (isValidPost(httpRequest)) {
        chain.doFilter(httpRequest, response);
      } else {
        logger.debug(request.getRemoteAddr() + " added to deny list.");
        denyList.add(httpRequest.getRemoteAddr());
      }
    }
  }

  private void logPost(HttpServletRequest httpRequest) {
    logger.debug("POST request from " + httpRequest.getRemoteAddr());
    logger.debug("request content length: " + String.valueOf(httpRequest.getContentLength()));
    Map<String, String[]> params = httpRequest.getParameterMap();
    logger.debug(String.valueOf(params.size()) + " parameters in request.");
    for (String key : params.keySet()) {
      String[] vals = params.get(key);
      if (vals == null || vals.length == 0 || vals[0] == null) {
        logger.debug(key + " = null");
      } else {
        logger.debug(key + " = " + vals[0]);
      }
    }
  }

  private boolean isValidPost(HttpServletRequest httpRequest) {
    if (httpRequest.getContentLength() > 56) {
      logger.debug("Content length greater than 56");
      return false;
    }
    if (httpRequest.getContentLength() < 55) {
      logger.debug("Content length less than 55");
      return false;
    }

    Map<String, String[]> params = httpRequest.getParameterMap();
    if (params == null) {
      logger.debug("params is null");
      return false;
    }
    if (params.size() != 4) {
      logger.debug("More than 4 parameters");
      return false;
    }
    if (!isValidCommand(httpRequest.getParameter("command"))) {
      logger.debug("invalid command: " + httpRequest.getParameter("command"));
      return false;
    }
    if (!isValidCampus(httpRequest.getParameter("campus"))) {
      logger.debug("Invalid campus: " + httpRequest.getParameter("campus"));
      return false;
    }
    if (!isValidDate(httpRequest.getParameter("to"))) {
      logger.debug("Invalid date: " + httpRequest.getParameter("to"));
      return false;
    }
    if (!isValidDate(httpRequest.getParameter("from"))) {
      logger.debug("Invalid date: " + httpRequest.getParameter("from"));
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
    return campus.matches(Constants.CAMPUSES_MATCH);
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
