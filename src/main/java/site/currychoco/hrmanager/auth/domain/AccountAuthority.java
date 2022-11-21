package site.currychoco.hrmanager.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "account_authority")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AccountAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_no")
    private Long authNo;
    @Column(name = "emp_no")
    private Long empNo;
    @Column(name = "auth_code")
    private String authCode;

    public AccountAuthority(AccountAuthorityDto accountAuthorityDto) {
        this.empNo = accountAuthorityDto.getEmpNo();
        this.authCode = accountAuthorityDto.getAuthCode();
    }
}
