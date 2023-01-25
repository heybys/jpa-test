package com.heybys.optimusamicus.common.filter;

import com.heybys.optimusamicus.common.exception.InvalidParameterException;
import com.heybys.optimusamicus.member.domain.MemberRepository;
import com.heybys.optimusamicus.member.service.AuthService;
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

  private final MemberRepository memberRepository;

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
        return new AuthService(memberRepository);
      case JWT:
        throw new InvalidParameterException("The JWT authentication service is not implemented.");
      case OAUTH:
        throw new InvalidParameterException("The OAuth authentication service is not implemented.");
      default:
        throw new InvalidParameterException("The authentication service type is wrong.");
    }
  }
}
