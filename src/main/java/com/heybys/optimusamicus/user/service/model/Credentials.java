package com.heybys.optimusamicus.user.service.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Credentials {

  /**
   * @see <a
   *     href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication#authentication_schemes">HTTP
   *     authentication - MDN Web Docs - Mozilla</a>
   */
  public enum Scheme {
    BASIC("Basic"),
    Bearer("Bearer"),
    DIGEST("Digest"),
    HOBA("HOBA"),
    Mutual("Mutual"),
    Negotiate("Negotiate"),
    NTLM("NTLM");

    private final String name;

    Scheme(String name) {
      this.name = name;
    }
  }

  private final Scheme scheme;
  private final String token;
  private final String username;
  private final String password;

  @Builder
  public Credentials(Scheme scheme, String token, String username, String password) {
    this.scheme = scheme;
    this.token = token;
    this.username = username;
    this.password = password;
  }

  public static Credentials of(String authorization) {
    assert authorization != null;

    if (authorization.startsWith(Scheme.BASIC.name)) {
      // authorization: Basic credentialsBase64Token
      String token = authorization.substring(Scheme.BASIC.name.length()).trim();
      byte[] bytes = Base64.getDecoder().decode(token);
      String plainToken = new String(bytes, StandardCharsets.UTF_8);

      // plainToken = username:password
      String[] split = plainToken.split(":", 2);
      if (!StringUtils.hasText(split[0]) || !StringUtils.hasText(split[1])) {
        throw new IllegalArgumentException(
            "As Credentials, username and password must have values.");
      }

      String username = split[0];
      String password = split[1];

      return Credentials.builder()
          .scheme(Scheme.BASIC)
          .token(token)
          .username(username)
          .password(password)
          .build();
    } else {
      throw new IllegalArgumentException(authorization + " cannot decoded.");
    }
  }
}
