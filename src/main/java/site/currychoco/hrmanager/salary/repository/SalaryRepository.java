package site.currychoco.hrmanager.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.currychoco.hrmanager.salary.domain.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
