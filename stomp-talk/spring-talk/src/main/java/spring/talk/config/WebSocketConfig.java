package spring.talk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import spring.talk.config.handler.ChatWebSocketHandler;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatWebSocketHandler chatWebSocketHandler;

    /**
     * Handler 등록
     * <p>만든 WebSocketHandler를 WebSocketHandlerRegistry에 등록합니다.
     *
     * @param registry WebSocketHandlerRegistry, 웹소켓 Handler를 등록하기 위한 interface 입니다.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("[+] 최초 WebSocket 연결을 위한 등록 Handler");
        registry.addHandler(chatWebSocketHandler, "ws-stomp")
                .setAllowedOrigins("http://localhost:3000");
    }
}
