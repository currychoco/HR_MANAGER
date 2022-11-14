package site.currychoco.hrmanager.dept.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Department {
    @Id
    @Column(name = "dept_code")
    private String deptCode;
    @Column(name = "upper_dept_code")
    private String upperDeptCode;
    @Column(name = "head_emp_no")
    private Integer headEmpNo;
    @Column(name = "dept_name")
    private String deptName;
    @Column(name = "dept_name_en")
    private String deptNameEn;
    @Column(name = "dept_level")
    private int deptLevel;
}
