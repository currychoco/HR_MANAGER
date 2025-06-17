package site.currychoco.hrmanager.emp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.currychoco.hrmanager.core.account.domain.Account;
import site.currychoco.hrmanager.department.domain.Department;
import site.currychoco.hrmanager.job.domain.Job;
import site.currychoco.hrmanager.position.domain.Position;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EmployeeAllInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private Long empNo;
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
    @Column(name="address1")
    private String address1;
    @Column(name="address2")
    private String address2;
    @Column(name="zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "dept_code", insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_code", insertable = false, updatable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "position_code", insertable = false, updatable = false)
    private Position position;

    @OneToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Account account;
}
