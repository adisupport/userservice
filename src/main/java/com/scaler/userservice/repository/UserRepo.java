package com.scaler.userservice.repository;

import com.scaler.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findByEmail(String email);

}