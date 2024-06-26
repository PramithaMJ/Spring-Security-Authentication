package com.pmj.springSecurity.repository;

import com.pmj.springSecurity.entity.Role;
import com.pmj.springSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    User findByRoles(Role role);
}
