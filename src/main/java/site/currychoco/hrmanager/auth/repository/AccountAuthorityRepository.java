package site.currychoco.hrmanager.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.auth.domain.AccountAuthority;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, Long> {
}
