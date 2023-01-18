package com.heybys.optimusamicus.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUsername(String username);

  Optional<Member> findByUsernameAndPassword(String username, String password);
}
