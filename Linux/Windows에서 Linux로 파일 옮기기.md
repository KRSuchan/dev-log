# Windows에서 Linux로 파일 옮기기

**1. SCP (SSH가 가능한 경우)**

- 방법

    아래 커맨드 입력

    ~~~bash

    scp "C:\경로\파일이름.txt" user@서버IP:/home/user/

    ~~~
    -> 비밀번호 입력하면 전송 완료(Linux 서버에 SSH 접근이 가능해야함)

**2. WindSCP (GUI프로그램)**

- 방법

    1. 아래 정보 입력
    - 파일 프로토콜 : SFTP
    - 서버 IP
    - 사용자 이름 / 비밀번호 입력
    2. 로그인 -> 좌 : Windows / 우 : Linux
    3. 드래그 앤 드롭으로 전송
    (SSH 키로 접속 시 고급->인증->개인키 파일 선택)

**3. PuTTY + PSCP (명령어 도구)**

- 방법 (PSCP.exe 파일 위치 확인 필요. 디폴트 : C:\Program Files\PuTTY\)

    아래 커맨드 입력

    ~~~bash

    "C:\Program Files\PuTTY\pscp.exe" C:\test.txt user@서버IP:/home/user/

    ~~~
    -> 비밀번호 입력하면 전송 완료

