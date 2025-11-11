package com.princeworks.looply.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
  private Long userId;

  @Email
  @NotBlank
  @Size(max = 50)
  @Column(name = "email", nullable = false)
  private String email;

  @NotBlank
  @Size(max = 20)
  @Column(name = "user_name")
  private String userName;

  @NotBlank
  @Size(max = 120)
  private String password;

  private String image;

  public User(String email, String userName, String password) {
    this.email = email;
    this.userName = userName;
    this.password = password;
  }

  @OneToMany (mappedBy = "sender")
  private List<Message> messagesSent;

  @OneToMany (mappedBy = "receiver")
  private List<Message> messagesReceived;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
