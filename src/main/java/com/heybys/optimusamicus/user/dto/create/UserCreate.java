package com.heybys.optimusamicus.user.dto.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.Type;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

public class UserCreate {

  @Data
  public static class Request {

    @NotNull(message = "Need to username")
    private String username;

    @NotNull(message = "Need to userType")
    private User.Type userType;

    @NotNull(message = "Need to userPhoneNumber")
    @Pattern(regexp = "01(?:0|1|[6-9])(\\d{3}|\\d{4})(\\d{4})", message = "Invalid format")
    private String userPhoneNumber;

    @NotNull(message = "Need to userAddress")
    private String userAddress;

    private Long userGroupId;

    public User toUser() {
      return User.builder()
          .name(username)
          .type(userType)
          .phoneNumber(userPhoneNumber)
          .address(userAddress)
          .build();
    }
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
    private UserGroupCreate.Response userGroupCreateResponse;

    @Builder
    public Response(
        Long userId, Type userType, String username, String userPhoneNumber, String userAddress) {
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
        UserGroupCreate.Response userGroup = UserGroupCreate.Response.from(user.getGroup());
        response.setUserGroupCreateResponse(userGroup);
      }

      return response;
    }
  }
}
