package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.utils.StringUtils;
import com.heybys.optimusamicus.order.handler.TransactionHandler;
import com.heybys.optimusamicus.user.entity.User;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@LogExecutionTime
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserService userService;

  private final TransactionHandler transactionHandler;

  @Transactional
  public void story(Long userId) {
    User retrieveUser = userService.retrieveUser(userId);
    log.info("retrieveUser = " + retrieveUser);

    retrieveUser.autoGenerateName();
    log.info("updated retrieveUser = " + retrieveUser);

    Map<String, Object> params = new HashMap<>();
    String phoneNumber = "010" + StringUtils.generateRandomNumbers(8);
    params.put("phoneNumber", phoneNumber);
    Long count = userService.updateUser(retrieveUser.getId(), params);
    log.info("update user with phoneNumber = " + phoneNumber);

    log.info("last retrieveUser = " + retrieveUser);
  }
}
