package com.heybys.optimusamicus.common.filter;

import com.heybys.optimusamicus.common.exception.InvalidParameterException;
import com.heybys.optimusamicus.user.domain.UserRepository;
import com.heybys.optimusamicus.user.service.AuthService;
import com.heybys.optimusamicus.user.service.SessionAuthService;
import javax.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFactory {

  public enum AuthType {
    SESSION,
    JWT,
    OAUTH
  }

  private final UserRepository userRepository;

  private static final AuthType authType = AuthType.SESSION;

  public Filter getFilter() {
    switch (authType) {
      case SESSION:
        return new SessionAuthFilter();
      case JWT:
        throw new InvalidParameterException("The JWT authentication filter is not implemented.");
      case OAUTH:
        throw new InvalidParameterException("The OAuth authentication filter is not implemented.");
      default:
        throw new InvalidParameterException("The authentication filter type is wrong.");
    }
  }

  public AuthService getService() {
    switch (authType) {
      case SESSION:
        return new SessionAuthService(userRepository);
      case JWT:
        throw new InvalidParameterException("The JWT authentication service is not implemented.");
      case OAUTH:
        throw new InvalidParameterException("The OAuth authentication service is not implemented.");
      default:
        throw new InvalidParameterException("The authentication service type is wrong.");
    }
  }
}
