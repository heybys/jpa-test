package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.user.entity.UserGroup;
import com.heybys.optimusamicus.user.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserGroupService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final UserGroupRepository userGroupRepository;

  @Transactional
  public UserGroup createUserGroup(UserGroup userGroup) {
    return userGroupRepository.save(userGroup);
  }
}
