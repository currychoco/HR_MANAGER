package site.currychoco.hrmanager.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AuthorityDto {
    private String authCode;
    private String authName;

    public static AuthorityDto fromEntity(Authority auth){
        if(auth == null){
            return null;
        }
        return new AuthorityDto(
                auth.getAuthCode(),
                auth.getAuthName()
        );
    }
}
