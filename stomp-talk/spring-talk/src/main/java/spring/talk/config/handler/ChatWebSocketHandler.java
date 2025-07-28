package spring.talk.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // key : 세션 id, value : 웹소켓 세션
    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /**
     * 새로운 세션이 생성되었을 때, 이를 HashMap으로 관리하도록 수행합니다.
     *
     * @param session 새로 생성된 session
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("[+] afterConnectionEstablished :: " + session.getId());
        sessions.put(session.getId(), session);
    }

    /**
     * 미지의 세션에서 수신한 text message를 세션 목록에서 탐색 후 발송합니다.
     *
     * @param session destination
     * @param message received for send
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("[+] handleTextMessage :: " + session);
        System.out.println("[+] handleTextMessage :: " + message.getPayload());

        sessions.forEach((key, value) -> {
            System.out.println("key :: " + key + " value :: " + value);
            if (!key.equals(session.getId())) {
                try {
                    value.sendMessage(message);
                } catch (IOException e) {
                    log.error("[!] handleTextMessage :: ", e);
                }
            }
        });
    }

    /**
     * 커넥션 종료 후 세션 처리를 담당합니다.
     *
     * @param session 삭제할 WebSocketSession
     * @param status  닫힘 상태 출력용
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
        System.out.println("[+] afterConnectionClosed - Session: " + session.getId() + ", CloseStatus: " + status);
    }
}
