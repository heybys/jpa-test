package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  @PostMapping("/login")
  public String login(
      HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL) {

    Map<String, String> loginMember = new HashMap<>();
    loginMember.put("name", StringUtils.generateRandomAlphabets(8));
    loginMember.put("age", StringUtils.generateRandomNumbers(2));

    HttpSession session = request.getSession();
    session.setAttribute("memberInfo", loginMember);

    return "redirect:" + redirectURL;
  }
}
