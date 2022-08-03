package com.heybys.optimusamicus.order.entity;

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

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    private Long orderId;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Builder
    public Order(Long orderId, String serialNumber) {
        this.orderId = orderId;
        this.serialNumber = serialNumber;
    }
}
