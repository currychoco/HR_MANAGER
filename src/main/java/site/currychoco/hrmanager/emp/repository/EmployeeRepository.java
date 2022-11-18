package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.job.domain.Job;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
