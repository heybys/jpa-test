package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class UserCreate {

  @Data
  public static class Request {

    @NotNull private String username;

    @NotNull private UserType userType;

    @NotNull private String phoneNumber;

    @NotNull private String address;

    private Long userGroupId;

    public User toUser() {
      return User.builder()
          .username(username)
          .userType(userType)
          .phoneNumber(phoneNumber)
          .address(address)
          .build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long userId;
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
