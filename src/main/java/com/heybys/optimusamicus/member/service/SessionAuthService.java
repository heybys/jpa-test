package com.heybys.optimusamicus.member.service;

import com.heybys.optimusamicus.common.utils.HttpServletRequestProvider;
import com.heybys.optimusamicus.common.utils.HttpServletResponseProvider;
import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.domain.MemberRepository;
import com.heybys.optimusamicus.member.service.model.Credentials;
import com.heybys.optimusamicus.member.service.model.SessionUserInfo;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionAuthService implements AuthService {

  private final MemberRepository memberRepository;

  @Override
  public SessionUserInfo login(Credentials credentials) {
    Member member =
        memberRepository
            .findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "There is no user with the username and password."));
    SessionUserInfo sessionUserInfo = SessionUserInfo.from(member);
    sessionUserInfo.addTo(HttpServletRequestProvider.getSession());

    return sessionUserInfo;
  }

  @Override
  public void logout() {
    // invalidate session
    HttpServletRequestProvider.getSession(false).invalidate();

    // expire sessionId cookie
    HttpServletResponse response = HttpServletResponseProvider.getResponse();
    Cookie cookie = new Cookie("JSESSIONID", null);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }
}
