package site.currychoco.hrmanager.salary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.currychoco.hrmanager.job.domain.JobDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salary")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Salary {
    @Id
    @Column(name = "emp_no")
    private Long empNo;
    @Column(name = "amount")
    private long amount;

    public Salary(SalaryDto salaryDto){
        this.empNo = salaryDto.getEmpNo();
        this.amount = salaryDto.getAmount();
    }

    public void modify(SalaryDto dto){
        this.amount = dto.getAmount();
    }
}
