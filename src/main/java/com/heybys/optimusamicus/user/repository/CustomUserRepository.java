package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.entity.User;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

  Page<User> searchUsers(Map<String, String> conditions, Pageable pageable);

  void saveAllTest();
}
