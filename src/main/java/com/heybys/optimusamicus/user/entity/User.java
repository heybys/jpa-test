package com.heybys.optimusamicus.user.entity;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(name = "UK_phone_number", columnNames = {"phone_number"}),
    @UniqueConstraint(name = "UK_username", columnNames = {"username"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class User {

  public enum UserType {
    NORMAL, ADMIN
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Type(type = "")
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false, columnDefinition = "char(10)")
  private UserType userType;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "phone_number", columnDefinition = "char(11)")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Type(type = "yes_no")
  @Column(name = "use_yn", nullable = false)
  private Boolean useYn = true;

  @ManyToOne(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_group_id")
  private UserGroup userGroup;

  @Builder
  public User(Long userId, String username, UserType userType) {
    this.userId = userId;
    this.username = username;
    this.userType = userType;
  }
}
