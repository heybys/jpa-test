package com.heybys.optimusamicus.apigateway;

import com.heybys.optimusamicus.apigateway.exception.UnauthorizedException;
import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.member.service.AuthService;
import com.heybys.optimusamicus.member.service.model.Credentials;
import com.heybys.optimusamicus.member.service.model.SessionUserInfo;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@LogExecutionTime
@RequiredArgsConstructor
@RestController
public class AuthController {

  private final AuthService authService;

  @PostMapping("/api/v1/login")
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

  @DeleteMapping("/api/v1/logout")
  public ResponseEntity<CommonResponse> logout(HttpServletRequest request) {

    try {
      authService.logout();
      return ResponseEntity.ok(CommonResponse.success());
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }
}
