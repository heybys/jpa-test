package com.heybys.optimusamicus.order.entity;

import com.heybys.optimusamicus.order.listener.OrderListener;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@ToString
@Entity
@Table(
    name = "orders",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "UK_serial_number",
          columnNames = {"serial_number"})
    })
@EntityListeners(OrderListener.class)
@NoArgsConstructor
public class Order implements Persistable<Long> {

  @Override
  public boolean isNew() {
    return true;
  }

  @Id
  @Column(name = "order_id")
  private Long id;

  @Type(type = "org.hibernate.type.UUIDCharType")
  @Column(name = "serial_number")
  private UUID serialNumber;

  @Column private String name;

  @Builder
  public Order(Long id, String name) {
    this.id = id;
    this.serialNumber = UUID.randomUUID();
    this.name = name;
  }

  public void refreshSerialNumber() {
    serialNumber = UUID.randomUUID();
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
    return getId() != null && Objects.equals(getId(), order.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
