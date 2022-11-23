package site.currychoco.hrmanager.ownInformation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "own_information")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class OwnInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no")
    private Long no;
    @Column(name="emp_no")
    private Long empNo;
    @Column(name="emp_name")
    private String empName;
    @Column(name="emp_name_en")
    private String empNameEn;
    @Column(name="gender")
    private String gender;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="allow")
    private boolean allow;
    @Column(name="request_date")
    private Timestamp requestDate;
    @Column(name="allow_date")
    private Timestamp allowDate;

    public OwnInformation(OwnInformationDto dto){
        this.no = dto.getNo();
        this.empNo = dto.getEmpNo();
        this.empName = dto.getEmpName();
        this.empNameEn = dto.getEmpNameEn();
        this.gender = dto.getGender();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.allow = dto.getAllow();
        this.requestDate = dto.getRequestDate();
        this.allowDate = dto.getAllowDate();
    }

    public boolean getAllow() {
        return this.allow;
    }
}
