package site.currychoco.hrmanager.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.account.domain.Account;
import site.currychoco.hrmanager.account.domain.AccountDto;
import site.currychoco.hrmanager.account.service.AccountService;
import site.currychoco.hrmanager.auth.service.AccountAuthorityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;

    private final AccountAuthorityService accountAuthorityService;

    //---
    // page
    //---



    //---
    // API
    //---

    // 로그인
    @ResponseBody
    @PostMapping("/account/login")
    public boolean login(@RequestParam String id, @RequestParam String password, HttpServletRequest request){
        AccountDto result = accountService.login(id, password);

        if(result != null){

            HttpSession session = request.getSession();
            session.setAttribute("id", result.getId());
            session.setAttribute("empNo", result.getEmpNo());
            // 로그인 된 계정의 권한 확인하기
            List<String> grantList = accountAuthorityService.getAllGrant(result.getEmpNo());
            Collections.sort(grantList);

            if(grantList.size() != 0){
                session.setAttribute("grant", grantList);
            }
        }
        return result != null;
    }

    // 회원가입
    @ResponseBody
    @PostMapping("/account/join")
    public boolean join(@RequestBody Account account){
        AccountDto dto = AccountDto.fromEntity(account);
        AccountDto result = accountService.join(dto);
        return result != null;
    }

    /**
     * 인증 번호 이메일 전송
     */
    @ResponseBody
    @PostMapping("/account/send-identification-email")
    public void sendIdentificationEmail(@RequestParam(name = "empNo") long empNo) {
        accountService.sendIdentificationEmail(empNo);
    }

    /**
     * 입력한 키가 맞는지 확인
     */
    @ResponseBody
    @GetMapping("/account/validate-identification-email")
    public boolean checkIdentificationEmail(@RequestParam(name = "empNo") long empNo, @RequestParam(name = "key") String key) {
        return accountService.validateIdentificationEmail(empNo, key);
    }

    // 로그아웃
    @ResponseBody
    @PostMapping("/account/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
