package com.princeworks.looply.payload.response;

import com.princeworks.looply.payload.request.AttachmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDTO {
  private Long messageId;
  private String message;
  private Long senderId;
  private Long receiverId;
  private Long groupId;
  private LocalDateTime createdAt;

  public MessageResponseDTO(
      Long messageId,
      String message,
      Long senderId,
      Long receiverId,
      Long groupId,
      LocalDateTime createdAt) {
    this.createdAt = createdAt;
    this.groupId = groupId;
    this.message = message;
    this.messageId = messageId;
    this.receiverId = receiverId;
    this.senderId = senderId;
  }

  private List<AttachmentDTO> attachments;
}
