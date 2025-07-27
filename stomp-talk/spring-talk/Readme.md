# Spring Boot WebSocket STOMP Chatting Application

## 프로젝트 소개

본 프로젝트는 Spring Boot와 WebSocket, STOMP 프로토콜을 이용하여 구현한 실시간 채팅 애플리케이션입니다. 학습 목적으로 제작되었으며, 기본적인 채팅 기능을 제공합니다.

## 주요 기능

* **실시간 양방향 통신:** WebSocket과 STOMP를 사용하여 클라이언트와 서버 간의 실시간 양방향 통신을 지원합니다.
* **메시지 브로드캐스팅:** 특정 주제(Topic)를 구독하는 모든 클라이언트에게 메시지를 전달합니다.

## 기술 스택

* **Back-end:**
    * Java 17
    * Spring Boot 3.5.4
    * Spring Web
    * Spring WebSocket
    * Lombok
* **Front-end:** nest-talk
* **Build Tool:** Gradle

## 아키텍처

```
Client  <-- WebSocket/STOMP -->  Spring Boot Server
                                  - WebSocketConfig
                                  - WebSocketStompBrokerConfig
                                  - ChatController
                                  - ChatWebSocketHandler
```

## API Endpoints

* **WebSocket Endpoint:** `/ws-stomp`
* **Message Broker:**
    * **Subscribe:** `/sub/message`
    * **Publish:** `/pub/messages`

## DTO

### ChatMessageDto

```java

@Data
@AllArgsConstructor
public class ChatMessageDto {
    private String content;
    private String sender;
}
```

## 향후 개선 사항

* **사용자 인증/인가:** Spring Security를 이용한 사용자 인증 및 인가 기능 추가
* **채팅방 기능:** 다중 채팅방 생성 및 참여 기능 구현
* **메시지 저장:** 데이터베이스에 채팅 메시지 저장 및 조회 기능 추가
