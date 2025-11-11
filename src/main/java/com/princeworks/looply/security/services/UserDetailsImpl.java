package com.princeworks.looply.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.princeworks.looply.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Getter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private static final Long serialVersionUID = 1L;

  private Long id;
  private String email;
  private String userName;

  @JsonIgnore private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
      Collection<? extends GrantedAuthority> authorities,
      String email,
      Long id,
      String password,
      String userName) {
    this.id = id;
    this.authorities = authorities;
    this.email = email;
    this.password = password;
    this.userName = userName;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    return new UserDetailsImpl(
        authorities, user.getEmail(), user.getUserId(), user.getPassword(), user.getUserName());
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean equals(Object b) {
    if (this == b) return true;
    if (b == null || getClass() != b.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) b;
    return Objects.equals(id, user.id);
  }
}
