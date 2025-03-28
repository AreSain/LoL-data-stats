# LoL-data-stats

통계를 이용한 League of Legend 사용자 피드백 프로젝트

Riot API를 이용하여 얻은 최근 전적 데이터를 토대로 유저에게 유의미한 피드백을 제공하는 것이 목표


## 기술 기획

### Java, Spring Boot를 사용한 메인 서버
- MVC 패턴으로 작성
- 차후 통계분석 부분을 Python으로 교체하기 위해 멀티모듈로 작성하여 의존성 관리
- 중복조회를 막기 위해 유저의 전적을 MySQL에 저장하여 통계분석에 사용

### Python
- 통계분석의 성능을 위해 Python 서버를 추가 예정 (MSA방식, gRPC와 kafka로 성능을 개선하려 함)
- 위 자바 프로토타입으로 Riot API의 한도를 늘릴 수 있다면, PostgreSQL로 DB를 변경하여 통계분석을 진행할 예정
- 상위권 및 비슷한 유저와의 비교를 통한 피드백 제공

### 회원 관리
- Java 서버에 MySQL을 이용하여 회원 서비스 추가
