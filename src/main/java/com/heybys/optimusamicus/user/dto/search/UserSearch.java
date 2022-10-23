package com.heybys.optimusamicus.user.dto.search;

import com.heybys.optimusamicus.user.entity.User;
import lombok.Data;

public class UserSearch {

  @Data
  public static class Request {

    private String username;
    private User.Type userType;
    private String userGroupName;
  }
}
