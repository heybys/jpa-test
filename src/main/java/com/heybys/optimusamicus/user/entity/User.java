package com.heybys.optimusamicus.user.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Setter
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Setter
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private UserType userType;

  @Setter
  @Column(name = "use_yn", nullable = false)
  @Type(type = "yes_no")
  private Boolean useYn = false;

  @Builder
  public User(Long userId, String username, UserType userType) {
    this.userId = userId;
    this.username = username;
    this.userType = userType;
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

  public enum UserType {
    NORMAL, ADMIN
  }
}
