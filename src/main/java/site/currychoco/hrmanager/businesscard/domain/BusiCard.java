package site.currychoco.hrmanager.businesscard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "busi_card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BusiCard {
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
    @Column(name="dept_name")
    private String deptName;
    @Column(name="dept_name_en")
    private String deptNameEn;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_name_en")
    private String jobNameEn;
    @Column(name = "position_name")
    private String positionName;
    @Column(name = "position_name_en")
    private String positionNameEn;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "allow")
    private String allow;
    @Column(name="request_date")
    private Timestamp requestDate;
    @Column(name="allow_date")
    private Timestamp allowDate;

    public BusiCard(BusiCardDto dto){
        this.no = dto.getNo();
        this.empNo = dto.getEmpNo();
        this.empName = dto.getEmpName();
        this.empNameEn = dto.getEmpNameEn();
        this.deptName = dto.getDeptName();
        this.deptNameEn = dto.getDeptNameEn();
        this.jobName = dto.getJobName();
        this.jobNameEn = dto.getJobNameEn();
        this.positionName = dto.getPositionName();
        this.positionNameEn = dto.getPositionNameEn();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.allow = dto.getAllow();
        this.requestDate = dto.getRequestDate();
        this.allowDate = dto.getAllowDate();
    }

    public void modify(BusiCardDto dto){
        this.no = dto.getNo();
        this.empNo = dto.getEmpNo();
        this.empName = dto.getEmpName();
        this.empNameEn = dto.getEmpNameEn();
        this.deptName = dto.getDeptName();
        this.deptNameEn = dto.getDeptNameEn();
        this.jobName = dto.getJobName();
        this.jobNameEn = dto.getJobNameEn();
        this.positionName = dto.getPositionName();
        this.positionNameEn = dto.getPositionNameEn();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.allow = dto.getAllow();
        this.requestDate = dto.getRequestDate();
        this.allowDate = dto.getAllowDate();
    }
}
