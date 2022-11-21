package site.currychoco.hrmanager.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.auth.domain.Authority;
import site.currychoco.hrmanager.auth.domain.AuthorityDto;
import site.currychoco.hrmanager.auth.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    /**
     * 모든 권한 출력
     */
    public List<AuthorityDto> getAllAuthority(){
        List<AuthorityDto> dtoList = new ArrayList<>();

        List<Authority> authList = authorityRepository.findAll();

        for(Authority auth : authList){
            dtoList.add(AuthorityDto.fromEntity(auth));
        }

        return dtoList;
    }
}
