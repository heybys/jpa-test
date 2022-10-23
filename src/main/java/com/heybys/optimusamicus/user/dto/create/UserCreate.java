package com.heybys.optimusamicus.user.dto.create;

import com.heybys.optimusamicus.user.entity.User;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

public class UserCreate {

  @Data
  public static class Request {

    @NotNull(message = "need to username")
    private String username;

    @NotNull(message = "need to type")
    private User.Type type;

    @NotNull(message = "need to phoneNumber")
    @Pattern(
        regexp = "01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})",
        message = "Invalid phoneNumber format")
    private String phoneNumber;

    @NotNull(message = "need to address")
    private String address;

    private String selfIntroduction;

    private Long userGroupId;

    public User toUser() {
      return User.builder()
          .name(username)
          .type(type)
          .phoneNumber(phoneNumber)
          .address(address)
          .selfIntroduction(selfIntroduction)
          .build();
    }
  }
}
