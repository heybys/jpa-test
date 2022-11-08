package com.heybys.optimusamicus.user.domain.entity;

import com.heybys.optimusamicus.common.entity.BaseEntity;

// @Getter
// @ToString
// @Entity
// @Table(name = "user_group")
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroup extends BaseEntity {

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @Column(name = "user_group_id")
  // private Long id;
  //
  // @NotNull
  // @Column(name = "user_group_name")
  // private String name;
  //
  // @Builder
  // public UserGroup(Long id, String name) {
  //   this.id = id;
  //   this.name = name;
  // }
  //
  // @Override
  // public boolean equals(Object o) {
  //   if (this == o) {
  //     return true;
  //   }
  //   if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
  //     return false;
  //   }
  //   UserGroup userGroup = (UserGroup) o;
  //   return getId() != null && Objects.equals(getId(), userGroup.getId());
  // }
  //
  // @Override
  // public int hashCode() {
  //   return getClass().hashCode();
  // }
}
