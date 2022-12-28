package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.utils.HttpServletRequestProvider;
import com.heybys.optimusamicus.user.domain.entity.User;
import com.heybys.optimusamicus.user.domain.repository.UserRepository;
import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.UserProfile;
import com.heybys.optimusamicus.user.service.model.UserRegisterInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionAuthService implements AuthService {

  private final UserRepository userRepository;

  @Override
  public void register(Credentials credentials, UserRegisterInfo userRegisterInfo) {
    User user =
        User.builder()
            .username(credentials.getUsername())
            .password(credentials.getPassword())
            .phoneNumber(userRegisterInfo.getPhoneNumber())
            .address(userRegisterInfo.getAddress())
            .email(userRegisterInfo.getEmail())
            .build();

    userRepository.save(user);
  }

  @Override
  public UserProfile login(Credentials credentials) {
    User user =
        userRepository
            .findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "There is no user with the username and password."));
    UserProfile userProfile = UserProfile.from(user);
    userProfile.addTo(HttpServletRequestProvider.getSession());

    return userProfile;
  }

  @Override
  public void logout() {
  }
}
