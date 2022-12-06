package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.utils.HttpServletRequestProvider;
import com.heybys.optimusamicus.user.domain.entity.User;
import com.heybys.optimusamicus.user.domain.repository.UserRepository;
import com.heybys.optimusamicus.user.service.model.UserLoginInfo;
import com.heybys.optimusamicus.user.service.model.UserRegisterInfo;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionAuthService implements AuthService {

  private final UserRepository userRepository;

  @Override
  public void login(UserLoginInfo info) {
    User user =
        userRepository
            .findByUsernameAndPassword(info.getUsername(), info.getPassword())
            .orElseThrow(IllegalArgumentException::new);

    HttpSession session = HttpServletRequestProvider.getSession();
    session.setAttribute("sessionUser", user);
  }

  @Override
  public void register(UserRegisterInfo authInfo) {
    userRepository.save(authInfo.toUser());
  }
}
