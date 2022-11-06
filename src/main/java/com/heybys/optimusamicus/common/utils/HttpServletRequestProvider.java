package com.heybys.optimusamicus.common.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpServletRequestProvider {

  public static HttpServletRequest getRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    assert requestAttributes instanceof ServletRequestAttributes;

    return ((ServletRequestAttributes) requestAttributes).getRequest();
  }
}
