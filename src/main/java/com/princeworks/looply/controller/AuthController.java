package com.princeworks.looply.controller;

import com.princeworks.looply.model.User;
import com.princeworks.looply.repository.UserRepository;
import com.princeworks.looply.security.jwt.JwtUtils;
import com.princeworks.looply.security.request.LoginRequest;
import com.princeworks.looply.security.request.SignUpRequest;
import com.princeworks.looply.security.response.MessageResponse;
import com.princeworks.looply.security.response.UserInfoResponse;
import com.princeworks.looply.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired private JwtUtils jwtUtils;
  @Autowired private UserRepository userRepository;
  @Autowired private AuthenticationManager authenticationManager;

  @PostMapping("/signin")
  public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication;
    try {
      authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getUserName(), loginRequest.getPassword()));
    } catch (AuthenticationException e) {
      Map<String, Object> map = new HashMap<>();
      map.put("message", "Bad credentials");
      map.put("status", false);
      return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    UserInfoResponse response =
        new UserInfoResponse(userDetails.getId(), userDetails.getUsername());
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(response);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (userRepository.existsByUserName(signUpRequest.getUserName())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already taken!"));
    }
    User user =
        new User(
            signUpRequest.getEmail(), signUpRequest.getUserName(), signUpRequest.getPassword());
    userRepository.save(user);
    return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
  }

  @GetMapping("/username")
  public String getUsername(Authentication authentication) {
    if (authentication != null) return authentication.getName();
    return "";
  }

  @GetMapping("/user")
  public ResponseEntity<?> getUserDetails(Authentication authentication) {
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    UserInfoResponse response =
        new UserInfoResponse(userDetails.getId(), userDetails.getUsername());
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/signout")
  public ResponseEntity<?> signOut() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
