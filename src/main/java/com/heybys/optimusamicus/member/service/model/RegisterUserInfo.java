package com.heybys.optimusamicus.member.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUserInfo {

  @NotNull(message = "need to address")
  private String address;

  @NotNull(message = "need to phoneNumber")
  @Pattern(
      regexp = "01([016789])(\\d{3}|\\d{4})(\\d{4})",
      message = "Invalid phoneNumber format")
  private String phoneNumber;

  @Pattern(regexp = "^(\\S+)@(\\S+)\\.(\\S+)$|null", message = "Invalid email format")
  private String email;
}
