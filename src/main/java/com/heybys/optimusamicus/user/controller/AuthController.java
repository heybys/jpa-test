package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.user.controller.dto.create.UserCreate;
import com.heybys.optimusamicus.user.domain.entity.User;
import com.heybys.optimusamicus.user.service.AuthInfo;
import com.heybys.optimusamicus.user.service.AuthService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<CommonResponse> login(
      HttpServletRequest request, HttpServletResponse response) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      AuthInfo authInfo = parse(authorization);

      authService.login(authInfo);

      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail());
    }
  }

  @PostMapping("/register")
  public ResponseEntity<CommonResponse> register(
      HttpServletRequest request, @RequestBody @Valid UserCreate.Request userInfo) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      AuthInfo authInfo = parse(authorization);

      User user =
          User.builder()
              .username(authInfo.getUsername())
              .password(authInfo.getPassword())
              .address(userInfo.getAddress())
              .phoneNumber(userInfo.getPhoneNumber())
              .email(userInfo.getEmail())
              .build();

      authService.register(user);

      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail());
    }
  }

  private AuthInfo parse(final String authorization) {
    if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
      // authorization: Basic base64credentials
      String base64Credentials = authorization.substring("Basic".length()).trim();
      byte[] decoded = Base64.getDecoder().decode(base64Credentials);
      String credentials = new String(decoded, StandardCharsets.UTF_8);
      // credentials = username:password
      String[] splits = credentials.split(":", 2);

      return AuthInfo.of(splits[0], splits[1]);
    }
    throw new IllegalArgumentException();
  }
}
