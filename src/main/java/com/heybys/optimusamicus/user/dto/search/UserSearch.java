package com.heybys.optimusamicus.user.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heybys.optimusamicus.user.entity.User;
import lombok.Builder;
import lombok.Data;

public class UserSearch {

  @Data
  public static class Request {

    private String username;
    private User.Type userType;
    private String userGroupName;
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long userId;
    private User.Type userType;
    private String username;
    private String userPhoneNumber;
    private String userAddress;

    @JsonProperty(value = "userGroup")
    private UserGroupSearch.Response userGroupSearchResponse;

    @Builder
    public Response(
        Long userId,
        User.Type userType,
        String username,
        String userPhoneNumber,
        String userAddress) {
      this.userId = userId;
      this.userType = userType;
      this.username = username;
      this.userPhoneNumber = userPhoneNumber;
      this.userAddress = userAddress;
    }

    public static Response from(User user) {
      Response response =
          Response.builder()
              .userId(user.getId())
              .userType(user.getType())
              .username(user.getName())
              .userPhoneNumber(user.getPhoneNumber())
              .userAddress(user.getAddress())
              .build();

      if (user.getGroup() != null) {
        UserGroupSearch.Response userGroup = UserGroupSearch.Response.from(user.getGroup());
        response.setUserGroupSearchResponse(userGroup);
      }

      return response;
    }
  }
}
