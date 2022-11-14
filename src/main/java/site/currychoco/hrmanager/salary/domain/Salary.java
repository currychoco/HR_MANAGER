package site.currychoco.hrmanager.salary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_authority")
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
}
