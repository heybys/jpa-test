package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.UserDTO;
import com.heybys.optimusamicus.user.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<CommonResponse> getAllUsers() {
    List<UserDTO.Response> allUsers = userService.getAllUsers();

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS, allUsers);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<CommonResponse> getUserById(@PathVariable Long userId) {
    UserDTO.Response user = userService.getUserById(userId);

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS, user);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserDTO.Request request) {
    userService.createUser(request);

    CommonResponse response = new CommonResponse(StatusCode.SUCCESS);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
