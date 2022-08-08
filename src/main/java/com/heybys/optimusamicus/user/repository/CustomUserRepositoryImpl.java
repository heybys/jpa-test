package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

  @Override
  public Page<User> findAllByUsername(String username, Pageable pageable) {

    return null;
  }

  @Override
  public void saveAllTest() {
  }
}
