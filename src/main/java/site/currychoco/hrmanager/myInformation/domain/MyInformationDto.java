package site.currychoco.hrmanager.myInformation.domain;

import lombok.*;

import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MyInformationDto {

    private Long no;
    private Long empNo;
    private String empName;
    private String empNameEn;
    private String gender;
    private String email;
    private String phone;
    private String allow;
    private Timestamp requestDate;
    private Timestamp allowDate;

    public static MyInformationDto fromEntity(MyInformation ownInfo){
        if(ownInfo == null){
            return null;
        }
        return new MyInformationDto(
            ownInfo.getNo(),
            ownInfo.getEmpNo(),
            ownInfo.getEmpName(),
            ownInfo.getEmpNameEn(),
            ownInfo.getGender(),
            ownInfo.getEmail(),
            ownInfo.getPhone(),
            ownInfo.getAllow(),
            ownInfo.getRequestDate(),
            ownInfo.getAllowDate()
        );
    }
}
