package com.heybys.optimusamicus.user.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.UserGroup;
import lombok.Builder;
import lombok.Data;

public class UserGroupSearch {

  @Data
  public static class Request {

    private String userGroupName;
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
