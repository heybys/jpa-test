package com.heybys.optimusamicus.user.repository;

import static com.heybys.optimusamicus.user.entity.QUser.user;
import static com.heybys.optimusamicus.user.entity.QUserGroup.userGroup;

import com.heybys.optimusamicus.user.dto.search.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.Type;
import com.heybys.optimusamicus.user.repository.support.UserQuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
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

    List<User> users =
        queryFactory
            .selectFrom(user)
            .leftJoin(userGroup)
            .on(user.group.id.eq(userGroup.id))
            .fetchJoin()
            .where(
                userTypeEq(userSearchRequest.getUserType()),
                usernameEq(userSearchRequest.getUsername()),
                userGroupNameEq(userSearchRequest.getUserGroupName()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    JPAQuery<User> countQuery =
        queryFactory
            .selectFrom(user)
            .leftJoin(userGroup)
            .on(user.group.id.eq(userGroup.id))
            .where(
                userTypeEq(userSearchRequest.getUserType()),
                usernameEq(userSearchRequest.getUsername()),
                userGroupNameEq(userSearchRequest.getUserGroupName()));

    return PageableExecutionUtils.getPage(users, pageable, countQuery::fetchCount);
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
