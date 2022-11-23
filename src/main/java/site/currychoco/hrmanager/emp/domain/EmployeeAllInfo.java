package site.currychoco.hrmanager.emp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAllInfo {
    @Id
    private Long empNo;
    private String empName;
    private String empNameEn;
    private String deptCode;
    private String deptName;
    private String deptNameEn;
    private String jobCode;
    private String jobName;
    private String jobNameEn;
    private String positionCode;
    private String positionName;
    private String positionNameEn;
    private String gender;
    private String email;
    private String phone;
    private Timestamp startDate;
    private String id;

}
