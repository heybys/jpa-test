package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.dto.UserCreate;
import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import com.heybys.optimusamicus.user.entity.UserGroup;
import com.heybys.optimusamicus.user.repository.UserGroupRepository;
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

  private final UserGroupRepository userGroupRepository;

  public Page<User> searchUsers(UserSearch.Request request, Pageable pageable) {
    return userRepository.searchUsers(request, pageable);
  }

  public UserCreate.Response getUserById(Long userId) {
    return userRepository.findById(userId).map(UserCreate.Response::new)
        .orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  public void createUser(UserCreate.Request request) {
    UserGroup userGroup = UserGroup.builder().userGroupName("TestGroup").build();
    userGroupRepository.save(userGroup);

    User user = request.toUser();
    user.setUserType(UserType.NORMAL);
    user.setAddress("Republic Of Korea");
    user.setUserGroup(userGroup);

    List<User> users = cloneUser(user, 777);

    userRepository.batchInsert(users);
  }

  private List<User> cloneUser(User user, Integer count) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      users.add(User.builder().username(user.getUsername() + "_" + i).build());
    }
    return users;
  }
}
