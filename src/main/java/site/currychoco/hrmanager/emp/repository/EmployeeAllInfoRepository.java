package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import java.util.List;

public interface EmployeeAllInfoRepository extends JpaRepository<EmployeeAllInfo, Long> {
    List<EmployeeAllInfo> findByEmpNameContaining(String empName);
}
