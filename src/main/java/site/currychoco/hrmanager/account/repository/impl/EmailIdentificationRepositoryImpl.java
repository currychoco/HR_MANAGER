package site.currychoco.hrmanager.account.repository.impl;

import org.springframework.stereotype.Component;
import site.currychoco.hrmanager.account.domain.EmailIdentification;
import site.currychoco.hrmanager.account.repository.EmailIdentificationRepository;
import site.currychoco.hrmanager.core.exception.BadRequestException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class EmailIdentificationRepositoryImpl implements EmailIdentificationRepository {

    private Map<String, EmailIdentification> store = new HashMap<>();

    @Override
    public Optional<EmailIdentification> findById(String email) {
        return Optional.ofNullable(store.get(email));
    }


    @Override
    public EmailIdentification save(EmailIdentification identification) {
        store.put(identification.getEmail(), identification);
        return identification;
    }

    @Override
    public void check(EmailIdentification identification) {
        identification.setChecked(true);
        save(identification);
    }
}
