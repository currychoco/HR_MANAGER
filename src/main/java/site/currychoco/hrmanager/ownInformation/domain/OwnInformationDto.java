package site.currychoco.hrmanager.ownInformation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.currychoco.hrmanager.position.domain.Position;
import site.currychoco.hrmanager.position.domain.PositionDto;

import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class OwnInformationDto {

    private Long no;
    private Long empNo;
    private String empName;
    private String empNameEn;
    private String gender;
    private String email;
    private String phone;
    private boolean allow;
    private Timestamp requestDate;
    private Timestamp allowDate;

    public boolean getAllow() {
        return allow;
    }

    public static OwnInformationDto fromEntity(OwnInformation ownInfo){
        if(ownInfo == null){
            return null;
        }
        return new OwnInformationDto(
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
