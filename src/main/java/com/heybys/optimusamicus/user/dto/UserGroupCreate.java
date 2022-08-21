package com.heybys.optimusamicus.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heybys.optimusamicus.user.entity.UserGroup;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public class UserGroupCreate {

  @Data
  public static class Request {

    @NotNull private String userGroupName;

    public UserGroup toUserGroup() {
      return UserGroup.builder().userGroupName(userGroupName).build();
    }
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
