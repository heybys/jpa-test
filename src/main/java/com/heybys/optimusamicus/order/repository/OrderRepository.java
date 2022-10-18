package com.heybys.optimusamicus.order.repository;

import com.heybys.optimusamicus.order.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByName(String name);
}
