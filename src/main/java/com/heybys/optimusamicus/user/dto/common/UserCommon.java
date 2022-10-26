package com.heybys.optimusamicus.user.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.Type;
import lombok.Builder;
import lombok.Data;

public class UserCommon {

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long id;
    private Type type;
    private String username;
    private String phoneNumber;
    private String address;
    private String selfIntroduction;
    private UserGroupCommon.Response userGroup;

    @Builder
    public Response(
        Long id,
        Type type,
        String username,
        String phoneNumber,
        String address,
        String selfIntroduction) {
      this.id = id;
      this.type = type;
      this.username = username;
      this.phoneNumber = phoneNumber;
      this.address = address;
      this.selfIntroduction = selfIntroduction;
    }

    public static Response from(User user) {
      Response response =
          Response.builder()
              .id(user.getId())
              .type(user.getType())
              .username(user.getUsername())
              .phoneNumber(user.getPhoneNumber())
              .address(user.getAddress())
              .selfIntroduction(user.getSelfIntroduction())
              .build();

      if (user.isGroupIn()) {
        UserGroupCommon.Response userGroup = UserGroupCommon.Response.from(user.getGroup());
        response.setUserGroup(userGroup);
      }

      return response;
    }
  }
}
