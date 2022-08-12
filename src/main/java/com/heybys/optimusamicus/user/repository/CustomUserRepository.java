package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

  Page<User> searchUsers(UserSearch.Request request, Pageable pageable);

  void saveAllTest();
}
