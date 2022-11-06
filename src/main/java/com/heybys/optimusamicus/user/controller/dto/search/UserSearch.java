package com.heybys.optimusamicus.user.controller.dto.search;

import com.heybys.optimusamicus.user.domain.entity.User;
import lombok.Data;

public class UserSearch {

  @Data
  public static class Request {

    private String username;
    private User.Type userType;
    private String userGroupName;
  }
}
