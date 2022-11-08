package com.heybys.optimusamicus.user.domain.entity;

import com.heybys.optimusamicus.common.entity.BaseEntity;

// @Slf4j
// @ToString
// @Getter
// @Entity
// @Table(
//     name = "user",
//     uniqueConstraints = {
//         @UniqueConstraint(
//             name = "UK_username",
//             columnNames = {"username"})
//     })
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sample extends BaseEntity {

  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @Column(name = "user_id")
  // private Long id;
  //
  // @NotNull
  // @Enumerated(EnumType.STRING)
  // @Column(name = "user_type")
  // private Type type;
  //
  // @NotNull
  // @Column(name = "username")
  // private String username;
  //
  // @NotNull
  // @Column(name = "password")
  // private String password;
  //
  // @Column(name = "phone_number")
  // private String phoneNumber;
  //
  // @Column(name = "address")
  // private String address;
  //
  // @Column(name = "email")
  // private String email;
  //
  // @Exclude
  // @ManyToOne(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
  // @JoinColumn(
  //     name = "user_group_id",
  //     referencedColumnName = "user_group_id",
  //     foreignKey = @ForeignKey(name = "FK_user_group_id"))
  // private UserGroup group;
  //
  // @Builder
  // public Sample(
  //     Type type,
  //     String username,
  //     String password,
  //     String phoneNumber,
  //     String address) {
  //   this.type = type;
  //   this.username = username;
  //   this.password = password;
  //   this.phoneNumber = phoneNumber;
  //   this.address = address;
  // }
  //
  // public void joinTo(UserGroup group) {
  //   this.group = group;
  // }
  //
  // public boolean isGroupIn() {
  //   return this.group != null;
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
  //   Sample user = (Sample) o;
  //   return getId() != null && Objects.equals(getId(), user.getId());
  // }
  //
  // @Override
  // public int hashCode() {
  //   return getClass().hashCode();
  // }
  //
  // public enum Type {
  //   NORMAL,
  //   ADMIN;
  //
  //   @JsonCreator
  //   public Type from(String value) {
  //     for (Type type : Type.values()) {
  //       if (type.name().equals(value)) {
  //         return type;
  //       }
  //     }
  //     return null;
  //   }
  // }
}
