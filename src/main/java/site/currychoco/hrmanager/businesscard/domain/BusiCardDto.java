package site.currychoco.hrmanager.businesscard.domain;

import lombok.*;
import site.currychoco.hrmanager.emp.domain.EmployeeAllInfo;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BusiCardDto {
    private Long no;
    private Long empNo;
    private String empName;
    private String empNameEn;
    private String deptName;
    private String deptNameEn;
    private String jobName;
    private String jobNameEn;
    private String positionName;
    private String positionNameEn;
    private String email;
    private String phone;
    private String allow;
    private Timestamp requestDate;
    private Timestamp allowDate;

    public static BusiCardDto fromEntity(BusiCard busiCard){
        if(busiCard == null){
            return null;
        }
        return new BusiCardDto(
                busiCard.getNo(),
                busiCard.getEmpNo(),
                busiCard.getEmpName(),
                busiCard.getEmpNameEn(),
                busiCard.getDeptName(),
                busiCard.getDeptNameEn(),
                busiCard.getJobName(),
                busiCard.getJobNameEn(),
                busiCard.getPositionName(),
                busiCard.getPositionNameEn(),
                busiCard.getEmail(),
                busiCard.getPhone(),
                busiCard.getAllow(),
                busiCard.getRequestDate(),
                busiCard.getAllowDate()
        );
    }

    public static BusiCardDto fromEntity(EmployeeAllInfo empInfo){
        if(empInfo == null){
            return null;
        }
        return new BusiCardDto(
                null,
                empInfo.getEmpNo(),
                empInfo.getEmpName(),
                empInfo.getEmpNameEn(),
                empInfo.getDeptName(),
                empInfo.getDeptNameEn(),
                empInfo.getJobName(),
                empInfo.getJobNameEn(),
                empInfo.getPositionName(),
                empInfo.getPositionNameEn(),
                empInfo.getEmail(),
                empInfo.getPhone(),
                "R",
                new Timestamp(System.currentTimeMillis()),
                null
        );
    }
}


