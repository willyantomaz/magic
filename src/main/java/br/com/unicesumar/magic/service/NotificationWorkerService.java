package br.com.unicesumar.magic.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationWorkerService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "deck_updates_queue")
    public void notifyDeckUpdate(String updateMessage) {
        messagingTemplate.convertAndSend("/topic/deckUpdates", updateMessage);
        System.out.println("Mensagem enviada para WebSocket: " + updateMessage);
    }
}
