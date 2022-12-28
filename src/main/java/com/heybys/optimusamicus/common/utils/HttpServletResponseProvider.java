package com.heybys.optimusamicus.common.utils;

import javax.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class HttpServletResponseProvider {

  public static HttpServletResponse getResponse() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    assert requestAttributes instanceof ServletRequestAttributes;

    return ((ServletRequestAttributes) requestAttributes).getResponse();
  }

}
