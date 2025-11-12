package com.princeworks.looply.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private Long senderId;
    private Long receiverId;
    private Long groupId;
    private String content;
    private List<String> attachmentList;
    private LocalDateTime createdAt;
}
