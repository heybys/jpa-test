package com.heybys.optimusamicus.user.domain.repository;

import com.heybys.optimusamicus.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameAndPassword(String username, String password);
}
