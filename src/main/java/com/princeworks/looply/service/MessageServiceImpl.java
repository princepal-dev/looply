package com.princeworks.looply.service;

import com.princeworks.looply.model.User;
import com.princeworks.looply.payload.MessageDTO;
import com.princeworks.looply.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageDTO createNewMessage(MessageDTO messageDTO) {
        User sender = userRepository.findById(messageDTO.getSenderId()).orElseThrow(() -> )
    }
}
