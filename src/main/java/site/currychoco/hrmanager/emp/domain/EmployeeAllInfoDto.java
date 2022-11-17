package site.currychoco.hrmanager.emp.domain;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAllInfoDto {
    private Long empNo;
    private String empName;
    private String empNameEn;
    private String deptName;
    private String jobName;
    private String positionName;
    private String gender;
    private String email;
    private String phone;
    private Timestamp startDate;
    private String id;

}
