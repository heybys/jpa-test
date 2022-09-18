package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.exception.AuthenticationFailedException;
import com.heybys.optimusamicus.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/users/{userId}")
  public ResponseEntity<CommonResponse> authenticate(@PathVariable Long userId) {

    try {
      authenticationService.story(userId);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS), HttpStatus.OK);
    } catch (Exception e) {
      throw new AuthenticationFailedException();
    }
  }
}
