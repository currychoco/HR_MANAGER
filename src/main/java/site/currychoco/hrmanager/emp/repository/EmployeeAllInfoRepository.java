package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import java.util.List;

public interface EmployeeAllInfoRepository extends JpaRepository<EmployeeAllInfo, Long> {

    @Query (value = "SELECT\n" +
            "emp.emp_no,\n" +
            "emp.address1,\n" +
            "emp.address2,\n" +
            "emp.dept_code,\n" +
            "dept.dept_name,\n" +
            "dept.dept_name_en,\n" +
            "emp.email,\n" +
            "emp.emp_name,\n" +
            "emp.emp_name_en,\n" +
            "emp.gender,\n" +
            "acc.id,\n" +
            "emp.job_code,\n" +
            "j.job_name,\n" +
            "j.job_name_en,\n" +
            "emp.phone,\n" +
            "emp.position_code,\n" +
            "p.position_name,\n" +
            "p.position_name_en,\n" +
            "emp.start_date,\n" +
            "emp.zip_code\n" +
            "FROM \n" +
            "employee emp \n" +
            "INNER JOIN job j ON emp.job_code = j.job_code\n" +
            "INNER JOIN department dept ON emp.dept_code = dept.dept_code\n" +
            "INNER JOIN account acc ON emp.emp_no = acc.emp_no\n" +
            "INNER JOIN position p ON emp.position_code = p.position_code", nativeQuery = true)
    List<EmployeeAllInfo> findByEmpNameContaining(String empName);
}
