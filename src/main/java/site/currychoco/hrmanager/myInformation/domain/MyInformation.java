package site.currychoco.hrmanager.myInformation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "my_information")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MyInformation {
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
    private String allow;
    @Column(name="request_date")
    private Timestamp requestDate;
    @Column(name="allow_date")
    private Timestamp allowDate;
    @Column(name="address1")
    private String address1;
    @Column(name="address2")
    private String address2;
    @Column(name="zip_code")
    private String zipCode;

    public MyInformation(MyInformationDto dto){
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
        this.address1 = dto.getAddress1();
        this.address2 = dto.getAddress2();
        this.zipCode = dto.getZipCode();
    }

    public void modify(MyInformationDto dto){
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
        this.address1 = dto.getAddress1();
        this.address2 = dto.getAddress2();
        this.zipCode = dto.getZipCode();
    }
}
