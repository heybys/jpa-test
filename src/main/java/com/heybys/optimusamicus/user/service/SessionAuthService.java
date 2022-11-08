package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.utils.HttpServletRequestProvider;
import com.heybys.optimusamicus.user.domain.entity.User;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionAuthService implements AuthService {

  private final UserService userService;

  @Override
  public boolean login() {
    HttpServletRequest request = HttpServletRequestProvider.getRequest();

    final String authorization = request.getHeader("Authorization");
    AuthInfo authInfo = parse(authorization);

    if (authInfo.hasInfo()) {
      User user = userService.findRegisteredUser(authInfo.getUsername(), authInfo.getPassword());
      if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionUser", user);
        return true;
      }
    }

    return false;
  }

  public void register() {}

  private AuthInfo parse(final String authorization) {
    if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
      // authorization: Basic base64credentials
      String base64Credentials = authorization.substring("Basic".length()).trim();
      byte[] decoded = Base64.getDecoder().decode(base64Credentials);
      String credentials = new String(decoded, StandardCharsets.UTF_8);
      // credentials = username:password
      String[] splits = credentials.split(":", 2);

      return AuthInfo.of(splits[0], splits[1]);
    }
    return AuthInfo.empty();
  }
}
