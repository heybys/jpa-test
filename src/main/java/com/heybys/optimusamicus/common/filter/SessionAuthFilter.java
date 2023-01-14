package com.heybys.optimusamicus.common.filter;

import com.heybys.optimusamicus.user.service.model.AuthConst;
import com.heybys.optimusamicus.user.service.model.SessionUserInfo;
import java.io.IOException;
import java.util.EnumMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class SessionAuthFilter implements Filter {

  private static final EnumMap<HttpMethod, String[]> whitelist = new EnumMap<>(HttpMethod.class);

  @Override
  public void init(FilterConfig config) throws ServletException {
    whitelist.put(HttpMethod.OPTIONS, new String[]{"*"});
    whitelist.put(HttpMethod.GET, new String[]{});
    whitelist.put(
        HttpMethod.POST,
        new String[]{"/api/v1/user", "/api/v1/login"});
    whitelist.put(HttpMethod.PUT, new String[]{});
    whitelist.put(HttpMethod.PATCH, new String[]{});
    whitelist.put(HttpMethod.DELETE, new String[]{});
  }

  @Override
  public void destroy() {
    // not yet implemented.
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    String requestURI = httpRequest.getRequestURI();
    String method = httpRequest.getMethod();
    log.info("requestURI : [{}] {}", method, requestURI);

    if (isLoginRequired(HttpMethod.valueOf(method), requestURI)) {
      HttpSession session = httpRequest.getSession(false);
      if (session == null) {
        throw new IllegalArgumentException();
      } else {
        SessionUserInfo sessionUserInfo = (SessionUserInfo) session.getAttribute(
            AuthConst.USER_INFO);
        log.info("userProfile : {}", sessionUserInfo);
      }
    }

    chain.doFilter(request, response);
  }

  private boolean isLoginRequired(HttpMethod method, String requestURI) {
    return !PatternMatchUtils.simpleMatch(whitelist.get(method), requestURI);
  }
}
