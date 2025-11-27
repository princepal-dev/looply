package com.princeworks.looply.service;

import com.princeworks.looply.model.User;
import com.princeworks.looply.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepository;

  @Override
  public Long findUserIdByUserName(String userName) {
    User user =
        userRepository
            .findByUserName(userName)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + userName));
    return user.getUserId();
  }
}
