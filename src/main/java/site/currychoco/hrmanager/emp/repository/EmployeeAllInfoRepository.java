package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeAllInfoRepository extends JpaRepository<EmployeeAllInfo, Long> {

    Optional<EmployeeAllInfo> findByEmpNo(Long empNo);

    List<EmployeeAllInfo> findByEmpNameContaining(String empName);
}
