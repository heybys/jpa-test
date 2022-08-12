package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import javax.validation.constraints.NotNull;
import lombok.Data;

public class UserCreate {


  @Data
  public static class Request {

    @NotNull
    private String username;

    private UserType userType = UserType.NORMAL;

    public User toUser() {
      return User.builder().userType(userType).username(username).build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long userId;

    public Response(User user) {
      this.userId = user.getUserId();
    }
  }

}
