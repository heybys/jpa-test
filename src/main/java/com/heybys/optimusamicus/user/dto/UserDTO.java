package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDTO {


  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private String username;

    public User toUser() {
      return User.builder().userType(UserType.NORMAL).username(username).build();
    }
  }

  @Getter
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private final Long userId;

    private final String username;

    private final String userType;

    public Response(User user) {
      this.userId = user.getUserId();
      this.username = user.getUsername();
      this.userType = user.getUserType().name();
    }
  }

}
