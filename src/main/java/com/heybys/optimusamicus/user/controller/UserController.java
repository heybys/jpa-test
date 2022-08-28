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
import org.springframework.beans.BeanUtils;
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

@LogExecutionTime
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final UserGroupService userGroupService;

  @GetMapping("")
  public ResponseEntity<CommonResponse> retrieveUsers(
      UserSearch.Request request, @PageableDefault() Pageable pageable) {

    try {
      // call service
      List<User> retrievedUsers = userService.retrieveUsers(request, pageable);

      // convert entity to dto
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
      // call service
      User retrievedUser = userService.retrieveUser(userId);

      // convert entity to dto
      UserSearch.Response response = UserSearch.Response.from(retrievedUser);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
  }

  @PostMapping("")
  public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreate.Request request) {

    try {
      // convert dto to entity
      User user = request.toUser();
      Long userGroupId = request.getUserGroupId();
      if (userGroupId != null) {
        user.setUserGroup(userGroupService.retrieveUserGroup(userGroupId));
      }

      // call service
      User createdUser = userService.createUser(user);

      // convert entity to dto
      UserCreate.Response response = UserCreate.Response.from(createdUser);

      return new ResponseEntity<>(
          new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.CREATED);
    } catch (Exception e) {
      throw new UserNotCreatedException();
    }
  }

  @PostMapping("/clones")
  public ResponseEntity<CommonResponse> createUserClones(
      @RequestBody @Valid UserCreate.Request request) {

    try {
      // convert dto to entity
      User user = request.toUser();
      Long userGroupId = request.getUserGroupId();
      if (userGroupId != null) {
        user.setUserGroup(userGroupService.retrieveUserGroup(userGroupId));
      }

      // call service
      List<User> userClones = makeUserClones(user, 10000);
      List<User> createdUserClones = userService.createUsers(userClones);

      // convert entity to dto
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
   * @param user  user entity
   * @param count copy count
   * @return copied user list
   */
  private List<User> makeUserClones(User user, Integer count) {
    List<User> users = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      User build = User.builder().build();

      BeanUtils.copyProperties(user, build);
      build.setName(user.getName() + "_" + i);
      build.setPhoneNumber(user.getPhoneNumber() + i);

      users.add(build);
    }
    return users;
  }
}
