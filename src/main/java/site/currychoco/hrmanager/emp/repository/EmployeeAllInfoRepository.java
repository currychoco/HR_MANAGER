package site.currychoco.hrmanager.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeAllInfoRepository extends JpaRepository<EmployeeAllInfo, Long> {

    @Query (value =
            """
            SELECT
                emp.emp_no AS empNo,
                emp.address1 AS address1,
                emp.address2 AS address2,
                emp.dept_code AS deptCode,
                dept.dept_name AS deptName,
                dept.dept_name_en AS deptNameEn,
                emp.email AS email,
                emp.emp_name AS empName,
                emp.emp_name_en AS empNameEn,
                emp.gender AS gender,
                acc.id AS accountId,
                emp.job_code AS jobCode,
                j.job_name AS jobName,
                j.job_name_en AS jobNameEn,
                emp.phone AS phone,
                emp.position_code AS positionCode,
                p.position_name AS positionName,
                p.position_name_en AS positionNameEn,
                emp.start_date AS startDate,
                emp.zip_code AS zipCode
            FROM employee emp
                INNER JOIN job j ON emp.job_code = j.job_code
                INNER JOIN department dept ON emp.dept_code = dept.dept_code
                INNER JOIN account acc ON emp.emp_no = acc.emp_no
                INNER JOIN position p ON emp.position_code = p.position_code
            WHERE emp.emp_no = :empNo
            """, nativeQuery = true)
    Optional<EmployeeAllInfo> findByEmpNo(Long empNo);

    @Query (value =
            """
            SELECT
                emp.emp_no AS empNo,
                emp.address1 AS address1,
                emp.address2 AS address2,
                emp.dept_code AS deptCode,
                dept.dept_name AS deptName,
                dept.dept_name_en AS deptNameEn,
                emp.email AS email,
                emp.emp_name AS empName,
                emp.emp_name_en AS empNameEn,
                emp.gender AS gender,
                acc.id AS accountId,
                emp.job_code AS jobCode,
                j.job_name AS jobName,
                j.job_name_en AS jobNameEn,
                emp.phone AS phone,
                emp.position_code AS positionCode,
                p.position_name AS positionName,
                p.position_name_en AS positionNameEn,
                emp.start_date AS startDate,
                emp.zip_code AS zipCode
            FROM employee emp
                INNER JOIN job j ON emp.job_code = j.job_code
                INNER JOIN department dept ON emp.dept_code = dept.dept_code
                INNER JOIN account acc ON emp.emp_no = acc.emp_no
                INNER JOIN position p ON emp.position_code = p.position_code
            """, nativeQuery = true)
    List<EmployeeAllInfo> findByEmpNameContaining(String empName);
}
