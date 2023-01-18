package com.heybys.optimusamicus.member.service;

import com.heybys.optimusamicus.member.service.model.Credentials;
import com.heybys.optimusamicus.member.service.model.SessionUserInfo;

public interface AuthService {

  SessionUserInfo login(Credentials credentials);

  void logout();
}
