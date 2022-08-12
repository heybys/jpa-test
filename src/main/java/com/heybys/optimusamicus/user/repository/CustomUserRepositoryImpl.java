package com.heybys.optimusamicus.user.repository;

import static com.heybys.optimusamicus.user.entity.QUser.user;

import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import com.heybys.optimusamicus.user.repository.support.UserQuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl extends UserQuerydslRepositorySupport implements
    CustomUserRepository {

  @Override
  public Page<User> searchUsers(UserSearch.Request request, Pageable pageable) {

    List<User> users = queryFactory
        .select(user)
        .from(user)
        .where(
            usernameEq(request.getUsername()),
            userTypeEq(request.getUserType()),
            useYnEq(request.getUseYn())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<User> countQuery = queryFactory
        .select(user)
        .from(user)
        .where(
            usernameEq(request.getUsername()),
            userTypeEq(request.getUserType()),
            useYnEq(request.getUseYn())
        );

    return PageableExecutionUtils.getPage(users, pageable, countQuery::fetchCount);
  }

  @Override
  public void saveAllTest() {
  }

  private BooleanExpression usernameEq(String username) {
    return username != null ? user.username.eq(username) : null;
  }

  private BooleanExpression userTypeEq(UserType userType) {
    return userType != null ? user.userType.eq(userType) : null;
  }

  private BooleanExpression useYnEq(Boolean useYn) {
    return useYn != null ? user.useYn.eq(useYn) : null;
  }
}
