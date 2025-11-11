package com.princeworks.looply.repository;

import com.princeworks.looply.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean findByUserName(
      @Size @NotBlank @Size(min = 4, message = "Please provide a valid username!") String userName);

  boolean findByEmail(
      @Email @NotBlank @Size(max = 30, message = "Email cannot be more than 30 characters!")
          String email);
}
