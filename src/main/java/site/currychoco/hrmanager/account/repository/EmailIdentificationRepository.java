package site.currychoco.hrmanager.account.repository;

import site.currychoco.hrmanager.account.domain.EmailIdentification;

import java.util.Optional;

public interface EmailIdentificationRepository {

    Optional<EmailIdentification> findById(String email);

    EmailIdentification save(EmailIdentification identification);
}
