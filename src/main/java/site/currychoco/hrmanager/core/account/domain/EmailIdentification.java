package site.currychoco.hrmanager.core.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class EmailIdentification {

    private String email;
    private String key;

    @Setter
    private boolean checked;

    public EmailIdentification(String email, String key) {
        this.email = email;
        this.key = key;
        this.checked = false;
    }

}
