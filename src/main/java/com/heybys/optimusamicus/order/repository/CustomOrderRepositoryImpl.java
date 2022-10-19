package com.heybys.optimusamicus.order.repository;

import static com.heybys.optimusamicus.order.entity.QOrder.order;

import com.heybys.optimusamicus.order.entity.Order;
import com.heybys.optimusamicus.order.feign.FeignTest;
import com.heybys.optimusamicus.order.repository.support.OrderQuerydslRepositorySupport;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcAccessor;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomOrderRepositoryImpl extends OrderQuerydslRepositorySupport
    implements CustomOrderRepository {

  private final JdbcTemplate jdbcTemplate;

  private final JdbcAccessor jdbcAccessor;

  private final DataSource dataSource;

  private final FeignTest feignTest;

  @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
  private Integer jdbcBatchSize;

  @Override
  public Order insert(Order orderEntity) {
    insertQuery(orderEntity);
    Object articles = feignTest.getArticles();
    log.debug("articles : {}", articles);
    return queryFactory.selectFrom(order).where(order.id.eq(orderEntity.getId())).fetchOne();
  }

  // @Modifying(flushAutomatically = true, clearAutomatically = true)
  private void insertQuery(Order orderEntity) {
    try {
      Connection c = DataSourceUtils.getConnection(dataSource);
      boolean autoCommit = c.getAutoCommit();
      log.debug("autoCommit : {}", autoCommit);

      c.setAutoCommit(true);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    int affectedRows =
        jdbcTemplate.update(
            "INSERT INTO orders (order_id, name, serial_number) VALUES (?, ?, ?) ",
            orderEntity.getId(),
            orderEntity.getName(),
            orderEntity.getSerialNumber().toString());

    if (affectedRows != 1) {
      throw new IllegalArgumentException();
    }
  }
}

// EntityManager entityManager = getEntityManager();
// assert entityManager != null;
// int affectedRows =
//     entityManager
//         .createNativeQuery(
//             "       INSERT INTO orders "
//                 + "  (order_id, name, serial_number)"
//                 + " VALUES "
//                 + "  (:id, :name, :serialNumber)")
//         .setParameter("id", orderEntity.getId())
//         .setParameter("name", orderEntity.getName())
//         .setParameter("serialNumber", orderEntity.getSerialNumber().toString())
//         .executeUpdate();
// long affectedRows =
//     queryFactory.insert(order).columns(order.id, order.name).values(1L, "test").execute();
// .set(order.id, orderEntity.getId())
// .set(order.serialNumber, orderEntity.getSerialNumber())
// .set(order.name, orderEntity.getName())
// queryFactory
//     .insert(order)
//     .columns(order.id, order.serialNumber, order.name)
//     .values(orderEntity.getId(), orderEntity.getSerialNumber(), orderEntity.getName())
//     .execute();
