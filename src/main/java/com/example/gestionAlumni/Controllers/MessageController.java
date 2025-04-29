package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.Entities.Message;
import com.example.gestionAlumni.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestParam Long senderId,
                               @RequestParam Long receiverId,
                               @RequestParam String content) {
        return messageService.sendMessage(senderId, receiverId, content);
    }

    @GetMapping("/between")
    public List<Message> getMessagesBetweenUsers(@RequestParam Long senderId,
                                                 @RequestParam Long receiverId) {
        return messageService.getMessagesBetweenUsers(senderId, receiverId);
    }

    @GetMapping("/conversation")
    public List<Message> getConversationMessages(@RequestParam Long senderId,
                                                 @RequestParam Long receiverId) {
        return messageService.getMessagesBetweenUsers(senderId, receiverId);
    }
}
