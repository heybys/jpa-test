package com.heybys.optimusamicus.common.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
// @WebFilter(filterName = "SessionAuthFilter")
public class SessionAuthFilter implements Filter {

  private static final String[] whitelist = {"/api/v1/auth/login", "/api/v1/auth/logout"};

  public void init(FilterConfig config) throws ServletException {}

  public void destroy() {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String requestURI = httpRequest.getRequestURI();
    log.info("requestURI : {}", requestURI);

    if (isLoginRequired(requestURI)) {
      HttpSession session = httpRequest.getSession(false);
      if (session == null) {
        // httpResponse.sendRedirect("/api/v1/auth/login?redirectURL=" + requestURI);
        return;
      } else {
        Enumeration<String> attributeNames = session.getAttributeNames();
        for (String attributeName : Collections.list(attributeNames)) {
          Object attribute = session.getAttribute(attributeName);
          log.info("{} : {}", attributeName, attribute);
        }
      }
    }

    chain.doFilter(request, response);
  }

  private boolean isLoginRequired(String requestURI) {
    return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }
}
