package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

  Page<User> findAllByUsername(String username, Pageable pageable);
}
