package site.currychoco.hrmanager.auth.domain;

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
public class AccountAuthority {
    @Id
    @Column(name = "auth_no")
    private Long authNo;
    @Column(name = "emp_no")
    private Long empNo;
    @Column(name = "auth_code")
    private String authCode;
}
