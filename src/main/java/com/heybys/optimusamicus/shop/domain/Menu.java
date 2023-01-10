package com.heybys.optimusamicus.shop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;

@ToString
@Getter
@Entity(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "menu_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Long price;

  @Column(name = "shop_id")
  private Long shopId;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "menu_id")
  @Exclude
  private List<OptionGroup> optionGroups = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Menu menu = (Menu) o;
    return id != null && Objects.equals(id, menu.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
