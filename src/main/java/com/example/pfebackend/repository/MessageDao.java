package com.example.pfebackend.repository;

import com.example.pfebackend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageDao extends JpaRepository <Message, Long>{


    List<Message> findByChatIdOrderByDateSent(String chatId);
}
