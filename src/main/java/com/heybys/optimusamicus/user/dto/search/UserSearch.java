package com.heybys.optimusamicus.user.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import lombok.Builder;
import lombok.Data;

public class UserSearch {

  @Data
  public static class Request {

    private String name;
    private UserType userType;
    private String groupName;
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long id;
    private UserType userType;
    private String name;
    private String phoneNumber;
    private String address;

    @JsonProperty(value = "userGroup")
    private UserGroupSearch.Response userGroupSearchResponse;

    @Builder
    public Response(Long id, UserType userType, String name, String phoneNumber, String address) {
      this.id = id;
      this.userType = userType;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.address = address;
    }

    public static Response from(User user) {
      Response response =
          Response.builder()
              .id(user.getUserId())
              .userType(user.getUserType())
              .name(user.getUsername())
              .phoneNumber(user.getPhoneNumber())
              .address(user.getAddress())
              .build();

      if (user.getUserGroup() != null) {
        UserGroupSearch.Response userGroup = UserGroupSearch.Response.from(user.getUserGroup());
        response.setUserGroupSearchResponse(userGroup);
      }

      return response;
    }
  }
}
