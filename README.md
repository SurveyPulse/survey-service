# MSA 기반 SurveyPulse 설문 서비스

SurveyPulse 플랫폼의 설문(Survey) 관리를 담당하는 마이크로서비스입니다.
설문 생성, 조회, 목록, 검색, 배포, 종료, 문항 조회 기능을 제공합니다.

## 주요 기능

- **설문 생성** (`POST /api/surveys`)
  - 요청 본문으로 설문 제목, 설명, 생성자 ID, 시작/종료 시간, 질문 목록 전송
  - 설문 및 연관 문항(Question) 엔티티 저장

- **설문 조회** (`GET /api/surveys/{surveyId}`)
  - 설문 기본 정보 및 질문, 생성자 사용자명 포함한 상세 정보 반환
  - Resilience4j 회로 차단기 적용된 Feign 호출로 사용자 정보 조회

- **설문 목록 조회** (`GET /api/surveys?page={page}`)
  - Redis(AWS ElastiCache) 캐싱 적용

- **설문 삭제** (`DELETE /api/surveys/{surveyId}`)
  - 설문과 연관된 모든 질문 삭제 후 설문 자체 삭제

- **설문 배포 (URL 생성)** (`POST /api/surveys/{surveyId}/deploy`)
  - 설문 상태 OPEN으로 변경 후 기본 URL(baseUrl + /{surveyId}) 반환

- **설문 종료** (`POST /api/surveys/{surveyId}/close`)
  - 설문 상태 CLOSED로 변경

- **설문 문항 목록 조회** (`GET /api/surveys/{surveyId}/questions`)
  - 특정 설문에 속한 모든 문항 및 설문 정보를 포함한 DTO 리스트 반환
  - Redis(AWS ElastiCache) 캐싱 적용

- **단일 문항 조회** (`GET /api/surveys/{surveyId}/{questionId}`)
  - 특정 설문 내 단일 문항 상세 정보 반환
  - Redis(AWS ElastiCache) 캐싱 적용

- **설문 제목 검색** (`GET /api/surveys/search?title={title}&page={page}`)
  - 제목 부분 일치 검색, 페이징된 서머리 응답

- **진행 중인 설문 조회** (`GET /api/surveys/active?page={page}`)
  - 현재 시간 기준 OPEN 상태인 설문 목록 조회
  - Redis(AWS ElastiCache) 캐싱 적용

## 기술 스펙

- **언어 & 프레임워크**: Java, Spring Boot
- **데이터베이스**: Spring Data JPA, MySQL(AWS RDS)
- **캐싱**: Redis(AWS ElastiCache)
- **HTTP 클라이언트**: OpenFeign
- **회로 차단기 & 복원력**: Resilience4j
- **보안**: Spring Security, JWT
- **로깅 & 모니터링**: Elasticsearch, Logstash, Kibana (ELK), Prometheus, Grafana
- **CI/CD**: GitHub Actions
- **컨테이너 & 오케스트레이션**: Docker, Kubernetes(AWS EKS), Helm
- **아키텍처**: 마이크로서비스 아키텍처(MSA)


## 아키텍처

![서비스 아키텍처 다이어그램](https://github.com/SurveyPulse/user-service/blob/main/docs/images/aws-architecture.png)
![RDS 아키텍처 다이어그램](https://github.com/SurveyPulse/user-service/blob/main/docs/images/aws-rds-architecture.png)


## CI/CD 아키텍처
![CI/CD 파이프라인 다이어그램](https://github.com/SurveyPulse/user-service/blob/main/docs/images/cicd-architecture.png)
