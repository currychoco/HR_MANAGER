package site.currychoco.hrmanager.emp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EmployeeDto {

    private Long empNo;
    private String deptCode;
    private String jobCode;
    private String positionCode;
    private String empName;
    private String empNameEn;
    private String gender;
    private String email;
    private String phone;

    public static EmployeeDto fromEntity(Employee emp){
        if(emp == null){
            return null;
        }
        return new EmployeeDto(
                emp.getEmpNo(),
                emp.getDeptCode(),
                emp.getJobCode(),
                emp.getPositionCode(),
                emp.getEmpName(),
                emp.getEmpNameEn(),
                emp.getGender(),
                emp.getEmail(),
                emp.getPhone()
        );
    }
}
