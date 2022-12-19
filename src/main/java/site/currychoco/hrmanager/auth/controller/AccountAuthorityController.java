package site.currychoco.hrmanager.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.auth.domain.AccountAuthorityDto;
import site.currychoco.hrmanager.auth.service.AccountAuthorityService;
import site.currychoco.hrmanager.core.annotation.CheckAuthority;
import site.currychoco.hrmanager.core.exception.BadRequestException;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class AccountAuthorityController {

    private final AccountAuthorityService accountAuthorityService;

    // ---
    // page
    // ---

    // ---
    // api
    // ---

    @CheckAuthority(authCode = "g000009")
    @ResponseBody
    @PostMapping("/account-authority/add")
    public void addAccountAuthority(@RequestBody AccountAuthorityDto accountAuthorityDto, HttpSession session){
        if((Long)session.getAttribute("empNo") == accountAuthorityDto.getEmpNo()){
            throw new BadRequestException("본인에게 권한을 부여할 수 없습니다");
        }
        accountAuthorityService.addAccountAuthority(accountAuthorityDto);
    }
}
