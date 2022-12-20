package site.currychoco.hrmanager.core.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.currychoco.hrmanager.core.account.domain.Account;
import site.currychoco.hrmanager.core.account.domain.AccountDto;
import site.currychoco.hrmanager.core.account.service.AccountService;
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
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 로그인
    @ResponseBody
    @PostMapping("/account/login")
    public boolean login(@RequestParam String id, @RequestParam String password, HttpServletRequest request){

        sleep();

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
    public void join(@RequestBody Account account){

        sleep();

        AccountDto dto = AccountDto.fromEntity(account);
        accountService.join(dto);
    }

    /**
     * 인증 번호 이메일 전송
     */
    @ResponseBody
    @PostMapping("/account/send-identification-email")
    public void sendIdentificationEmail(@RequestParam(name = "empNo") long empNo) {

        sleep();

        accountService.sendIdentificationEmail(empNo);
    }

    /**
     * 입력한 키가 맞는지 확인
     */
    @ResponseBody
    @GetMapping("/account/validate-identification-email")
    public boolean checkIdentificationEmail(@RequestParam(name = "empNo") long empNo, @RequestParam(name = "key") String key) {

        sleep();

        return accountService.validateIdentificationEmail(empNo, key);
    }

    // 로그아웃
    @ResponseBody
    @PostMapping("/account/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    /**
     * 아이디 중복 체크
     */
    @ResponseBody
    @GetMapping("/account/duplicate-id")
    public boolean checkDuplicateId(@RequestParam String id){

        sleep();

        return accountService.checkDuplicateId(id);
    }

    /**
     * 회원가입 유무 확인
     */
    @ResponseBody
    @GetMapping("/account/check-is-id")
    public boolean checkIsId(@RequestParam Long empNo){

        sleep();

        return accountService.checkIsId(empNo);
    }

}
