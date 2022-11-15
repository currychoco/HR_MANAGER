package site.currychoco.hrmanager.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.account.domain.Account;
import site.currychoco.hrmanager.account.domain.AccountDto;
import site.currychoco.hrmanager.account.repository.AccountRepository;

@RequiredArgsConstructor
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    // 로그인
    public AccountDto login(String id, String password){
        Account selectedAcc = accountRepository.findAccountByIdAndPassword(id, password);
        if(selectedAcc == null){
            return null;
        } else{
            AccountDto accDto = AccountDto.fromEntity(selectedAcc);
            return accDto;
        }
    }

    // 회원가입
    public AccountDto join(AccountDto accountDto){
        Account account = new Account(accountDto);
        Account savedAccount = accountRepository.save(account);
        AccountDto result = AccountDto.fromEntity(savedAccount);
        return result;
    }


}
