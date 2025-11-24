package com.princeworks.looply.service;

import com.princeworks.looply.exceptions.ResourceNotFoundException;
import com.princeworks.looply.model.Group;
import com.princeworks.looply.model.Message;
import com.princeworks.looply.model.MessageAttachment;
import com.princeworks.looply.model.User;
import com.princeworks.looply.payload.request.AttachmentDTO;
import com.princeworks.looply.payload.request.MessageDTO;
import com.princeworks.looply.payload.response.MessageResponseDTO;
import com.princeworks.looply.repository.GroupRepository;
import com.princeworks.looply.repository.UserRepository;
import com.princeworks.looply.repository.messageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
  @Autowired private ModelMapper modelMapper;
  @Autowired private UserRepository userRepository;
  @Autowired private GroupRepository groupRepository;
  @Autowired private messageRepository messageRepository;

  @Override
  public MessageResponseDTO createNewMessage(MessageDTO messageDTO) {
    User sender =
        userRepository
            .findById(messageDTO.getSenderId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Sender", "userId", messageDTO.getSenderId()));

    User receiver = null;
    if (messageDTO.getReceiverId() != null)
      receiver =
          userRepository
              .findById(messageDTO.getReceiverId())
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          "Receiver", "userId", messageDTO.getReceiverId()));

    Group group = null;
    if (messageDTO.getGroupId() != null)
      group =
          groupRepository
              .findById(messageDTO.getGroupId())
              .orElseThrow(
                  () -> new ResourceNotFoundException("Group", "groupId", messageDTO.getGroupId()));

    Message message = new Message(sender, receiver, messageDTO.getMessage(), group);

    if (messageDTO.getAttachments() != null) {
      List<MessageAttachment> attachments = new ArrayList<>();

      for (AttachmentDTO item : messageDTO.getAttachments()) {
        MessageAttachment attachment = new MessageAttachment();
        attachment.setMessage(message);
        attachment.setFileUrl(item.getFileUrl());
        attachment.setFileName(item.getFileName());
        attachment.setFileType(item.getFileType());
        attachments.add(attachment);
      }

      message.setAttachments(attachments);
    }

    messageRepository.save(message);

    MessageResponseDTO responseDTO =
        new MessageResponseDTO(
            message.getMessageId(),
            message.getMessage(),
            messageDTO.getSenderId(),
            messageDTO.getReceiverId(),
            messageDTO.getGroupId(),
            message.getCreatedAt());

    List<AttachmentDTO> attachmentDTOS =
        message.getAttachments().stream()
            .map(item -> modelMapper.map(item, AttachmentDTO.class))
            .toList();

    responseDTO.setAttachments(attachmentDTOS);
    return responseDTO;
  }
}
