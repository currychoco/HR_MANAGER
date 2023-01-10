package site.currychoco.hrmanager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.api.domain.ApiKeyIp;

public interface ApiKeyIpRepository extends JpaRepository<ApiKeyIp, Long> {
    boolean existsByApiKeyAndIpAddress(String key, String ip);
}
