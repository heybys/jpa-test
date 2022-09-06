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

    @NotNull private String userGroupName;

    public UserGroup toUserGroup() {
      return UserGroup.builder().name(userGroupName).build();
    }
  }

  @Data
  @JsonInclude(Include.NON_NULL)
  public static class Response {

    private Long userGroupId;
    private String userGroupName;

    @Builder
    public Response(Long userGroupId, String userGroupName) {
      this.userGroupId = userGroupId;
      this.userGroupName = userGroupName;
    }

    public static Response from(UserGroup userGroup) {
      return Response.builder()
          .userGroupId(userGroup.getId())
          .userGroupName(userGroup.getName())
          .build();
    }
  }
}
