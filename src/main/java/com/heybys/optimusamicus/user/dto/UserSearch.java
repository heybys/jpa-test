package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import lombok.Data;

public class UserSearch {


  @Data
  public static class Request {

    private String username;

    private UserType userType;

    private String userGroupName;
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private final String username;

    private final String userType;

    public Response(User user) {
      this.username = user.getUsername();
      this.userType = user.getUserType().name();
    }
  }

}
