package com.heybys.optimusamicus.member.service;

import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

  private final MemberRepository memberRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByUsername(username).orElseThrow();

    return User.builder().username(member.getUsername()).password(member.getPassword())
        .roles(member.getRole()).build();
  }

  public Member register(Member member) {
    member.encodePassword(passwordEncoder);
    return memberRepository.save(member);
  }
}
