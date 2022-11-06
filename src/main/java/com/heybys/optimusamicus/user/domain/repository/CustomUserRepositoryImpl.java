package com.heybys.optimusamicus.user.domain.repository;

import static com.heybys.optimusamicus.user.entity.QUser.user;
import static com.heybys.optimusamicus.user.entity.QUserGroup.userGroup;

import com.heybys.optimusamicus.user.controller.dto.search.UserSearch;
import com.heybys.optimusamicus.user.domain.entity.User;
import com.heybys.optimusamicus.user.domain.entity.User.Type;
import com.heybys.optimusamicus.user.domain.repository.support.UserQuerydslRepositorySupport;
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

  private final JdbcTemplate jdbcTemplate;

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
  public void patchUser(Long userId, Map<String, Object> params) {
    EntityManager entityManager = getEntityManager();
    assert entityManager != null;

    entityManager.flush();

    JPAUpdateClause updateClause = queryFactory.update(user);

    if (params.containsKey("type") && params.get("type") != null) {
      User.Type type = User.Type.valueOf(params.get("type").toString());
      updateClause.set(user.type, type);
    }

    if (params.containsKey("username") && params.get("username") != null) {
      String username = params.get("username").toString();
      updateClause.set(user.username, username);
    }

    if (params.containsKey("phoneNumber") && params.get("phoneNumber") != null) {
      String phoneNumber = params.get("phoneNumber").toString();
      updateClause.set(user.phoneNumber, phoneNumber);
    }

    if (params.containsKey("address") && params.get("address") != null) {
      String address = params.get("address").toString();
      updateClause.set(user.address, address);
    }

    if (params.containsKey("selfIntroduction")) {
      if (params.get("selfIntroduction") != null) {
        String selfIntroduction = params.get("selfIntroduction").toString();
        updateClause.set(user.selfIntroduction, selfIntroduction);
      } else {
        updateClause.setNull(user.selfIntroduction);
      }
    }

    long count = updateClause.where(user.id.eq(userId)).execute();

    if (count != 1L) {
      throw new IllegalArgumentException();
    }

    entityManager.clear();
  }

  @Override
  public List<User> batchInsert(List<User> users) {
    this.jdbcTemplate.batchUpdate(
        "  INSERT INTO user (`type`, `name`, `phone_number`, `address`,`self_introduction`, `user_group_id`) "
            + " VALUES (?, ?, ?, ?, ?) ",
        users,
        jdbcBatchSize,
        (ps, argument) -> {
          ps.setString(1, argument.getType().name());
          ps.setString(2, argument.getUsername());
          ps.setString(3, argument.getPhoneNumber());
          ps.setString(4, argument.getAddress());
          ps.setString(5, argument.getSelfIntroduction());
          ps.setObject(6, userGroupIdFrom(argument));
        });

    return users;
  }

  private BooleanExpression userTypeEq(Type type) {
    return type != null ? user.type.eq(type) : null;
  }

  private BooleanExpression usernameEq(String username) {
    return username != null ? user.username.eq(username) : null;
  }

  private BooleanExpression userGroupNameEq(String userGroupName) {
    return userGroupName != null ? userGroup.name.eq(userGroupName) : null;
  }

  private Long userGroupIdFrom(User user) {
    return user.getGroup() != null ? user.getGroup().getId() : null;
  }
}
