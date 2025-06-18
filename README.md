# HR_MANAGER
사내 인사 관리시스템 서비스

## Spec
- Spring Boot 2.7.18
- Java 21
- Maven Build
- Vue.js + Vite
- H2 Database
- JPA (Hibernate)

## Docker 환경 구성
아래 Container Registry 를 참고하여 테스트

### HR_MANAGER API 서비스
- https://github.com/users/currychoco/packages/container/package/hr-manager-api
``` bash
docker pull ghcr.io/currychoco/hr-manager-api:latest
docker run --platform=linux/amd64 -p 8080:8080 ghcr.io/currychoco/hr-manager-api:latest
```

### HR_MANAGER Frontend 서비스
- https://github.com/users/currychoco/packages/container/package/hr-manager-fe
``` bash
docker pull ghcr.io/currychoco/hr-manager-fe:latest
docker run --platform=linux/amd64 -p 80:80 ghcr.io/currychoco/hr-manager-fe:latest
```
