package com.heybys.jpatest.main.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @PostMapping("/jpa-test/v1/members")
    public String getAllMembers() {

        return "";
    }
}
