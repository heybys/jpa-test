package com.heybys.optimusamicus.member.service.model;

import com.heybys.optimusamicus.member.domain.Member;
import java.io.Serializable;
import javax.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SessionUserInfo implements Serializable {

  private final String username;

  private final String address;

  private final String phoneNumber;

  private final String email;

  @Builder
  public SessionUserInfo(String username, String address, String phoneNumber, String email) {
    this.username = username;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public static SessionUserInfo from(Member member) {
    return SessionUserInfo.builder().username(member.getUsername()).address(member.getAddress())
        .phoneNumber(member.getPhoneNumber()).email(member.getEmail()).build();
  }

  public void addTo(HttpSession session) {
    session.setAttribute(AuthConst.USER_INFO, this);
  }
}
