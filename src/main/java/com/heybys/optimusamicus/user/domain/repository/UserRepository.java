package com.heybys.optimusamicus.user.domain.repository;

import com.heybys.optimusamicus.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

  User findByUsernameAndPassword(String username, String password);
}
