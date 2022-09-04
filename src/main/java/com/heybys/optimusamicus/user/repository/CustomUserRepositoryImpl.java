package com.heybys.optimusamicus.user.repository;

import static com.heybys.optimusamicus.user.entity.QUser.user;
import static com.heybys.optimusamicus.user.entity.QUserGroup.userGroup;

import com.heybys.optimusamicus.user.dto.search.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
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
            .on(user.userGroup.userGroupId.eq(userGroup.userGroupId))
            .fetchJoin()
            .where(
                userTypeEq(userSearchRequest.getUserType()),
                usernameEq(userSearchRequest.getName()),
                userGroupNameEq(userSearchRequest.getGroupName()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    JPAQuery<User> countQuery =
        queryFactory
            .selectFrom(user)
            .leftJoin(userGroup)
            .on(user.userGroup.userGroupId.eq(userGroup.userGroupId))
            .where(
                userTypeEq(userSearchRequest.getUserType()),
                usernameEq(userSearchRequest.getName()),
                userGroupNameEq(userSearchRequest.getGroupName()));

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
          ps.setString(1, argument.getUserType().name());
          ps.setString(2, argument.getUsername());
          ps.setString(3, argument.getPhoneNumber());
          ps.setString(4, argument.getAddress());
          ps.setObject(5, userGroupIdFrom(argument));
        });

    return users;
  }

  private BooleanExpression userTypeEq(UserType userType) {
    return userType != null ? user.userType.eq(userType) : null;
  }

  private BooleanExpression usernameEq(String username) {
    return username != null ? user.username.eq(username) : null;
  }

  private BooleanExpression userGroupNameEq(String userGroupName) {
    return userGroupName != null ? userGroup.userGroupName.eq(userGroupName) : null;
  }

  private Long userGroupIdFrom(User user) {
    return user.getUserGroup() != null ? user.getUserGroup().getUserGroupId() : null;
  }
}
