package com.heybys.optimusamicus.user.validator;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

  public void validate(Map<String, Object> params) {
    if (params.containsKey("phoneNumber")) {
      String phoneNumber = params.get("phoneNumber").toString();

      if (phoneNumber.matches("01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})")) {
        throw new IllegalArgumentException();
      }
    }
  }
}
