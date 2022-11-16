package site.currychoco.hrmanager.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.auth.domain.AccountAuthority;
import site.currychoco.hrmanager.auth.repository.AccountAuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountAuthorityService {

    @Autowired
    private AccountAuthorityRepository accountAuthorityRepository;

    // emp_no을 입력받아 권한 리스트 출력
    public List<String> getAllGrant(Long emp_no){
        List<AccountAuthority> accountAuthorityList = accountAuthorityRepository.findAllByEmpNo(emp_no);
        List<String> strList = new ArrayList<>();
        if(accountAuthorityList.size() != 0){
            for(AccountAuthority acc : accountAuthorityList){
                strList.add(acc.getAuthCode());
            }
            return strList;
        }else{
            return null;
        }
    }
}
