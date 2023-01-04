package com.heybys.optimusamicus.shop.domain;

import java.util.ArrayList;
import java.util.List;
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

@ToString
@Getter
@Entity(name = "option_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "option_group_id")
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "option_group_id")
  @Exclude
  private List<Option> options = new ArrayList<>();

  public void add(Option option) {
    this.options.add(option);
  }
}
