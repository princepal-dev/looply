package com.princeworks.looply.service;

import com.princeworks.looply.payload.MessageDTO;
import jakarta.validation.Valid;

public interface MessageService {
    MessageDTO createNewMessage(@Valid MessageDTO messageDTO);
}
