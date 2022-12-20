package site.currychoco.hrmanager.core.account.repository;

import site.currychoco.hrmanager.core.account.domain.EmailIdentification;

import java.util.Optional;

public interface EmailIdentificationRepository {

    Optional<EmailIdentification> findById(String email);

    EmailIdentification save(EmailIdentification identification);

    void check(EmailIdentification identification);
}
