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
@Table(name = "authority")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Authority {
    @Id
    @Column(name = "auth_code")
    private String authCode;
    @Column(name = "auth_name")
    private String authName;

    public Authority(AuthorityDto auth){
        this.authCode = auth.getAuthCode();
        this.authName = auth.getAuthName();
    }
}
