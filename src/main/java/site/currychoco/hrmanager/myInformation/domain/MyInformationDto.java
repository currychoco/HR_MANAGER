package site.currychoco.hrmanager.myInformation.domain;

import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


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

    public String getRequestDateToString() {
        if(requestDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.format(requestDate);
        }
        return null;
    }

    public String getAllowDateToString() {
        if(allowDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return sdf.format(allowDate);
        }
        return null;
    }

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
