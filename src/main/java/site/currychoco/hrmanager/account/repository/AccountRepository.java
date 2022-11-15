package site.currychoco.hrmanager.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByIdAndPassword(String id, String password);
}
