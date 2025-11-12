package com.princeworks.looply.repository;

import com.princeworks.looply.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(
      @Size @NotBlank @Size(min = 4, message = "Please provide a valid username!") String userName);

  boolean existsByEmail(
      @Email @NotBlank @Size(max = 30, message = "Email cannot be more than 30 characters!")
          String email);

    boolean existsByUserName(@Size @NotBlank @Size(min = 4, message = "Please provide a valid username!") String userName);
}
