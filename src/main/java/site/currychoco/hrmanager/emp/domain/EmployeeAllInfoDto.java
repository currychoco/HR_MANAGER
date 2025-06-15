package site.currychoco.hrmanager.emp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EmployeeAllInfoDto {

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
    private String id = "-";
    private String address1;
    private String address2;
    private String zipCode;
}
