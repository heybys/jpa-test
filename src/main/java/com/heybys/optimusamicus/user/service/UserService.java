package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.dto.UserCreate;
import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final UserRepository userRepository;

  public Page<User> searchUsers(UserSearch.Request request, Pageable pageable) {
    return userRepository.searchUsers(request, pageable);
  }

  public UserCreate.Response getUserById(Long userId) {
    return userRepository.findById(userId).map(UserCreate.Response::new)
        .orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  public void createUser(UserCreate.Request request) {
    List<User> users = cloneUsers(request, 10000);
    long start = System.currentTimeMillis();

    userRepository.saveAll(users);

    long end = System.currentTimeMillis();

    double workingTime = end - start;
    logger.info("WorkingTime=[{}s]", workingTime / 1000);
  }

  public void createUserTest(UserCreate.Request request) {
    List<User> users = cloneUsers(request, 1000);
    long start = System.currentTimeMillis();

    userRepository.saveAllTest();

    long end = System.currentTimeMillis();

    double workingTime = end - start;
    logger.info("WorkingTime=[{}s]", workingTime / 1000);
  }

  private List<User> cloneUsers(UserCreate.Request request, Integer count) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      User user = request.toUser();
      user.setUsername(user.getUsername() + "_" + i);
      users.add(user);
    }
    return users;
  }
}
