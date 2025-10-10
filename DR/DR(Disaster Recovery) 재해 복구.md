# DR(Disaster Recovery) : 재해 복구

인프라 환경에서 장애나 재해 발생 시, 서비스 중단 없이 또는 최소한의 중단으로 복구하기 위한 핵심 아키텍처 설계입니다.

1. DR 개념 요약
- DR(Disaster Recovery) : 천재지변, 화재, 전산 장애 등으로 인해 서비스가 중단되었을 때, 백업 시스템으로 복구하는 체계.
- RTO(Recovery Time Objective) : 복구에 걸리는 시간 목표
- RPO(Recovery Point Objective) : 데이터 손실 허용 시간 (예: 5분이면 5분 전 데이터까지만 복구 가능)

⇒ RTO, RPO 이 두가지 목표가 DR 설계의 핵심입니다.

2. 인프라 DR 구축의 기본 구조

일반적으로 메인 센터(Primary)와 DR 센터(Secondary)로 나누어 구성합니다.

```
		사용자
		↓
[Primary Center] ——— 실시간 복제 ——— [DR Center]
		ㄴDB / App / Storage			 ㄴ 백업용 DB / App / Storage
```

주요 구성요소

Compute (서버) : 서비스 애플리케이션이 실행되는 서버. 가상머신, 컨테이너, 혹은 물리 서버

Storage (스토리지) : DB, 로그, 파일 등 데이터 저장소. 스냅샷 또는 실시간 복제 사용

Network (네트워크) : DR 센터 전환 시 트래픽 라우팅 전환을 위한 DNS, LB 설정

Database (DBN) : 실시간 복제 (Master-Slave, Multi-AZ), 또는 주기적 백업/복원

Backup/Sync : 파일 단위, 블록 단위, 또는 DB 단위의 실시간 동기화

3. DR 구축 방식 유형

| 유형 | 설명 | 복구속도 | 비용 |
| --- | --- | --- | --- |
| Warm Site | DR센터에 서버가 미리 구동. 데이터는 실시간 또는 주기적 복제 | 빠름 | 중간 |
| Hot Site | 완전 동일한 인프라를 두 센터에 동시에 운영(Active-Active) | 매우 빠름 | 높음 |
| Cold Site | 하드웨어 공간만 마련, 재해 시 장비 설치 후 복구 | 느림 | 저렴 |

4. DR 구축 절차

1) 요구사항 정의
- 서비스 중요도 분류 (핵심 서비스 vs 부가 서비스)
- RTO, RPO 설정
2) 아키텍처 설계
- 메인 ↔ DR 센터 간 거리, 네트워크 대역폭
- 동기식/비동기식 복제 여부
- DNS 전환 또는 LB 전환 구조
3) 데이터 복제 구성요소
- DB : MySQL → Replica, Oracle → Data Guard, Mongo → Replica Set
- 스토리지 : SAN replication, S3 Cross-Region Replication
4) 애플리케이션 레벨 복제
- CI/CD 시 DR 환경에서도 자동 배포
- 설정 파일, 시크릿 관리 등 동기화
5) 네트워크 구성
- DR 전환 시 DNS Failover 또는 Global Load Balancer 사용 (예 : Route53, Cloud DNS)
- VPN 또는 전용 회선으로 두 센터 연결
6) 복구 테스트(DR Drill)
- 정기적으로 복구 시뮬레이션 실시
- 실제 복구 소요 시간 측정 및 문서화
7) 모니터링 및 알림
- 메인센터 장애 탐지 → 자동 전환 스크립트 또는 수동 절차 실행
- 복구 완료 후 원복 절차 마련

5. 클라우드 환경에서의 DR 예시

| 유형 | 종류 |
| --- | --- |
| AWS | Multi-AZ, Cross-Region, Replication, Route53, Failover |
| Azure | Azure Site Recovery, Geo-Redundant Storage |
| GCP | Cloud SQL, Replica, Global Load Balancing |
| Naver Cloud / KT Cloud | Region 간 이중화 구성, Object Storage Cross Region |

6. DR 구축 시 주의점
- 단순 백업 시스템 ≠ DR 시스템 (복구 자동화가 되어야 함)
- 데이터 정합성 검증 : 복제 시 트랜잭션 유실 방지
- 테스트 주기화 : 분기별 또는 반기별로 DR 전환 테스트 필수
- 비용 최적화 : 전 서비스가 아닌 핵심 서비스 중심으로 설계
