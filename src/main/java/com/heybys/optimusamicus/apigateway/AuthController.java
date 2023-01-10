package com.heybys.optimusamicus.apigateway;

import com.heybys.optimusamicus.apigateway.exception.UnauthorizedException;
import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.user.service.AuthService;
import com.heybys.optimusamicus.user.service.model.Credentials;
import com.heybys.optimusamicus.user.service.model.SessionUserInfo;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<CommonResponse> login(HttpServletRequest request) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      Credentials credentials = Credentials.of(authorization);

      SessionUserInfo sessionUserInfo = authService.login(credentials);

      return ResponseEntity.status(HttpStatus.CREATED)
          .body(CommonResponse.success(sessionUserInfo));
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
}
