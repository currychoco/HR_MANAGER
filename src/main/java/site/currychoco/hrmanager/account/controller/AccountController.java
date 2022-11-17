package site.currychoco.hrmanager.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.currychoco.hrmanager.account.domain.Account;
import site.currychoco.hrmanager.account.domain.AccountDto;
import site.currychoco.hrmanager.account.service.AccountService;
import site.currychoco.hrmanager.auth.service.AccountAuthorityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountAuthorityService accountAuthorityService;

    // 로그인
    @PostMapping("/account/login")
    public boolean login(@RequestParam String id, @RequestParam String password, HttpServletRequest request){
        AccountDto result = accountService.login(id, password);

        if(result != null){

            HttpSession session = request.getSession();
            session.setAttribute("id", result.getId());
            session.setAttribute("empNo", result.getEmpNo());
            // 로그인 된 계정의 권한 확인하기
            List<String> grantList = accountAuthorityService.getAllGrant(result.getEmpNo());

            if(grantList != null){
                session.setAttribute("grant", grantList);
            }
        }
        return result != null;
    }

    // 회원가입
    @PostMapping("/account/join")
    public boolean join(@RequestBody Account account){
        AccountDto dto = AccountDto.fromEntity(account);
        AccountDto result = accountService.join(dto);
        return result != null;
    }

    // 로그아웃
    @PostMapping("/account/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
