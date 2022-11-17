package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfoDto;
import site.currychoco.hrmanager.job.domain.Job;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value="select \n" +
            "\te.emp_no,\n" +
            "    e.emp_name,\n" +
            "    e.emp_name_en,\n" +
            "    d.dept_name,\n" +
            "    j.job_name,\n" +
            "    p.position_name,\n" +
            "    e.gender,\n" +
            "    e.email,\n" +
            "    e.phone,\n" +
            "    e.start_date,\n" +
            "    a.id\n" +
            "from employee e\n" +
            "join department d\n" +
            "on e.dept_code = d.dept_code\n" +
            "join job j\n" +
            "on e.job_code = j.job_code\n" +
            "join `position` p\n" +
            "on e.position_code = p.position_code\n" +
            "join `account` a\n" +
            "on e.emp_no = a.emp_no\n" +
            "where e.emp_no = :empNo;", nativeQuery = true)
    EmployeeAllInfoDto getAllByEmpNo(Long empNo);

}
