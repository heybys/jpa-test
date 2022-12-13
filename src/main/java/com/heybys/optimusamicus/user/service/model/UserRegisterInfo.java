package com.heybys.optimusamicus.user.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterInfo {

  @NotNull(message = "need to address")
  private String address;

  @NotNull(message = "need to phoneNumber")
  @Pattern(
      regexp = "01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})",
      message = "Invalid phoneNumber format")
  private String phoneNumber;

  @Pattern(regexp = "^(\\S+)@(\\S+)\\.(\\S+)$|null", message = "Invalid email format")
  private String email;
}
