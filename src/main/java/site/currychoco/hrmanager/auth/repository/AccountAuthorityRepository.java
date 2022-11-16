package site.currychoco.hrmanager.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.auth.domain.AccountAuthority;

import java.util.List;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, Long> {
    List<AccountAuthority> findAllByEmpNo(Long emp_no);
}
