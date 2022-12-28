package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.RegisterUserInfo;
import com.heybys.optimusamicus.user.service.model.SessionUserInfo;

public interface AuthService {

  void register(Credentials credentials, RegisterUserInfo registerUserInfo);

  SessionUserInfo login(Credentials credentials);

  void logout();
}
