package site.currychoco.hrmanager.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.api.repository.ApiKeyAuthRepository;
import site.currychoco.hrmanager.api.repository.ApiKeyIpRepository;
import site.currychoco.hrmanager.api.repository.ApiKeyRepository;

@RequiredArgsConstructor
@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    private final ApiKeyAuthRepository apiKeyAuthRepository;

    private final ApiKeyIpRepository apiKeyIpRepository;

    /**
     *
     */
    public boolean isAuthorizedRequest(String key, String ip, String method, String uri){
        if(!apiKeyRepository.existsById(key)){
            return false;
        }
        if(!apiKeyIpRepository.existsByApiKeyAndIpAddress(key, ip)){
            return false;
        }
        if(!apiKeyAuthRepository.existsByApiKeyAndMethodAndUri(key, method, uri)){
            return false;
        }

        return true;
    }
}
