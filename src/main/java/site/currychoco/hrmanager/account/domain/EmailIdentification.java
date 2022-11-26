package site.currychoco.hrmanager.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailIdentification {

    private String email;
    private String key;
}
