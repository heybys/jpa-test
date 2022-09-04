package com.heybys.optimusamicus.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.heybys.optimusamicus.common.entity.BaseEntity;
import java.util.Objects;
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
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UK_phoneNumber",
            columnNames = {"phoneNumber"}),
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
  private Long userId;

  @NotNull
  @Length(max = 10)
  @Enumerated(EnumType.STRING)
  private UserType userType;

  @NotNull
  private String username;

  @Length(max = 20)
  private String phoneNumber;

  private String address;

  @ManyToOne(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "userGroupId")
  @Exclude
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
