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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    // 로그인
    @PostMapping("/account/login")
    public boolean login(@RequestParam String id, @RequestParam String password, HttpServletRequest request){
        AccountDto result = accountService.login(id, password);

        if(result != null){
            HttpSession session = request.getSession();
            session.setAttribute("id", result.getId());
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
