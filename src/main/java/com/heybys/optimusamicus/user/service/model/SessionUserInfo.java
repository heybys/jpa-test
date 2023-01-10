package com.heybys.optimusamicus.user.service.model;

import com.heybys.optimusamicus.user.domain.User;
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

  public static SessionUserInfo from(User user) {
    return SessionUserInfo.builder().username(user.getUsername()).address(user.getAddress())
        .phoneNumber(user.getPhoneNumber()).email(user.getEmail()).build();
  }

  public void addTo(HttpSession session) {
    session.setAttribute(AuthConst.USER_INFO, this);
  }
}
