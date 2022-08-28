package com.heybys.optimusamicus.order.entity;

import com.heybys.optimusamicus.common.entity.BaseEntity;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Entity
@Table(
    name = "orders",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "UK_serial_number",
          columnNames = {"serial_number"})
    })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "serial_number", nullable = false)
  private UUID serialNumber = UUID.randomUUID();

  @Builder
  public Order(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Order order = (Order) o;
    return id != null && Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
