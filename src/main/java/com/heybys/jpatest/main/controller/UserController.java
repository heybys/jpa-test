package com.heybys.jpatest.main.controller;

import com.heybys.jpatest.main.entity.User;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa-test/v1/users")
public class UserController {

    @PostMapping("")
    public List<User> getAllUsers() {
        return null;
    }
}
