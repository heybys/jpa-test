package com.heybys.optimusamicus.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.heybys.optimusamicus.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
import lombok.ToString;
import lombok.ToString.Exclude;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

@Slf4j
@Getter
@ToString
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "user_type")
  private Type type;

  @NotNull
  @Column(name = "username")
  private String name;

  @NotNull
  @Column(name = "password")
  private String password;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "self_introduction")
  private String selfIntroduction;

  @Exclude
  @ManyToOne(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
  @JoinColumn(
      name = "user_group_id",
      referencedColumnName = "user_group_id",
      foreignKey = @ForeignKey(name = "FK_user_group_id"))
  private UserGroup group;

  @Builder
  public User(
      Type type,
      String name,
      String password,
      String phoneNumber,
      String address,
      String selfIntroduction) {
    this.type = type;
    this.name = name;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.selfIntroduction = selfIntroduction;
  }

  public void joinTo(UserGroup group) {
    this.group = group;
  }

  public boolean isGroupIn() {
    return this.group != null;
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
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public enum Type {
    NORMAL,
    ADMIN;

    @JsonCreator
    public Type from(String value) {
      for (Type type : Type.values()) {
        if (type.name().equals(value)) {
          return type;
        }
      }
      return null;
    }
  }
}
