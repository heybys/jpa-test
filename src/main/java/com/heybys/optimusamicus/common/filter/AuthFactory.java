package com.heybys.optimusamicus.common.filter;

import com.heybys.optimusamicus.user.domain.repository.UserRepository;
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
    OAuth
  }

  private final UserRepository userRepository;

  private final AuthType authType = AuthType.SESSION;

  public Filter getFilter() {
    switch (authType) {
      case SESSION:
        return new SessionAuthFilter();
      case JWT:
        throw new RuntimeException("The JWT authentication filter is not implemented.");
      case OAuth:
        throw new RuntimeException("The OAuth authentication filter is not implemented.");
      default:
        throw new RuntimeException("The authentication filter type is wrong.");
    }
  }

  public AuthService getService() {
    switch (authType) {
      case SESSION:
        return new SessionAuthService(userRepository);
      case JWT:
        throw new RuntimeException("The JWT authentication service is not implemented.");
      case OAuth:
        throw new RuntimeException("The OAuth authentication service is not implemented.");
      default:
        throw new RuntimeException("The authentication service type is wrong.");
    }
  }
}
