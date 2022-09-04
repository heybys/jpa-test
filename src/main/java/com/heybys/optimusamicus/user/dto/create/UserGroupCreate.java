package com.heybys.optimusamicus.user.dto.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.UserGroup;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class UserGroupCreate {

  @Data
  public static class Request {

    @NotNull private String name;

    public UserGroup toUserGroup() {
      return UserGroup.builder().userGroupName(name).build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long id;
    private String name;

    @Builder
    public Response(Long id, String name) {
      this.id = id;
      this.name = name;
    }

    public static Response from(UserGroup userGroup) {
      return Response.builder()
          .id(userGroup.getUserGroupId())
          .name(userGroup.getUserGroupName())
          .build();
    }
  }
}
