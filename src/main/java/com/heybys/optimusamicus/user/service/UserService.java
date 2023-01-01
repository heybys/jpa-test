package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.domain.entity.User;
import com.heybys.optimusamicus.user.domain.repository.UserRepository;
import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.RegisterUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public void register(Credentials credentials, RegisterUserInfo registerUserInfo) {
    User user =
        User.builder()
            .username(credentials.getUsername())
            .password(credentials.getPassword())
            .phoneNumber(registerUserInfo.getPhoneNumber())
            .address(registerUserInfo.getAddress())
            .email(registerUserInfo.getEmail())
            .build();

    userRepository.save(user);
  }
}
