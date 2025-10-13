# BFD (Bidirectional Forwarding Detection)

라우터나 스위치 사이의 링크(경로)가 제대로 살아있는지 빠르게 감지하는 프로토콜

일반적으로 라우팅 프로토콜(OSPF, BGP, etc.)이 링크 장애를 감지하려면 수 초 이상 걸릴 수 있는데, BFD를 사용하면 밀리초 단위로 빠르게 감지하고 반응할 수 있다.

1. 동작 원리
- 두 장비(예: 라우터 A, B)가 서로 BFD 세션을 구성
- 이후 서로 주기적으로 “Hello 패킷”을 교환
- 일정시간 동안 상대방의 응답이 없으면 → 링크 장애로 판단.
- 이 정보를 OSPF, BGP 같은 상위 라우팅 프로토콜에 알려서 즉시 경로 전환(failover)을 유도

2. 효과
- 장애 감지 속도 극적으로 감소 (일반 OSPF: 1~10초 → BFD: 50~300ms)
- 라우팅 프로토콜에 독립적이어서, OSPF, BGP, IS-IS 등 어디에도 연동 가능
- 경로 안정성 향상, 빠른 복구

3. 예시
```bash
router ospf 1
 bfd all-interfaces
!
interface GigabitEthernet0/0
 bfd interval 300 min_rx 300 multiplier 3
```