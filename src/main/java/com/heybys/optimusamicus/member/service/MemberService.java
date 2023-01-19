package com.heybys.optimusamicus.member.service;

import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public Member register(Member member) {
    member.encodePassword();
    return memberRepository.save(member);
  }
}
