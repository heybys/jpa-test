package com.heybys.optimusamicus.user.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthInfo {

  private String username;
  private String password;

  public boolean hasInfo() {
    return StringUtils.hasLength(username) && StringUtils.hasLength(password);
  }

  public static AuthInfo of(String username, String password) {
    return new AuthInfo(username, password);
  }

  public static AuthInfo empty() {
    return new AuthInfo();
  }
}
