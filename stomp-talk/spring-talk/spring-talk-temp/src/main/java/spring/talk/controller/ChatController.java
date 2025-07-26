package spring.talk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.talk.dto.ChatMessageDto;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/messages")
    public ChatMessageDto send2(@RequestBody ChatMessageDto chatMessageDto) {
        template.convertAndSend("/sub/message", chatMessageDto);
        return chatMessageDto;
    }
}
