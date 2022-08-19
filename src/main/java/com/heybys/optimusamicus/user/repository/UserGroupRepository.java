package com.heybys.optimusamicus.user.repository;

import com.heybys.optimusamicus.user.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {}
