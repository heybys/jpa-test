package com.heybys.optimusamicus.user.domain.repository.support;

import com.heybys.optimusamicus.user.domain.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UserQuerydslRepositorySupport extends QuerydslRepositorySupport {

  protected JPAQueryFactory queryFactory;

  /**
   * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
   */
  public UserQuerydslRepositorySupport() {
    super(User.class);
  }

  @Override
  @PersistenceContext
  // @PersistenceContext(unitName = "userEntityManager")
  public void setEntityManager(EntityManager entityManager) {
    super.setEntityManager(entityManager);
    queryFactory = new JPAQueryFactory(entityManager);
  }
}
