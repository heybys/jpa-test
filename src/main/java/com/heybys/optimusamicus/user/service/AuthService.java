package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.SessionUserInfo;

public interface AuthService {

  SessionUserInfo login(Credentials credentials);

  void logout();
}
