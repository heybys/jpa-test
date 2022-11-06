package com.heybys.optimusamicus.user.controller.dto.search;

import lombok.Data;

public class UserGroupSearch {

  @Data
  public static class Request {

    private String userGroupName;
  }
}
