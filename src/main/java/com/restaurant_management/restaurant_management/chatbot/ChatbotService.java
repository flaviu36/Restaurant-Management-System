package com.restaurant_management.restaurant_management.chatbot;

import com.restaurant_management.restaurant_management.db.Reservation;
import com.restaurant_management.restaurant_management.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private final WebClient client;
    private final String ollamaModel = "gemma3:4b";

    @Autowired
    private ReservationService reservationService;

    public ChatbotService(ReservationService reservationService) {
        this.reservationService = reservationService;
        this.client = WebClient.builder()
                .baseUrl("http://localhost:11434")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String askOllama(String prompt) {

        String data = reservationService.getAllReservations().stream()
                .map(Reservation::toString)
                .collect(Collectors.joining(", "));

        Map<String, Object> request = Map.of(
                "model", ollamaModel,
                "messages", List.of(
                        Map.of("role", "user", "content", "You are a reservation database assistant.\n\n" +
                                "Here is the package database in JSON format:\n" +
                                "<<DATA>>\n[" + data + "]\n<<END_DATA>>\n\n" +
                                "You must answer ONLY based on this database.\n" +
                                "If the question does not refer to the database, reply with: <<I cannot answer>>.\n\n" +
                                "User question:\n" +
                                prompt)
                ),
                "stream", false
        );

        try {
            OllamaChatResponse result = client.post()
                    .uri("/api/chat")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(OllamaChatResponse.class)
                    .block();

            return result.message.content;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error while contacting Ollama: " + e.getMessage();
        }
    }
}

class OllamaChatResponse {
    public String model;
    public String created_at;
    public Message message;

    public static class Message {
        public String role;
        public String content;
    }
}