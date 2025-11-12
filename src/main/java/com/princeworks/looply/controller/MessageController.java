package com.princeworks.looply.controller;

import com.princeworks.looply.payload.MessageDTO;
import com.princeworks.looply.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    // TODO
    // 1. Send a Message
    @PostMapping("/messages/send")
    public ResponseEntity<MessageDTO> sendMessage(@Valid @RequestBody MessageDTO messageDTO) {
        MessageDTO savedMessage = messageService.createNewMessage(messageDTO);
        return new ResponseEntity<>(savedMessage, HttpStatus.OK);
    }

    // 2. Get Message from a user
    // 3. Get Message of a group
    // 4. Get Convo between two persons
    // 5. Delete a Message
    // 6. Edit a Message
    // 7. Send Message with attachments
    // 8. Fetch attachments of a message
}
