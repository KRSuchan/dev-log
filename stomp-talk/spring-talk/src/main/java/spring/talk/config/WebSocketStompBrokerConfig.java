package spring.talk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompBrokerConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * Message Broker 설정
     * <p> enableSimpleBroker : 수신자 prefix 설정 (다수 가능),
     * setApplicationDestinationPrefixes : 제공자 prefix 설정
     *
     * @param config 설정 파라미터 지정(MessageBrokerRegistry)
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    /**
     * Stomp 엔드포인트 등록
     * <p>stomp를 연결할 전용 엔드포인트 등록, CORS 허가할 접근할 주소 추가,
     * withSockJS는 websocket 미지원 환경에도 실시간 통신을 가능하게 도와줌
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }
}
