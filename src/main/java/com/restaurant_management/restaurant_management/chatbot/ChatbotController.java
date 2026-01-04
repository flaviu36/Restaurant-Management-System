package com.restaurant_management.restaurant_management.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin
public class ChatbotController {
    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public String chat(@RequestBody Map<String, String> body) {
        String request = body.get("question");
        return chatbotService.askOllama(request);
    }
}
