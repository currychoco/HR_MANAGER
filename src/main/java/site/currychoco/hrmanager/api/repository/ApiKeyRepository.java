package site.currychoco.hrmanager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.api.domain.ApiKey;

public interface ApiKeyRepository  extends JpaRepository<ApiKey, String> {
}
