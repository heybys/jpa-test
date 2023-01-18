package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.domain.Member;
import com.heybys.optimusamicus.user.domain.UserRepository;
import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.RegisterUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = userRepository.findByUsername(username).orElseThrow();

    return null;
  }

  public void register(Credentials credentials, RegisterUserInfo registerUserInfo) {
    Member member =
        Member.builder()
            .username(credentials.getUsername())
            .password(credentials.getPassword())
            .phoneNumber(registerUserInfo.getPhoneNumber())
            .address(registerUserInfo.getAddress())
            .email(registerUserInfo.getEmail())
            .build();

    userRepository.save(member);
  }
}
