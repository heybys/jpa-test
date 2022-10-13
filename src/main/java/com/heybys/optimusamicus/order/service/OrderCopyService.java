package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.entity.OrderCopy;
import com.heybys.optimusamicus.order.repository.OrderCopyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderCopyService {

  private final OrderCopyRepository orderCopyRepository;

  @Transactional
  public void createOrderCopyBy(Order order) {
    orderCopyRepository.save(OrderCopy.builder().order(order).build());
  }

  @Transactional
  public void updateOrderCopyBy(Order order) {
    OrderCopy orderCopy = orderCopyRepository.findById(order.getId()).orElseThrow();
    orderCopy.updateBy(order);
  }
}
