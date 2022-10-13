package com.heybys.optimusamicus.order.repository;

import com.heybys.optimusamicus.order.entity.OrderCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCopyRepository extends JpaRepository<OrderCopy, Long> {}
