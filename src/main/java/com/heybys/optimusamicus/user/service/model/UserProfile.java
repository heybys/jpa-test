package com.heybys.optimusamicus.user.service.model;

import com.heybys.optimusamicus.user.domain.entity.User;
import java.io.Serializable;
import javax.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile implements Serializable {

  private final String username;

  private final String address;

  private final String phoneNumber;

  private final String email;

  @Builder
  public UserProfile(String username, String address, String phoneNumber, String email) {
    this.username = username;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public static UserProfile from(User user) {
    return UserProfile.builder().username(user.getUsername()).address(user.getAddress())
        .phoneNumber(user.getPhoneNumber()).email(user.getEmail()).build();
  }

  public void addTo(HttpSession session) {
    session.setAttribute("userProfile", this);
  }
}
