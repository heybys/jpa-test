package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.dto.UserDTO;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final UserRepository userRepository;

  public List<UserDTO.Response> getAllUsers() {
    return userRepository.findAll().stream().map(UserDTO.Response::new)
        .collect(Collectors.toList());
  }

  public UserDTO.Response getUserById(Long userId) {
    return userRepository.findById(userId).map(UserDTO.Response::new)
        .orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  public void createUser(UserDTO.Request request) {
    List<User> users = cloneUsers(request, 10000);
    long start = System.currentTimeMillis();

    userRepository.saveAll(users);

    long end = System.currentTimeMillis();

    double workingTime = end - start;
    logger.info("WorkingTime=[{}s]", workingTime / 1000);
  }

  public void createUserTest(UserDTO.Request request) {
    List<User> users = cloneUsers(request, 10000);
    long start = System.currentTimeMillis();

    userRepository.saveAllTest();

    long end = System.currentTimeMillis();

    double workingTime = end - start;
    logger.info("WorkingTime=[{}s]", workingTime / 1000);
  }

  private List<User> cloneUsers(UserDTO.Request request, Integer count) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      User user = request.toUser();
      user.setUsername(user.getUsername() + "_" + i);
      users.add(user);
    }
    return users;
  }
}
