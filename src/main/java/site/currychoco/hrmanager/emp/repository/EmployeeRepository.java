package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.emp.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
