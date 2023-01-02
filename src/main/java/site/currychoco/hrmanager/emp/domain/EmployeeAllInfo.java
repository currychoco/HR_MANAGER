package site.currychoco.hrmanager.emp.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "employee_all_info")
@NoArgsConstructor
@Getter
@ToString
public class EmployeeAllInfo {
    @Id
    @Column(name = "emp_no")
    private Long empNo;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_name_en")
    private String empNameEn;
    @Column(name = "dept_code")
    private String deptCode;
    @Column(name = "dept_name")
    private String deptName;
    @Column(name = "dept_name_en")
    private String deptNameEn;
    @Column(name = "job_code")
    private String jobCode;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_name_en")
    private String jobNameEn;
    @Column(name = "position_code")
    private String positionCode;
    @Column(name = "position_name")
    private String positionName;
    @Column(name = "position_name_en")
    private String positionNameEn;
    @Column(name = "gender")
    private String gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "id")
    private String id = "-";
    @Column(name = "address")
    private String address;

    public String getStartDateToString() {
        if(startDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.format(startDate);
        }
        return null;
    }

}
