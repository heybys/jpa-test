package com.heybys.optimusamicus.member.service;

import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;

  private final PasswordEncoder passwordEncoder;

  public Member register(Member member) {
    member.encodePassword(passwordEncoder);
    return memberRepository.save(member);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(""));

    return User.builder().username(member.getUsername())
        .password(member.getPassword())
        .roles(member.getRole())
        .build();
  }

  public void dashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.debug("authentication : {}", authentication);
  }
}
