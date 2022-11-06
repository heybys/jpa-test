package com.heybys.optimusamicus.order.domain.repository.support;

import com.heybys.optimusamicus.order.domain.entity.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OrderQuerydslRepositorySupport extends QuerydslRepositorySupport {

  protected JPAQueryFactory queryFactory;

  /**
   * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
   */
  public OrderQuerydslRepositorySupport() {
    super(Order.class);
  }

  @Override
  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    super.setEntityManager(entityManager);
    queryFactory = new JPAQueryFactory(entityManager);
  }
}
