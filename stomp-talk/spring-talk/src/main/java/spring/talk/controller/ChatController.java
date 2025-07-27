package spring.talk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.talk.dto.ChatMessageDto;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/messages")
    public ChatMessageDto send2(@RequestBody ChatMessageDto chatMessageDto) {
        log.info("[+] {}  -> {}", chatMessageDto.getSender(), chatMessageDto.getContent());
        template.convertAndSend("/sub/message", chatMessageDto);
        return chatMessageDto;
    }
}
