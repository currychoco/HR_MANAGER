package site.currychoco.hrmanager.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.auth.domain.AccountAuthorityDto;
import site.currychoco.hrmanager.auth.service.AccountAuthorityService;

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

    @ResponseBody
    @PostMapping("/account-authority/add")
    public void addAccountAuthority(@RequestBody AccountAuthorityDto accountAuthorityDto){
        accountAuthorityService.addAccountAuthority(accountAuthorityDto);
    }
}