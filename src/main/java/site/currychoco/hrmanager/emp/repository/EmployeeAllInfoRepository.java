package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import java.util.List;

public interface EmployeeAllInfoRepository extends JpaRepository<EmployeeAllInfo, Long> {
    @Query(value="select e.emp_no, e.emp_name, e.emp_name_en, d.dept_name ,j.job_name, p.position_name, e.gender, e.email, e.phone, e.start_date, a.id " +
            "from employee e join department d on e.dept_code = d.dept_code join job j on e.job_code = j.job_code " +
            "join `position` p on e.position_code = p.position_code join `account` a on e.emp_no = a.emp_no where e.emp_no = ?", nativeQuery = true)
    EmployeeAllInfo getAllByEmpNo(Long empNo);

    @Query(value="select e.emp_no, e.emp_name, e.emp_name_en, d.dept_name ,j.job_name, p.position_name, e.gender, e.email, e.phone, e.start_date, a.id " +
            "from employee e join department d on e.dept_code = d.dept_code join job j on e.job_code = j.job_code " +
            "join `position` p on e.position_code = p.position_code left join `account` a on e.emp_no = a.emp_no where e.emp_no = ?", nativeQuery = true)
    List<EmployeeAllInfo> findAllByEmpNo(Long empNo);

    @Query(value="select e.emp_no, e.emp_name, e.emp_name_en, d.dept_name ,j.job_name, p.position_name, e.gender, e.email, e.phone, e.start_date, a.id " +
            "from employee e join department d on e.dept_code = d.dept_code join job j on e.job_code = j.job_code " +
            "join `position` p on e.position_code = p.position_code left join `account` a on e.emp_no = a.emp_no where e.emp_name = ?", nativeQuery = true)
    List<EmployeeAllInfo> findAllByEmpName(String name);

}
