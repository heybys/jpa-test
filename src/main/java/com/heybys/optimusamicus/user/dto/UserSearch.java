package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    private UserType userType;
    private String username;
    private String phoneNumber;
    private String address;
    private String userGroupName;

    @Builder
    public Response(User user) {
      BeanUtils.copyProperties(user, this);
      this.userGroupName = user.getUserGroup().getUserGroupName();
    }
  }
}
