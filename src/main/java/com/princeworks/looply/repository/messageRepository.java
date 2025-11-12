package com.princeworks.looply.repository;

import com.princeworks.looply.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface messageRepository extends JpaRepository<Message, Long> {}
