package com.heybys.optimusamicus.user.service.model;

import com.heybys.optimusamicus.user.domain.entity.User;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegisterInfo {

  private String username;
  private String password;
  private Additional additional;

  public static UserRegisterInfo of(String authorization, Additional additional) {
    UserRegisterInfo userRegisterInfo = new UserRegisterInfo();
    userRegisterInfo.parse(authorization);
    userRegisterInfo.set(additional);
    return userRegisterInfo;
  }

  public User toUser() {
    return User.builder()
        .username(this.username)
        .password(this.password)
        .address(this.additional.getAddress())
        .phoneNumber(this.additional.getPhoneNumber())
        .email(this.additional.getEmail())
        .build();
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

  private void set(final Additional additional) {
    this.additional = additional;
  }

  @Data
  public static class Additional {

    @NotNull(message = "need to address")
    private String address;

    @NotNull(message = "need to phoneNumber")
    @Pattern(
        regexp = "01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})",
        message = "Invalid phoneNumber format")
    private String phoneNumber;

    private String email;
  }
}
