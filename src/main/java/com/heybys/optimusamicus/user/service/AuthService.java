package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.service.model.UserLoginInfo;
import com.heybys.optimusamicus.user.service.model.UserRegisterInfo;

public interface AuthService {

  void login(UserLoginInfo userLoginInfo);

  void register(UserRegisterInfo userRegisterInfo);
}
