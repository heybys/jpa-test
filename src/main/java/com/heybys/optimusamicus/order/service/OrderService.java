package com.heybys.optimusamicus.order.service;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.order.controller.dto.Coffee;
import com.heybys.optimusamicus.order.controller.dto.Customer;
import com.heybys.optimusamicus.order.domain.entity.Order;
import com.heybys.optimusamicus.order.domain.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@LogExecutionTime
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  private final Customer customer;

  public void order(String menuName) {
    Coffee coffee = customer.order(menuName);

    log.info("Ordered coffee. {}", coffee);
  }

  public List<Order> retrieveOrders(String name) {
    return orderRepository.findByName(name);
  }

  @Transactional
  public Order retrieveOrder(Long orderId) {
    Order order = orderRepository.findById(orderId).orElseThrow();
    order.refreshSerialNumber();

    // try {
    //   Session session = entityManager.unwrap(Session.class);
    //   SessionFactory sessionFactory = session.getSessionFactory();
    //   JdbcConnectionAccess access = ((SessionImplementor)
    // sessionFactory).getJdbcConnectionAccess();
    //   Connection connection = access.obtainConnection();
    //   int isolation = connection.getTransactionIsolation();
    //   int transactionReadUncommitted = Connection.TRANSACTION_READ_UNCOMMITTED;
    // } catch (SQLException e) {
    //   throw new RuntimeException(e);
    // }

    return order;
  }

  @Transactional
  public Order createOrder(Order order) {
    return orderRepository.insert(order);
  }
}
