package com.heybys.optimusamicus.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.heybys.optimusamicus.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Entity
@Table(
    name = "user",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "UK_phone_number",
          columnNames = {"phone_number"}),
      @UniqueConstraint(
          name = "UK_username",
          columnNames = {"username"})
    })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

  public enum UserType {
    NORMAL,
    ADMIN;

    @JsonCreator
    public UserType from(String value) {
      for (UserType userType : UserType.values()) {
        if (userType.name().equals(value)) {
          return userType;
        }
      }
      return null;
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false, columnDefinition = "char(10)")
  private UserType userType;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "phone_number", columnDefinition = "char(111)")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @ManyToOne(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_group_id")
  private UserGroup userGroup;

  @Builder
  public User(
      UserType userType, String username, String phoneNumber, String address, UserGroup userGroup) {
    this.userType = userType;
    this.username = username;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.userGroup = userGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return userId != null && Objects.equals(userId, user.userId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
