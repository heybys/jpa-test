package com.heybys.optimusamicus.user.controller.dto.create;

import com.heybys.optimusamicus.user.domain.entity.UserGroup;
import javax.validation.constraints.NotNull;
import lombok.Data;

public class UserGroupCreate {

  @Data
  public static class Request {

    @NotNull private String name;

    public UserGroup toUserGroup() {
      return UserGroup.builder().name(name).build();
    }
  }
}
