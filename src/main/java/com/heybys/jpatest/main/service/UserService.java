package com.heybys.jpatest.main.service;

import com.heybys.jpatest.common.exception.UserNotFoundException;
import com.heybys.jpatest.dto.UserDTO;
import com.heybys.jpatest.main.entity.User;
import com.heybys.jpatest.main.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO.Response> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO.Response::new)
            .collect(Collectors.toList());
    }

    public UserDTO.Response getUserById(Long userId) {
        return userRepository.findById(userId).map(UserDTO.Response::new)
            .orElseThrow(UserNotFoundException::new);
    }

    public void createUser(UserDTO.Request request) {
        User user = request.toUser();
        userRepository.save(user);
    }
}
