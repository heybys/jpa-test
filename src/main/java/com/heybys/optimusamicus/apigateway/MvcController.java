package com.heybys.optimusamicus.apigateway;

import com.heybys.optimusamicus.member.domain.Member;
import com.heybys.optimusamicus.member.service.MemberService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class MvcController {

  public final MemberService memberService;

  @GetMapping("/")
  public String index(Model model, Principal principal) {
    if (principal == null) {
      model.addAttribute("message", "Hello Spring Security");
    } else {
      model.addAttribute("message", "Hello, " + principal.getName());
    }

    return "index";
  }

  @GetMapping("/info2")
  public ResponseEntity<String> info2() {
    return ResponseEntity.ok("info2");
  }

  @GetMapping("/info")
  public String info(Model model) {
    model.addAttribute("message", "Here are more information.");
    return "info";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model, Principal principal) {
    model.addAttribute("message", "Hi, " + principal.getName());
    memberService.dashboard();
    return "dashboard";
  }

  @GetMapping("/admin")
  public String admin(Model model, Principal principal) {
    model.addAttribute("message", "Hello Admin, " + principal.getName());
    return "admin";
  }

  @ResponseBody
  @GetMapping("/member/{username}/{password}/{role}")
  public Member createMember(Member member) {
    return memberService.register(member);
  }
}
