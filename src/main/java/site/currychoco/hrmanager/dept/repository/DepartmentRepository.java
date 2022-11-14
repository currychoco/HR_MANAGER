package site.currychoco.hrmanager.dept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.dept.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Query(value = "select * from department d inner join employee e on d.dept_code = e.dept_code where e.emp_no = :empNo", nativeQuery = true)
    Department findDepartmentByEmpNo(Long empNo);
}
