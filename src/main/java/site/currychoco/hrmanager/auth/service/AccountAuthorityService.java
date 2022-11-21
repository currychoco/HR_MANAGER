package site.currychoco.hrmanager.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.auth.domain.AccountAuthority;
import site.currychoco.hrmanager.auth.domain.AccountAuthorityDto;
import site.currychoco.hrmanager.auth.repository.AccountAuthorityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountAuthorityService {

    private final AccountAuthorityRepository accountAuthorityRepository;

    /**
     * empNo 을 입력받아 권한 리스트 출력
     */
    public List<String> getAllGrant(Long empNo){
        List<AccountAuthority> accountAuthorityList = accountAuthorityRepository.findAllByEmpNo(empNo);

        /*
        List<String> strList = accountAuthorityList.stream()
                .map(acc -> acc.getAuthCode())
                .collect(Collectors.toList());
        */

        List<String> strList = new ArrayList<>();
        if(!accountAuthorityList.isEmpty()) {
            for(AccountAuthority acc : accountAuthorityList) {
                strList.add(acc.getAuthCode());
            }
        }
        return strList;
    }

    /**
     * AccountAuthority 추가
     */
    public void addAccountAuthority(AccountAuthorityDto accountAuthorityDto){
        AccountAuthority accountAuthority = new AccountAuthority(accountAuthorityDto);
        accountAuthorityRepository.save(accountAuthority);
    }
}
