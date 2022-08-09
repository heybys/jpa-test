package com.heybys.optimusamicus.user.repository;

import static com.heybys.optimusamicus.user.entity.QUser.user;

import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<User> findAllByConditions(Map<String, String> conditions, Pageable pageable) {

    List<User> users = queryFactory
        .select(user)
        .from(user)
        .where(
            usernameEq(conditions.get("username"))
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    PageableExecutionUtils.getPage()

    return res;
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
