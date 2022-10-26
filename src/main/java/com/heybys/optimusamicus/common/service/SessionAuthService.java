package com.heybys.optimusamicus.common.service;

import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.service.UserService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
public class SessionAuthService implements AuthService {

  private final UserService userService;

  @Override
  public boolean authenticate() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    assert requestAttributes instanceof ServletRequestAttributes;

    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

    final String authorization = request.getHeader("Authorization");
    String[] usernamePassword = parse(authorization);
    if (usernamePassword != null) {
      String username = usernamePassword[0];
      String password = usernamePassword[1];

      User user = userService.findRegisteredUser(username, password);
      if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionUser", user);
        return true;
      }
    }

    return false;
  }

  private String[] parse(final String authorization) {
    if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
      // authorization: Basic base64credentials
      String base64Credentials = authorization.substring("Basic".length()).trim();
      byte[] decoded = Base64.getDecoder().decode(base64Credentials);
      String credentials = new String(decoded, StandardCharsets.UTF_8);
      // credentials = username:password
      return credentials.split(":", 2);
    }
    return null;
  }
}
