package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

  Page<User> searchUsers(UserSearch.Request request, Pageable pageable);

  int[][] batchInsert(List<User> users);
}
