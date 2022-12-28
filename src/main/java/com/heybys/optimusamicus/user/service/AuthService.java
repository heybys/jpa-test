package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.UserProfile;
import com.heybys.optimusamicus.user.service.model.UserRegisterInfo;

public interface AuthService {

  void register(Credentials credentials, UserRegisterInfo userRegisterInfo);

  UserProfile login(Credentials credentials);

  void logout();
}
