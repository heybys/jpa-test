package com.heybys.optimusamicus.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public void saveAllTest() {
  }
}
