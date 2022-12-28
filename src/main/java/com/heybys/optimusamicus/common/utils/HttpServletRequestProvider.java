package com.heybys.optimusamicus.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class HttpServletRequestProvider {

  public static HttpServletRequest getRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    assert requestAttributes instanceof ServletRequestAttributes;

    return ((ServletRequestAttributes) requestAttributes).getRequest();
  }

  public static HttpSession getSession() {
    return getRequest().getSession();
  }

  public static HttpSession getSession(boolean create) {
    return getRequest().getSession(create);
  }
}
