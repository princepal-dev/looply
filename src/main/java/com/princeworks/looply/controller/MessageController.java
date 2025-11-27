package com.princeworks.looply.controller;

import com.princeworks.looply.payload.request.MessageDTO;
import com.princeworks.looply.payload.response.MessageResponseDTO;
import com.princeworks.looply.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {
  @Autowired private MessageService messageService;

  // 1. Send a Message
  @PostMapping("/messages/send")
  public ResponseEntity<MessageResponseDTO> sendMessage(@Valid @RequestBody MessageDTO messageDTO) {
    MessageResponseDTO savedMessage = messageService.createNewMessage(messageDTO);
    return ResponseEntity.ok(savedMessage);
  }

  // 2. Get Message of a group
  // 3. Get Convo between two persons
  // 4. Delete a Message
  // 5. Edit a Message
  // 6. Send Message with attachments
  // 7. Fetch attachments of a message
}
