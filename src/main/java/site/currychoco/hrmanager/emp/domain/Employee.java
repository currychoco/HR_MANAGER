package site.currychoco.hrmanager.emp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Employee {
    @Id
    @Column(name = "emp_no")
    private Long empNo;
    @Column(name = "dept_code")
    private String deptCode;
    @Column(name = "job_code")
    private String jobCode;
    @Column(name = "position_code")
    private String positionCode;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_name_en")
    private String empNameEn;
    @Column(name = "gender")
    private String gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name="start_date")
    private Timestamp startDate;

    public Employee(EmployeeDto dto){
        this.empNo = dto.getEmpNo();
        this.deptCode = dto.getDeptCode();
        this.jobCode = dto.getJobCode();
        this.positionCode = dto.getPositionCode();
        this.empName = dto.getEmpName();
        this.empNameEn = dto.getEmpNameEn();
        this.gender = dto.getGender();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.startDate = dto.getStartDate();
    }
}
