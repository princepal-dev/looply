package com.princeworks.looply.controller;

import com.princeworks.looply.model.User;
import com.princeworks.looply.repository.UserRepository;
import com.princeworks.looply.security.request.SignUpRequest;
import com.princeworks.looply.security.response.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired private UserRepository userRepository;

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (userRepository.findByUserName(signUpRequest.getUserName())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.findByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already taken!"));
    }

    User user =
        new User(
            signUpRequest.getEmail(), signUpRequest.getUserName(), signUpRequest.getPassword());
    userRepository.save(user);
    return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
  }
}
