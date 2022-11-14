package site.currychoco.hrmanager.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.auth.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
