package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.UserProfile;
import com.heybys.optimusamicus.user.service.model.UserRegisterInfo;

public interface AuthService {

  UserProfile login(Credentials credentials);

  void register(Credentials credentials, UserRegisterInfo userRegisterInfo);
}
