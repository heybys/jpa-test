package com.heybys.optimusamicus.user.controller.dto.patch;

import com.heybys.optimusamicus.user.domain.entity.User;
import javax.validation.constraints.Pattern;
import lombok.Data;

public class UserPatch {

  @Data
  public static class Request {

    private String username;

    private String password;

    private User.Type type;

    @Pattern(
        regexp = "01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})",
        message = "Invalid phoneNumber format")
    private String phoneNumber;

    private String address;

    private String selfIntroduction;

    private Long userGroupId;
  }
}
