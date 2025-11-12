package com.princeworks.looply.payload;

import com.princeworks.looply.model.MessageAttachment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long groupId;
    private Long senderId;
    private Long receiverId;

    @NotBlank
    private String message;
    private List<AttachmentDTO> attachments;
}
