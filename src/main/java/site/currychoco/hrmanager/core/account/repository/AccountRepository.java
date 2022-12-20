package site.currychoco.hrmanager.core.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.core.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByIdAndPassword(String id, String password);

    boolean existsById(String id);
}
