package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.UserCreate;
import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.entity.User.UserType;
import com.heybys.optimusamicus.user.entity.UserGroup;
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

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final UserGroupService userGroupService;

  @GetMapping("")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> retrieveUsers(
      UserSearch.Request request, @PageableDefault() Pageable pageable) {

    List<User> retrievedUsers = userService.retrieveUsers(request, pageable);

    List<UserSearch.Response> responses =
        retrievedUsers.stream()
            .map(user -> UserSearch.Response.builder().user(user).build())
            .collect(Collectors.toList());

    return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, responses), HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> retrieveUser(@PathVariable Long userId) {

    User retrievedUser = userService.retrieveUser(userId);

    UserSearch.Response response = UserSearch.Response.builder().user(retrievedUser).build();

    return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
  }

  @PostMapping("")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreate.Request request) {

    UserGroup userGroup = UserGroup.builder().userGroupName("UserGroupTest").build();
    UserGroup createdUserGroup = userGroupService.createUserGroup(userGroup);

    User user = request.toUser();
    user.setUserType(UserType.NORMAL);
    user.setAddress("AddressTest");
    user.setUserGroup(createdUserGroup);

    User createdUser = userService.createUser(user);

    UserCreate.Response response = UserCreate.Response.builder().user(createdUser).build();

    return new ResponseEntity<>(
        new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.CREATED);
  }

  @PostMapping("/clones")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> createUserClones(
      @RequestBody @Valid UserCreate.Request request) {

    UserGroup userGroup = UserGroup.builder().userGroupName("UserGroupTest").build();
    UserGroup createdUserGroup = userGroupService.createUserGroup(userGroup);

    User user = request.toUser();
    user.setUserType(UserType.NORMAL);
    user.setAddress("AddressTest");
    user.setUserGroup(createdUserGroup);

    List<User> createdClones = userService.createUsers(makeUserClones(user, 100));

    List<UserCreate.Response> responses =
        createdClones.stream()
            .map(clone -> UserCreate.Response.builder().user(clone).build())
            .collect(Collectors.toList());

    return new ResponseEntity<>(
        new CommonResponse(StatusCode.SUCCESS, responses), HttpStatus.CREATED);
  }

  private List<User> makeUserClones(User user, Integer count) {
    List<User> users = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      User build = User.builder().build();

      BeanUtils.copyProperties(user, build);
      build.setUsername(user.getUsername() + "_" + i);

      users.add(build);
    }
    return users;
  }
}
