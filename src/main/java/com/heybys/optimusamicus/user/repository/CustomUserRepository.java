package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

  Page<User> findAllByUsername(String username, Pageable pageable);

  void saveAllTest();
}
