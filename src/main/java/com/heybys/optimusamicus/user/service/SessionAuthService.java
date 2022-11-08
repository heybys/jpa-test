package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.utils.HttpServletRequestProvider;
import com.heybys.optimusamicus.user.domain.entity.User;
import com.heybys.optimusamicus.user.domain.repository.UserRepository;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionAuthService implements AuthService {

  private final UserRepository userRepository;

  @Override
  public void login(AuthInfo authInfo) {
    User user =
        userRepository
            .findByUsernameAndPassword(authInfo.getUsername(), authInfo.getPassword())
            .orElseThrow(IllegalArgumentException::new);

    HttpSession session = HttpServletRequestProvider.getSession();
    session.setAttribute("sessionUser", user);
  }

  @Override
  public void register(User user) {
    userRepository.save(user);
  }
}
