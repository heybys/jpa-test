package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.user.exception.UnauthorizedException;
import com.heybys.optimusamicus.user.service.UserService;
import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.RegisterUserInfo;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

  public final UserService userService;

  @PostMapping("")
  public ResponseEntity<CommonResponse> register(
      HttpServletRequest request, @RequestBody @Valid RegisterUserInfo registerUserInfo) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      Credentials credentials = Credentials.of(authorization);

      userService.register(credentials, registerUserInfo);

      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      throw new UnauthorizedException(e);
    }
  }
}
