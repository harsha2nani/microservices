package com.demo.security.repository;

import com.demo.security.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserCredential,Long> {

    Optional<UserCredential> findByName(String username);
}
