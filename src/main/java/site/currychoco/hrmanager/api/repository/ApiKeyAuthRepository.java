package site.currychoco.hrmanager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.api.domain.ApiKeyAuth;

public interface ApiKeyAuthRepository  extends JpaRepository<ApiKeyAuth, Long> {
    boolean existsByApiKeyAndMethodAndUri(String key, String method, String uri);
}
