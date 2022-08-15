package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.dto.UserSearch.Request;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final UserRepository userRepository;

  public List<User> retrieveUsers(Request request, Pageable pageable) {
    return userRepository.retrieveUsers(request, pageable).getContent();
  }

  public User retrieveUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public List<User> createUsers(List<User> users) {
    return userRepository.batchInsert(users);
  }
}
