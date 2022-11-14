package site.currychoco.hrmanager.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AccountAuthorityDto {
    private Long authNo;
    private Long empNo;
    private String authCode;

    public static AccountAuthorityDto fromEntity(AccountAuthority accauth){
        if(accauth == null){
            return null;
        }
        return new AccountAuthorityDto(
                accauth.getAuthNo(),
                accauth.getEmpNo(),
                accauth.getAuthCode()
        );
    }
}
