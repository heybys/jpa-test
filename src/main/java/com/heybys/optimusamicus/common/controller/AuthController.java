package com.heybys.optimusamicus.common.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
      @RequestParam(defaultValue = "/") String redirectURL) {

    if (authService.authenticate()) {
      return ResponseEntity.ok(CommonResponse.success(redirectURL));
    } else {
      // 'UNAUTHORIZED' is used to mean 'UNAUTHENTICATED'
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.fail());
    }
  }
}
