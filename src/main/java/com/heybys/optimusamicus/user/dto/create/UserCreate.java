package com.heybys.optimusamicus.user.dto.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heybys.optimusamicus.user.dto.create.UserGroupCreate.Response;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.Type;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class UserCreate {

  @Data
  public static class Request {
    @NotNull private String name;
    @NotNull private User.Type type;
    // @Length(min = 10, max = 11)
    // @Pattern(regexp = "^[0-9]+$")
    @NotNull private String phoneNumber;
    @NotNull private String address;
    private Long userGroupId;

    public User toUser() {
      return User.builder().name(name).type(type).phoneNumber(phoneNumber).address(address).build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {
    private Long id;
    private Type type;
    private String name;
    private String phoneNumber;
    private String address;

    @JsonProperty(value = "userGroup")
    private UserGroupCreate.Response userGroupCreateResponse;

    @Builder
    public Response(Long id, Type type, String name, String phoneNumber, String address) {
      this.id = id;
      this.type = type;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.address = address;
    }

    public static Response from(User user) {
      Response response =
          Response.builder()
              .id(user.getId())
              .type(user.getType())
              .name(user.getName())
              .phoneNumber(user.getPhoneNumber())
              .address(user.getAddress())
              .build();

      if (user.getUserGroup() != null) {
        UserGroupCreate.Response userGroup = UserGroupCreate.Response.from(user.getUserGroup());
        response.setUserGroupCreateResponse(userGroup);
      }

      return response;
    }
  }
}
