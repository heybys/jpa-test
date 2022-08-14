package com.heybys.optimusamicus.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_group_id", nullable = false)
  private Long userGroupId;

  @Column(name = "name", nullable = false)
  private String userGroupName;


}
