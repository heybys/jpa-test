package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.domain.entity.User;

public interface AuthService {

  void login(AuthInfo authInfo);

  void register(User user);
}
