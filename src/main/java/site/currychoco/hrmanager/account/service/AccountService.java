package site.currychoco.hrmanager.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.account.domain.Account;
import site.currychoco.hrmanager.account.domain.AccountDto;
import site.currychoco.hrmanager.account.domain.EmailIdentification;
import site.currychoco.hrmanager.account.repository.AccountRepository;
import site.currychoco.hrmanager.account.repository.EmailIdentificationRepository;
import site.currychoco.hrmanager.account.repository.impl.EmailIdentificationRepositoryImpl;
import site.currychoco.hrmanager.core.exception.BadRequestException;
import site.currychoco.hrmanager.emp.domain.Employee;
import site.currychoco.hrmanager.emp.repository.EmployeeRepository;
import site.currychoco.hrmanager.trdparty.email.util.EmailSendUtil;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final EmployeeRepository employeeRepository;

    private final EmailIdentificationRepository emailIdentificationRepository;

    private final EmailSendUtil emailSendUtil;

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

    public void sendIdentificationEmail(long empNo) {
        Employee emp = employeeRepository.findById(empNo).orElseThrow(() -> new BadRequestException("해당 사번은 유효하지 않습니다"));
        String key = emailSendUtil.sendSimpleMessage(emp.getEmail());

        emailIdentificationRepository.save(new EmailIdentification(emp.getEmail(), key));
    }

    public boolean validateIdentificationEmail(long empNo, String key) {
        Employee emp = employeeRepository.findById(empNo).orElseThrow(() -> new BadRequestException("해당 사번은 유효하지 않습니다"));
        EmailIdentification identification = emailIdentificationRepository.findById(emp.getEmail()).orElseThrow(() -> new BadRequestException("인증 메일 발송을 다시 시도해 주세요"));
        String createdKey = identification.getKey();

        if(createdKey.equals(key)) { // 인증 이메일 발송 시에 만들어진 키(createdKey) 와 사용자가 입력한 인증 키(key) 가 동일한 지 체크 
            return true;
        }
        return false;
    }

    /**
     * 사번을 통한 아이디 검색
     */
    public AccountDto getAccountByEmpNo(Long empNo){
        Account account = accountRepository.findById(empNo).orElseThrow();
        AccountDto dto = AccountDto.fromEntity(account);
        return dto;
    }
}
