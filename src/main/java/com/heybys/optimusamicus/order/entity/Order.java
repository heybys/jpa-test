package com.heybys.optimusamicus.order.entity;

import com.heybys.optimusamicus.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id", nullable = false)
  private Long orderId;

  @Column(name = "serial_number", nullable = false)
  private String serialNumber;

  @Builder
  public Order(Long orderId, String serialNumber) {
    this.orderId = orderId;
    this.serialNumber = serialNumber;
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
    return orderId != null && Objects.equals(orderId, order.orderId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
