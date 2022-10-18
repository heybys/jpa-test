package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.create.UserCreate;
import com.heybys.optimusamicus.user.dto.search.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.exception.UserNotCreatedException;
import com.heybys.optimusamicus.user.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.service.UserGroupService;
import com.heybys.optimusamicus.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  private final UserGroupService userGroupService;

  @GetMapping("")
  public ResponseEntity<CommonResponse> retrieveUsers(
      UserSearch.Request request, @PageableDefault() Pageable pageable) {

    try {
      List<User> retrievedUsers = userService.retrieveUsers(request, pageable);

      List<UserSearch.Response> responses =
          retrievedUsers.stream().map(UserSearch.Response::from).collect(Collectors.toList());

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, responses), HttpStatus.OK);
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
  }

  @GetMapping("/{userId}")
  public ResponseEntity<CommonResponse> retrieveUser(@PathVariable Long userId) {

    try {
      User retrievedUser = userService.retrieveUser(userId);

      UserSearch.Response response = UserSearch.Response.from(retrievedUser);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
  }

  @PostMapping("")
  public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreate.Request request) {
    log.error("hello");
    try {
      // User user = request.toUser();
      // Long userGroupId = request.getUserGroupId();
      // if (userGroupId != null) {
      //   user.setGroup(userGroupService.retrieveUserGroup(userGroupId));
      // }
      //
      // User createdUser = userService.createUser(user);
      //
      // UserCreate.Response response = UserCreate.Response.from(createdUser);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS), HttpStatus.CREATED);
    } catch (Exception e) {
      throw new UserNotCreatedException();
    }
  }

  @PostMapping("/clones")
  public ResponseEntity<CommonResponse> createUserClones(
      @RequestBody @Valid UserCreate.Request request) {

    try {
      User user = request.toUser();
      Long userGroupId = request.getUserGroupId();
      if (userGroupId != null) {
        user.setGroup(userGroupService.retrieveUserGroup(userGroupId));
      }

      List<User> userClones = makeUserClones(user, 10000);
      List<User> createdUserClones = userService.createUsers(userClones);

      List<UserCreate.Response> responses =
          createdUserClones.stream().map(UserCreate.Response::from).collect(Collectors.toList());

      return new ResponseEntity<>(
          new CommonResponse(StatusCode.SUCCESS, responses), HttpStatus.CREATED);
    } catch (Exception e) {
      throw new UserNotCreatedException();
    }
  }

  /**
   * make user clones by source user entity
   *
   * @param user user entity
   * @param count copy count
   * @return copied user list
   */
  private List<User> makeUserClones(User user, Integer count) {
    List<User> userClones = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      User userClone =
          User.builder()
              .type(user.getType())
              .name(user.getName() + "_" + i)
              .address(user.getAddress())
              .phoneNumber(user.getPhoneNumber() + i)
              .group(user.getGroup())
              .build();

      userClones.add(userClone);
    }
    return userClones;
  }
}
