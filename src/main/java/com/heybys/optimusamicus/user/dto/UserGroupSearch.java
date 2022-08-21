package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.UserGroup;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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
    public Response(UserGroup userGroup) {
      BeanUtils.copyProperties(userGroup, this);
    }
  }
}
