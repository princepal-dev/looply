package com.princeworks.looply.service;

import com.princeworks.looply.payload.request.MessageDTO;
import com.princeworks.looply.payload.response.MessageResponseDTO;
import jakarta.validation.Valid;

public interface MessageService {
    MessageResponseDTO createNewMessage(@Valid MessageDTO messageDTO);
}
