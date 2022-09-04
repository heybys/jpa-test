package com.heybys.optimusamicus.user.entity;

import com.heybys.optimusamicus.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroup extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long userGroupId;

  @Column(nullable = false)
  private String userGroupName;

  @Builder
  public UserGroup(Long userGroupId, String userGroupName) {
    this.userGroupId = userGroupId;
    this.userGroupName = userGroupName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    UserGroup userGroup = (UserGroup) o;
    return userGroupId != null && Objects.equals(userGroupId, userGroup.userGroupId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
