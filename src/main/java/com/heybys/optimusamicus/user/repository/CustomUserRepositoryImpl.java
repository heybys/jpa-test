package com.heybys.optimusamicus.user.repository;

import static com.heybys.optimusamicus.user.entity.QUser.user;
import static com.heybys.optimusamicus.user.entity.QUserGroup.userGroup;

import com.heybys.optimusamicus.user.dto.search.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.Type;
import com.heybys.optimusamicus.user.repository.support.UserQuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl extends UserQuerydslRepositorySupport
    implements CustomUserRepository {

  private final JdbcTemplate userJdbcTemplate;

  @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
  private Integer jdbcBatchSize;

  @Override
  public Page<User> retrieveUsers(UserSearch.Request userSearchRequest, Pageable pageable) {

    List<User> content =
        queryFactory
            .selectFrom(user)
            .leftJoin(user.group, userGroup)
            .fetchJoin()
            .where(
                userTypeEq(userSearchRequest.getUserType()),
                usernameEq(userSearchRequest.getUsername()),
                userGroupNameEq(userSearchRequest.getUserGroupName()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    JPAQuery<Long> countQuery =
        queryFactory
            .select(user.count())
            .from(user)
            .leftJoin(userGroup)
            .on(user.group.id.eq(userGroup.id))
            .where(
                userTypeEq(userSearchRequest.getUserType()),
                usernameEq(userSearchRequest.getUsername()),
                userGroupNameEq(userSearchRequest.getUserGroupName()));

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
  }

  @Override
  public Long updateUser(Long userId, Map<String, Object> params) {
    EntityManager entityManager = getEntityManager();
    assert entityManager != null;

    // entityManager.flush();

    JPAUpdateClause updateClause = queryFactory.update(user);

    if (params.containsKey("username") && params.get("username") != null) {
      String username = params.get("username").toString();
      updateClause.set(user.name, username);
    }

    if (params.containsKey("phoneNumber") && params.get("phoneNumber") != null) {
      String phoneNumber = params.get("phoneNumber").toString();
      updateClause.set(user.phoneNumber, phoneNumber);
    }

    long count = updateClause.where(user.id.eq(userId)).execute();

    // entityManager.clear();

    return count;
  }

  @Override
  public List<User> batchInsert(List<User> users) {
    this.userJdbcTemplate.batchUpdate(
        "  INSERT INTO user (`type`, `name`, `phone_number`, `address`, `user_group_id`) "
            + " VALUES (?, ?, ?, ?, ?) ",
        users,
        jdbcBatchSize,
        (ps, argument) -> {
          ps.setString(1, argument.getType().name());
          ps.setString(2, argument.getName());
          ps.setString(3, argument.getPhoneNumber());
          ps.setString(4, argument.getAddress());
          ps.setObject(5, userGroupIdFrom(argument));
        });

    return users;
  }

  private BooleanExpression userTypeEq(Type type) {
    return type != null ? user.type.eq(type) : null;
  }

  private BooleanExpression usernameEq(String username) {
    return username != null ? user.name.eq(username) : null;
  }

  private BooleanExpression userGroupNameEq(String userGroupName) {
    return userGroupName != null ? userGroup.name.eq(userGroupName) : null;
  }

  private Long userGroupIdFrom(User user) {
    return user.getGroup() != null ? user.getGroup().getId() : null;
  }
}
