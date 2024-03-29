package com.example.pfebackend.contoller;


import com.example.pfebackend.models.Message;
import com.example.pfebackend.models.User;
import com.example.pfebackend.service.Impl.MessageService;
import com.example.pfebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(MessageService messageService, UserService userService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {

        User sender = userService.getUserById(message.getSenderId());
        User recipient = userService.getUserById(message.getRecipientId());

        if (sender != null && recipient != null) {
            String chatId = messageService.generateChatId(sender.getId(), recipient.getId());
            message.setChatId(chatId);

            message.setMessageSender(sender);
            messageService.saveMessage(message);

            sender.getSentMessages().add(message);
            userService.saveUser(sender);

            recipient.getReceivedMessages().add(message);
            userService.saveUser(recipient);

            messageService.sendMessageToUser(message.getRecipient(), message);

            return ResponseEntity.ok("Message sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid sender or recipient ID");
        }
    }



    @GetMapping("/chat/{userId1}/{userId2}")
    public ResponseEntity<List<Message>> getChatBetweenUsers(
            @PathVariable int userId1,
            @PathVariable int userId2) {
        List<Message> chatMessages = messageService.getChatMessagesBetweenUsers(userId1, userId2);

        System.out.println("Nombre de messages récupérés : " + chatMessages.size());

        return ResponseEntity.ok(chatMessages);
    }


    @MessageMapping("/send/message")
    public void sendMessageWebSocket(@Payload Message message) {

        User sender = userService.getUserById(message.getSenderId());
        User recipient = userService.getUserById(message.getRecipientId());

        if (sender != null && recipient != null) {
            message.setMessageSender(sender);
            messageService.saveMessage(message);

            sender.getSentMessages().add(message);
            userService.saveUser(sender);

            recipient.getReceivedMessages().add(message);
            userService.saveUser(recipient);

            messageService.sendMessageToUser(message.getRecipient(), message);
        }
    }
    @SubscribeMapping("/chat/{userId1}/{userId2}")
    public List<Message> getChatBetweenUsersWebSocket(
            @DestinationVariable int userId1,
            @DestinationVariable int userId2) {

        return messageService.getChatMessagesBetweenUsers(userId1, userId2);
    }
}
