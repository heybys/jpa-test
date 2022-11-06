package com.heybys.optimusamicus.order.domain.entity;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
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
    name = "orders_copy",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UK_serial_number",
            columnNames = {"serial_number"})
    })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCopy implements Persistable<Long> {

  @Override
  public boolean isNew() {
    return true;
  }

  @Id
  @Column(name = "orders_copy_id")
  private Long id;

  @NotNull
  @Type(type = "org.hibernate.type.UUIDCharType")
  @Column(name = "serial_number")
  private UUID serialNumber = UUID.randomUUID();

  @Builder
  public OrderCopy(Order order) {
    this.id = order.getId();
    this.serialNumber = order.getSerialNumber();
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
    OrderCopy order = (OrderCopy) o;
    return getId() != null && Objects.equals(getId(), order.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void updateBy(Order order) {
    this.serialNumber = order.getSerialNumber();
  }
}
