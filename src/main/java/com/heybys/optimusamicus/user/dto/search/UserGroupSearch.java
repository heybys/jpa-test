package com.heybys.optimusamicus.user.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.UserGroup;
import lombok.Builder;
import lombok.Data;

public class UserGroupSearch {

  @Data
  public static class Request {
    private String name;
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
      return Response.builder().id(userGroup.getId()).name(userGroup.getName()).build();
    }
  }
}
