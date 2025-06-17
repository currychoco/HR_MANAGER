package site.currychoco.hrmanager.emp.dto;

import lombok.*;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String address1;
    private String address2;
    private String zipCode;
    private String accountId;

    public static EmployeeAllInfoDto fromEntity(EmployeeAllInfo origin) {
        return EmployeeAllInfoDto.builder()
                .empNo(origin.getEmpNo())
                .empName(origin.getEmpName())
                .empNameEn(origin.getEmpNameEn())
                .deptCode(origin.getDepartment().getDeptCode())
                .deptName(origin.getDepartment().getDeptName())
                .deptNameEn(origin.getDepartment().getDeptNameEn())
                .jobCode(origin.getJob().getJobCode())
                .jobName(origin.getJob().getJobName())
                .jobNameEn(origin.getJob().getJobNameEn())
                .positionCode(origin.getPosition().getPositionCode())
                .positionName(origin.getPosition().getPositionName())
                .positionNameEn(origin.getPosition().getPositionNameEn())
                .gender(origin.getGender())
                .email(origin.getEmail())
                .phone(origin.getPhone())
                .startDate(origin.getStartDate())
                .address1(origin.getAddress1())
                .address2(origin.getAddress2())
                .zipCode(origin.getZipCode())
                .accountId(origin.getAccount().getId())
                .build();
    }
}
