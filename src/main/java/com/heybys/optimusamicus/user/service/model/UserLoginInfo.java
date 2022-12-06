package com.heybys.optimusamicus.user.service.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginInfo {

  private String username;
  private String password;

  public static UserLoginInfo of(String authorization) {
    UserLoginInfo userLoginInfo = new UserLoginInfo();
    userLoginInfo.parse(authorization);
    return userLoginInfo;
  }

  private void parse(final String authorization) {
    if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
      // authorization: Basic base64credentials
      String base64Credentials = authorization.substring("Basic".length()).trim();
      byte[] decoded = Base64.getDecoder().decode(base64Credentials);
      String credentials = new String(decoded, StandardCharsets.UTF_8);
      // credentials = username:password
      String[] splits = credentials.split(":", 2);

      this.username = splits[0];
      this.password = splits[1];
    }
    throw new IllegalArgumentException();
  }
}
