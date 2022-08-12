package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.UserCreate;
import com.heybys.optimusamicus.user.dto.UserSearch;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

  @GetMapping("")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> searchUsers(UserSearch.Request request,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    Page<User> users = userService.searchUsers(request, pageable);

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS, users);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> getUserById(@PathVariable Long userId) {

    UserCreate.Response user = userService.getUserById(userId);

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS, user);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreate.Request request) {

    userService.createUser(request);

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/test")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> createUserTest(
      @RequestBody @Valid UserCreate.Request request) {

    userService.createUserTest(request);

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
