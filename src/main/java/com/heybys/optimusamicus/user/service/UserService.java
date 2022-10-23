package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.user.dto.search.UserSearch.Request;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogExecutionTime
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public User retrieveUser(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }

  @Transactional(readOnly = true)
  public List<User> retrieveUsers(Request request, Pageable pageable) {
    return userRepository.retrieveUsers(request, pageable).getContent();
  }

  @Transactional
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public Long updateUser(Long userId, Map<String, Object> params) {
    return userRepository.patchUser(userId, params);
  }

  @Transactional
  public List<User> createUsers(List<User> users) {
    return userRepository.batchInsert(users);
  }
}
