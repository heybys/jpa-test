package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.dto.UserSearch.Request;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.exception.UserNotCreatedException;
import com.heybys.optimusamicus.user.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> retrieveUsers(Request request, Pageable pageable) {
    return userRepository.retrieveUsers(request, pageable).getContent();
  }

  public User retrieveUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  public User createUser(User user) {
    try {
      return userRepository.save(user);
    } catch (Exception e) {
      throw new UserNotCreatedException();
    }
  }

  @Transactional
  public List<User> createUsers(List<User> users) {
    try {
      return userRepository.batchInsert(users);
    } catch (Exception e) {
      throw new UserNotCreatedException();
    }
  }
}
