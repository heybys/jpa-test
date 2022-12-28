package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.user.exception.UnauthorizedException;
import com.heybys.optimusamicus.user.service.AuthService;
import com.heybys.optimusamicus.user.service.model.AuthConst;
import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.UserProfile;
import com.heybys.optimusamicus.user.service.model.UserRegisterInfo;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<CommonResponse> register(
      HttpServletRequest request, @RequestBody @Valid UserRegisterInfo userRegisterInfo) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      Credentials credentials = Credentials.of(authorization);

      authService.register(credentials, userRegisterInfo);

      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      throw new UnauthorizedException(e);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponse> login(HttpServletRequest request) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      Credentials credentials = Credentials.of(authorization);

      UserProfile userProfile = authService.login(credentials);

      return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.success(userProfile));
    } catch (Exception e) {
      throw new UnauthorizedException(e);
    }
  }

  @DeleteMapping("/logout")
  public ResponseEntity<CommonResponse> logout(HttpServletRequest request) {

    try {
      authService.logout();
      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @GetMapping("/profile")
  public ResponseEntity<CommonResponse> getProfile(
      @SessionAttribute(name = AuthConst.USER_INFO, required = false) UserProfile profile) {
    try {
      return ResponseEntity.ok(CommonResponse.success(profile));
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }
}
