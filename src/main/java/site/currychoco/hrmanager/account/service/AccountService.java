package site.currychoco.hrmanager.account.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import site.currychoco.hrmanager.account.domain.Account;
import site.currychoco.hrmanager.account.domain.AccountDto;
import site.currychoco.hrmanager.account.domain.EmailIdentification;
import site.currychoco.hrmanager.account.repository.AccountRepository;
import site.currychoco.hrmanager.account.repository.EmailIdentificationRepository;
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

    /**
     * 로그인
     */
    public AccountDto login(String id, String password){
        final String hexedPassword = DigestUtils.sha256Hex(password);
        Account selectedAcc = accountRepository.findAccountByIdAndPassword(id, hexedPassword);
        if(selectedAcc == null){
            return null;
        } else{
            AccountDto accDto = AccountDto.fromEntity(selectedAcc);
            return accDto;
        }
    }

    /**
     * 회원가입
     */
    public void join(AccountDto accountDto){
        Employee emp = employeeRepository.findById(accountDto.getEmpNo()).orElseThrow(()->new BadRequestException("해당 사원이 존재하지 않습니다"));
        EmailIdentification email = emailIdentificationRepository.findById(emp.getEmail()).orElseThrow(()->new BadRequestException("이메일 인증을 시도해 주십시오"));
        if(accountRepository.existsById(accountDto.getId())){
            throw new BadRequestException("사용할 수 없는 아이디입니다");
        }
        if(email.isChecked()){
            Account account = new Account(accountDto);
            accountRepository.save(account);
        }else{
            throw new BadRequestException("이메일 인증을 시도해 주십시오");
        }
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
            emailIdentificationRepository.check(identification);
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

    /**
     * 아이디 중복 체크
     */
    public boolean checkDuplicateId(String id){
        return !accountRepository.existsById(id);
    }

    /**
     * 아이디 유무 체크
     */
    public boolean checkIsId(Long empNo){
        return accountRepository.existsById(empNo);
    }

}
