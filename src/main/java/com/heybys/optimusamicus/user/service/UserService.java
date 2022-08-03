package com.heybys.optimusamicus.user.service;

import com.heybys.optimusamicus.common.exception.UserNotFoundException;
import com.heybys.optimusamicus.user.dto.UserDTO;
import com.heybys.optimusamicus.user.entity.User;
import com.heybys.optimusamicus.user.repository.UserRepository;
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
