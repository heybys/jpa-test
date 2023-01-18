package com.heybys.optimusamicus.member.service;

import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.domain.MemberRepository;
import com.heybys.optimusamicus.member.service.model.Credentials;
import com.heybys.optimusamicus.member.service.model.RegisterUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByUsername(username).orElseThrow();

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

    memberRepository.save(member);
  }
}
