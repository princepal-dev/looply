package com.princeworks.looply.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message_attachment")
public class MessageAttachment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long messageAttachmentId;

  @ManyToOne
  @JoinColumn(name = "message_id", nullable = false)
  private Message message;

  @Column(nullable = false)
  private String fileUrl;

  private String fileName;
  private String fileType;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
