package site.currychoco.hrmanager.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.department.domain.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Query(value = "select * from department d inner join employee e on d.dept_code = e.dept_code where e.emp_no = :empNo", nativeQuery = true)
    Department findDepartmentByEmpNo(Long empNo);

    @Query(value = "select * from department where dept_code like CONCAT('%',:data,'%') or dept_name like CONCAT('%',:data,'%')", nativeQuery = true)
    List<Department> findDepartmentByDeptCodeOrDeptName(String data);
}
