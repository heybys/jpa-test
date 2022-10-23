package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.common.UserCommon;
import com.heybys.optimusamicus.user.dto.create.UserCreate;
import com.heybys.optimusamicus.user.dto.search.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.exception.UserNotCreatedException;
import com.heybys.optimusamicus.user.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.exception.UserNotPatchedException;
import com.heybys.optimusamicus.user.service.UserGroupService;
import com.heybys.optimusamicus.user.service.UserService;
import com.heybys.optimusamicus.user.validator.UserValidator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  private final UserValidator userValidator;

  @GetMapping("/{userId}")
  public ResponseEntity<CommonResponse> retrieveUser(@PathVariable Long userId) {

    try {
      User retrievedUser = userService.retrieveUser(userId);

      UserCommon.Response response = UserCommon.Response.from(retrievedUser);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
  }

  @GetMapping("")
  public ResponseEntity<CommonResponse> retrieveUsers(
      UserSearch.Request request, @PageableDefault() Pageable pageable) {

    try {
      List<User> retrievedUsers = userService.retrieveUsers(request, pageable);

      List<UserCommon.Response> responses =
          retrievedUsers.stream().map(UserCommon.Response::from).collect(Collectors.toList());

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, responses), HttpStatus.OK);
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
  }

  @PostMapping("")
  public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreate.Request request) {

    try {
      User user = request.toUser();
      Long userGroupId = request.getUserGroupId();
      if (userGroupId != null) {
        user.joinTo(userGroupService.retrieveUserGroup(userGroupId));
      }

      User createdUser = userService.createUser(user);

      UserCommon.Response response = UserCommon.Response.from(createdUser);

      return new ResponseEntity<>(
          new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.CREATED);
    } catch (Exception e) {
      throw new UserNotCreatedException();
    }
  }

  @PatchMapping("/{userId}")
  public ResponseEntity<CommonResponse> patchUser(
      @PathVariable Long userId, @RequestBody Map<String, Object> params) {

    try {
      userValidator.validate(params);

      User patchedUser = userService.updateUser(userId, params);

      UserCommon.Response response = UserCommon.Response.from(patchedUser);

      return new ResponseEntity<>(
          new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.ACCEPTED);
    } catch (Exception e) {
      throw new UserNotPatchedException();
    }
  }
}
