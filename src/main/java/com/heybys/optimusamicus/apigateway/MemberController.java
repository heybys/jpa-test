package com.heybys.optimusamicus.apigateway;

import com.heybys.optimusamicus.apigateway.exception.UnauthorizedException;
import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.service.MemberService;
import com.heybys.optimusamicus.member.service.model.Credentials;
import com.heybys.optimusamicus.member.service.model.RegisterUserInfo;
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
@RequestMapping("/api/v1/member")
public class MemberController {

  public final MemberService memberService;

  @PostMapping("")
  public ResponseEntity<CommonResponse> register(
      HttpServletRequest request, @RequestBody @Valid RegisterUserInfo registerUserInfo) {

    try {
      String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
      Credentials credentials = Credentials.of(authorization);
      Member member = registerUserInfo.toMemberWith(credentials);
      return ResponseEntity.ok(CommonResponse.success(memberService.register(member)));
    } catch (Exception e) {
      throw new UnauthorizedException(e);
    }
  }
}
