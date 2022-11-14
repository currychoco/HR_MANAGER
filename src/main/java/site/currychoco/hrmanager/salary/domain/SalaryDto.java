package site.currychoco.hrmanager.salary.domain;

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
public class SalaryDto {

    private Long empNo;
    private long amount;

    public static SalaryDto fromEntity(Salary salary){
        if(salary == null){
            return null;
        }
        return new SalaryDto(
            salary.getEmpNo(),
            salary.getAmount()
        );
    }
}
