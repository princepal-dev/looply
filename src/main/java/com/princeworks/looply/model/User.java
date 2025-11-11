package com.princeworks.looply.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email")
    })
public class User {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String userId;

  @Email
  @NotBlank
  @Size(max = 50)
  @Column(name = "email")
  private String email;

  @NotBlank
  @Size(max = 20)
  @Column(name = "user_name")
  private String userName;

  @NotBlank
  @Size(max = 120)
  private String password;

  public User(String email, String userName, String password) {
    this.email = email;
    this.userName = userName;
    this.password = password;
  }
}
