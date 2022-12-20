package com.heybys.optimusamicus.user.service.model;

import com.heybys.optimusamicus.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile {

  private String username;

  private String address;

  private String phoneNumber;

  private String email;

  @Builder
  private UserProfile(String username, String address, String phoneNumber, String email) {
    this.username = username;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public static UserProfile from(User user) {
    return UserProfile.builder().username(user.getUsername()).address(user.getAddress())
        .phoneNumber(user.getPhoneNumber()).email(user.getEmail()).build();
  }
}
